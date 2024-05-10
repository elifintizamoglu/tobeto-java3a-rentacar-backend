package com.tobeto.rentacar.core.utilities.exceptions.types;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
