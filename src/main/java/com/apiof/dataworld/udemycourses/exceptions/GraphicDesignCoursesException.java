package com.apiof.dataworld.udemycourses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GraphicDesignCoursesException extends RuntimeException {
    public GraphicDesignCoursesException(String message) {
        super(message);
    }
}
