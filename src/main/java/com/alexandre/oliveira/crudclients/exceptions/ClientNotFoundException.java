package com.alexandre.oliveira.crudclients.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message) {
        super(message);
    }
}
