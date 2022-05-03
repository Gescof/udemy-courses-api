package com.apiof.dataworld.udemycourses.repositories;

import com.apiof.dataworld.udemycourses.models.entities.UdemyCourses;

import java.util.List;

public interface UdemyCoursesRepository<T extends UdemyCourses> {
    List<T> findAll();
}
