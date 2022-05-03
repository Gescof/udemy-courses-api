package com.apiof.dataworld.udemycourses.configurations;

import com.apiof.dataworld.udemycourses.models.dtos.BusinessAndFinancialCoursesDto;
import com.apiof.dataworld.udemycourses.models.entities.BusinessAndFinancialCourses;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.typeMap(BusinessAndFinancialCourses.class, BusinessAndFinancialCoursesDto.class).addMappings(mapper -> {
            mapper.map(BusinessAndFinancialCourses::getId, BusinessAndFinancialCoursesDto::setId);
            mapper.map(BusinessAndFinancialCourses::getTitle, BusinessAndFinancialCoursesDto::setTitle);
            mapper.map(BusinessAndFinancialCourses::getUrl, BusinessAndFinancialCoursesDto::setUrl);
            mapper.map(BusinessAndFinancialCourses::getIsPaid, BusinessAndFinancialCoursesDto::setIsPaid);
            mapper.map(BusinessAndFinancialCourses::getPrice, BusinessAndFinancialCoursesDto::setPrice);
            mapper.map(BusinessAndFinancialCourses::getNumSubscribers, BusinessAndFinancialCoursesDto::setNumSubscribers);
            mapper.map(BusinessAndFinancialCourses::getNumReviews, BusinessAndFinancialCoursesDto::setNumReviews);
            mapper.map(BusinessAndFinancialCourses::getNumPublishedLectures, BusinessAndFinancialCoursesDto::setNumPublishedLectures);
            mapper.map(BusinessAndFinancialCourses::getInstructionalLevel, BusinessAndFinancialCoursesDto::setInstructionalLevel);
            mapper.map(BusinessAndFinancialCourses::getContentInfo, BusinessAndFinancialCoursesDto::setContentInfo);
            mapper.map(BusinessAndFinancialCourses::getPublishedTime, BusinessAndFinancialCoursesDto::setPublishedTime);
            mapper.map(BusinessAndFinancialCourses::getTotal, BusinessAndFinancialCoursesDto::setTotal);
        });
        return modelMapper;
    }

}
