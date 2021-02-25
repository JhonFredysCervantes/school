package com.hardtech.school.controllers.exceptions;

public class ConfictException extends RuntimeException{

    private static final String DESCRIPTION = "Conflict (409)";

    public ConfictException(String detail) {
        super(DESCRIPTION+". "+detail);
    }
}
