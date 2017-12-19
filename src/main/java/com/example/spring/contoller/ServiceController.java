package com.example.spring.contoller;

import com.example.spring.contoller.request.AddServiceRequest;
import com.example.spring.contoller.request.LikeRequest;

import com.example.spring.contoller.storage.StorageService;
import com.example.spring.dto.AnnouncementDTO;
import com.example.spring.model.AddServiceUserParameters;
import com.example.spring.model.ServiceItem;
import com.example.spring.service.ServiceCustomerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by elh on 17.09.17.
 */
@Controller
@RequestMapping("/service/")
public class ServiceController {
    @Autowired
    ServiceCustomerService serviceService;

    @Autowired
    StorageService storageService;

    @RequestMapping("list/{id}")
    @ResponseBody
    public List<AnnouncementDTO> listItems(@Valid @PathVariable("id") Long userId) {
        return serviceService.list(userId);
    }

    @RequestMapping("favorites/{id}")
    @ResponseBody
    public List<AnnouncementDTO> favorites(@Valid @PathVariable("id") Long userId) {
        return serviceService.getLiked(userId);
    }


    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @SneakyThrows
    @ResponseBody
    public boolean submit(@RequestParam("file") MultipartFile file, @RequestParam("title") String title, @RequestParam("description") String description) {
        String filename = storageService.store(file);
        AddServiceUserParameters addServiceRequest = new AddServiceRequest(title, description, filename);
        serviceService.add(addServiceRequest);
        return true;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String upload() {
        return "uploadFile";
    }


    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @SneakyThrows
    @ResponseBody
    public boolean delete(@Valid @PathVariable("id") Long id) {
        serviceService.delete(id);
        return true;
    }

    @RequestMapping(value = "like", method = RequestMethod.POST)
    @SneakyThrows
    @ResponseBody
    public boolean like(@RequestBody @Valid LikeRequest likeRequest) {
        serviceService.like(likeRequest);
        return true;
    }

    @RequestMapping(value = "unlike", method = RequestMethod.POST)
    @SneakyThrows
    @ResponseBody
    public boolean unlike(@RequestBody @Valid LikeRequest likeRequest) {
        serviceService.unlike(likeRequest);
        return true;
    }

}
