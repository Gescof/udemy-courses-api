package com.apiof.dataworld.udemycourses.controllers;

import com.apiof.dataworld.udemycourses.models.dtos.BusinessAndFinancialCoursesDto;
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

}
