package com.semicolonafrica.exception;

public class ArtisanDoesNotExistException extends RuntimeException {
    public ArtisanDoesNotExistException(String message) {
        super(message);
    }
}
