package com.apiof.dataworld.udemycourses.controllers;

import com.apiof.dataworld.udemycourses.models.dtos.BusinessAndFinancialCoursesDto;
import com.apiof.dataworld.udemycourses.models.dtos.GraphicDesignCoursesDto;
import com.apiof.dataworld.udemycourses.models.dtos.MusicalInstrumentCoursesDto;
import com.apiof.dataworld.udemycourses.models.dtos.WebDevelopmentCoursesDto;
import com.apiof.dataworld.udemycourses.services.UdemyCoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UdemyCoursesController {
    private final UdemyCoursesService udemyCoursesService;

    @GetMapping("/courses/businessandfinancial")
    public ResponseEntity<List<BusinessAndFinancialCoursesDto>> getAllBusinessAndFinancialCourses() {
        final List<BusinessAndFinancialCoursesDto> foundUdemyCourses = udemyCoursesService.findAllBusinessAndFinancialCourses();
        return ResponseEntity.ok(foundUdemyCourses);
    }

    @GetMapping("/courses/graphicdesign")
    public ResponseEntity<List<GraphicDesignCoursesDto>> getAllGraphicDesignCourses() {
        final List<GraphicDesignCoursesDto> foundUdemyCourses = udemyCoursesService.findAllGraphicDesignCourses();
        return ResponseEntity.ok(foundUdemyCourses);
    }

    @GetMapping("/courses/musicalinstrument")
    public ResponseEntity<List<MusicalInstrumentCoursesDto>> getAllMusicalInstrumentCourses() {
        final List<MusicalInstrumentCoursesDto> foundUdemyCourses = udemyCoursesService.findAllMusicalInstrumentCourses();
        return ResponseEntity.ok(foundUdemyCourses);
    }

    @GetMapping("/courses/webdevelopment")
    public ResponseEntity<List<WebDevelopmentCoursesDto>> getAllWebDevelopmentCourses() {
        final List<WebDevelopmentCoursesDto> foundUdemyCourses = udemyCoursesService.findAllWebDevelopmentCourses();
        return ResponseEntity.ok(foundUdemyCourses);
    }

}
