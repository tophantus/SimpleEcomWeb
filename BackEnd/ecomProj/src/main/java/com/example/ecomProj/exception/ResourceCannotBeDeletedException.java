package com.example.ecomProj.exception;

public class ResourceCannotBeDeletedException extends RuntimeException {
    public ResourceCannotBeDeletedException(String message) {
        super(message);
    }
}
