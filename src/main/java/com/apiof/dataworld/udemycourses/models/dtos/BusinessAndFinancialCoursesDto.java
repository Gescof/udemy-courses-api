package com.apiof.dataworld.udemycourses.models.dtos;

import lombok.*;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BusinessAndFinancialCoursesDto {
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
