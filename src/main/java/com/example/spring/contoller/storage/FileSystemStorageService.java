package com.example.spring.contoller.storage;

/**
 * Created by elh on 27.09.17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        File dir = new File(properties.getLocation());
        dir.mkdir();
    }

    @Override
    public String store(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + originalName);
            }
            if (originalName.contains("..")) {
                // This is a security check
                throw new StorageException(
                    "Cannot store file with relative path outside current directory "
                        + originalName);
            }
            int type = originalName.lastIndexOf(".jpeg");
            int type2 = originalName.lastIndexOf(".png");
            int type3 = originalName.lastIndexOf(".jpg");
            if (type == -1 && type2 == -1 && type3 == -1) {
                throw new StorageException("Invalid type");
            }
            while (true) {
                UUID uuid = UUID.randomUUID();
                String filename = uuid.toString();
                originalName = filename + originalName.substring(originalName.lastIndexOf("."));
                if (Files.notExists(Paths.get(rootLocation.toString() + "/" + originalName))) {
                    Files.copy(file.getInputStream(), this.rootLocation.resolve(originalName),
                        StandardCopyOption.REPLACE_EXISTING);
                    break;
                }
            }

        } catch (IOException e) {
            throw new StorageException("Failed to store file " + originalName, e);
        }
        return originalName;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                .filter(path -> !path.equals(this.rootLocation))
                .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}