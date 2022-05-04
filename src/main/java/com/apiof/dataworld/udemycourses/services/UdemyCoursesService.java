package com.apiof.dataworld.udemycourses.services;

import com.apiof.dataworld.udemycourses.exceptions.BusinessAndFinancialCoursesException;
import com.apiof.dataworld.udemycourses.exceptions.GraphicDesignCoursesException;
import com.apiof.dataworld.udemycourses.exceptions.MusicalInstrumentCoursesException;
import com.apiof.dataworld.udemycourses.exceptions.WebDevelopmentCoursesException;
import com.apiof.dataworld.udemycourses.models.dtos.BusinessAndFinancialCoursesDto;
import com.apiof.dataworld.udemycourses.models.dtos.GraphicDesignCoursesDto;
import com.apiof.dataworld.udemycourses.models.dtos.MusicalInstrumentCoursesDto;
import com.apiof.dataworld.udemycourses.models.dtos.WebDevelopmentCoursesDto;
import com.apiof.dataworld.udemycourses.repositories.BusinessAndFinancialRepository;
import com.apiof.dataworld.udemycourses.repositories.GraphicDesignRepository;
import com.apiof.dataworld.udemycourses.repositories.MusicalInstrumentRepository;
import com.apiof.dataworld.udemycourses.repositories.WebDevelopmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UdemyCoursesService {
    private final BusinessAndFinancialRepository businessAndFinancialRepository;
    private final GraphicDesignRepository graphicDesignRepository;
    private final MusicalInstrumentRepository musicalInstrumentRepository;
    private final WebDevelopmentRepository webDevelopmentRepository;

    public List<BusinessAndFinancialCoursesDto> findAllBusinessAndFinancialCourses() {
        log.info("Init - Find All Business and Financial Courses");
        final List<BusinessAndFinancialCoursesDto> businessAndFinancialCoursesDtoList = businessAndFinancialRepository.findAll();
        return Optional.of(businessAndFinancialCoursesDtoList)
                .orElseThrow(() -> new BusinessAndFinancialCoursesException("Error - No business and financial courses could be retrieved"));
    }

    public List<GraphicDesignCoursesDto> findAllGraphicDesignCourses() {
        log.info("Init - Find All Graphic Design Courses");
        final List<GraphicDesignCoursesDto> graphicDesignCoursesDtoList = graphicDesignRepository.findAll();
        return Optional.of(graphicDesignCoursesDtoList)
                .orElseThrow(() -> new GraphicDesignCoursesException("Error - No graphic design courses could be retrieved"));
    }

    public List<MusicalInstrumentCoursesDto> findAllMusicalInstrumentCourses() {
        log.info("Init - Find All Musical Instrument Courses");
        final List<MusicalInstrumentCoursesDto> musicalInstrumentCoursesDtoList = musicalInstrumentRepository.findAll();
        return Optional.of(musicalInstrumentCoursesDtoList)
                .orElseThrow(() -> new MusicalInstrumentCoursesException("Error - No musical instrument courses could be retrieved"));
    }

    public List<WebDevelopmentCoursesDto> findAllWebDevelopmentCourses() {
        log.info("Init - Find All Web Development Courses");
        final List<WebDevelopmentCoursesDto> webDevelopmentCoursesDtoList = webDevelopmentRepository.findAll();
        return Optional.of(webDevelopmentCoursesDtoList)
                .orElseThrow(() -> new WebDevelopmentCoursesException("Error - No web development courses could be retrieved"));
    }

}
