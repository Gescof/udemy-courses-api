package com.apiof.dataworld.udemycourses.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class WebDevelopmentCoursesDto {
    private Long id;
    private String title;
    private String url;
    private Boolean isPaid;
    private String price;
    private Integer numSubscribers;
    private Integer numReviews;
    private Integer numPublishedLectures;
    private String instructionalLevel;
    private String contentInfo;
    private OffsetDateTime publishedTime;
    private Integer total;
}
