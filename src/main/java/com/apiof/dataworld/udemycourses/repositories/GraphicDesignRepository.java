package com.apiof.dataworld.udemycourses.repositories;

import com.apiof.dataworld.udemycourses.models.entities.GraphicDesignCourses;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GraphicDesignRepository implements UdemyCoursesRepository<GraphicDesignCourses> {

    @Override
    public List<GraphicDesignCourses> findAll() {
        return null;
    }
}
