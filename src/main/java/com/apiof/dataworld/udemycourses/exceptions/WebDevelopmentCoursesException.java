package com.apiof.dataworld.udemycourses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class WebDevelopmentCoursesException extends RuntimeException {
    public WebDevelopmentCoursesException(String message) {
        super(message);
    }
}
