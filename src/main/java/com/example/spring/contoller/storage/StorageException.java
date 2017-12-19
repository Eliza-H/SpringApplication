package com.example.spring.contoller.storage;

/**
 * Created by elh on 27.09.17.
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}