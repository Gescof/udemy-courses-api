package com.apiof.dataworld.udemycourses.repositories;

import com.apiof.dataworld.udemycourses.models.entities.MusicalInstrumentCourses;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicalInstrumentRepository implements UdemyCoursesRepository<MusicalInstrumentCourses> {

    @Override
    public List<MusicalInstrumentCourses> findAll() {
        return null;
    }
}
