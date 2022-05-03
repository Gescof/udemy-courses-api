package com.apiof.dataworld.udemycourses.services;

import com.apiof.dataworld.udemycourses.models.dtos.BusinessAndFinancialCoursesDto;
import com.apiof.dataworld.udemycourses.models.entities.BusinessAndFinancialCourses;
import com.apiof.dataworld.udemycourses.models.entities.GraphicDesignCourses;
import com.apiof.dataworld.udemycourses.models.entities.MusicalInstrumentCourses;
import com.apiof.dataworld.udemycourses.models.entities.WebDevelopmentCourses;
import com.apiof.dataworld.udemycourses.repositories.UdemyCoursesRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UdemyCoursesService {
    private final ModelMapper modelmapper;
    private final UdemyCoursesRepository<BusinessAndFinancialCourses> businessAndFinancialRepository;
    private final UdemyCoursesRepository<GraphicDesignCourses> graphicDesignRepository;
    private final UdemyCoursesRepository<MusicalInstrumentCourses> musicalInstrumentRepository;
    private final UdemyCoursesRepository<WebDevelopmentCourses> webDevelopmentRepository;

    public List<BusinessAndFinancialCoursesDto> findAllBusinessAndFinancialCourses() {
        final List<BusinessAndFinancialCourses> businessAndFinancialCoursesList = businessAndFinancialRepository.findAll();
        final List<BusinessAndFinancialCoursesDto> businessAndFinancialCoursesDtoList = businessAndFinancialCoursesList.stream()
                .map(businessAndFinancialCourses -> modelmapper.map(businessAndFinancialCourses, BusinessAndFinancialCoursesDto.class))
                .toList();
        return Optional.of(businessAndFinancialCoursesDtoList)
                .orElseThrow();
    }

}
