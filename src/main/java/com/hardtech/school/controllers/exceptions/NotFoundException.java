package com.hardtech.school.controllers.exceptions;

public class NotFoundException extends RuntimeException{

    private static final String DESCRIPTION = "Not found (404)";

    public NotFoundException(String detail) {
        super(DESCRIPTION+". "+detail);
    }
}
