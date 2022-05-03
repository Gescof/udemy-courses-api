package com.apiof.dataworld.udemycourses.repositories;

import com.apiof.dataworld.udemycourses.models.entities.WebDevelopmentCourses;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WebDevelopmentRepository implements UdemyCoursesRepository<WebDevelopmentCourses> {

    @Override
    public List<WebDevelopmentCourses> findAll() {
        return null;
    }
}
