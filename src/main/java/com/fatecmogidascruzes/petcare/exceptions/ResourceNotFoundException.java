package com.fatecmogidascruzes.petcare.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String message)
    {
        super(message);
    }
}
