package com.apiof.dataworld.udemycourses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MusicalInstrumentCoursesException extends RuntimeException {
    public MusicalInstrumentCoursesException(String message) {
        super(message);
    }
}
