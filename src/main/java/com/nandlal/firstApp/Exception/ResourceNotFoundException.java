package com.nandlal.firstApp.Exception;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
