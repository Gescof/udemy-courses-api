package com.apiof.dataworld.udemycourses.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebDevelopmentCourses extends UdemyCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @Column(name = "ispaid")
    private Boolean isPaid;
    @Column(name = "price")
    private String price;
    @Column(name = "numsubscribers")
    private Integer numSubscribers;
    @Column(name = "numreviews")
    private Integer numReviews;
    @Column(name = "numpublishedlectures")
    private Integer numPublishedLectures;
    @Column(name = "instructionallevel")
    private String instructionalLevel;
    @Column(name = "contentinfo")
    private String contentInfo;
    @Column(name = "publishedtime")
    private OffsetDateTime publishedTime;
    @Column(name = "total")
    private Integer total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WebDevelopmentCourses that = (WebDevelopmentCourses) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}