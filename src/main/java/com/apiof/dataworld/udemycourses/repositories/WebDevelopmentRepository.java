package com.apiof.dataworld.udemycourses.repositories;

import com.apiof.dataworld.udemycourses.exceptions.WebDevelopmentCoursesException;
import com.apiof.dataworld.udemycourses.models.dtos.WebDevelopmentCoursesDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

@Repository
@AllArgsConstructor
@Slf4j
public class WebDevelopmentRepository {
    private final Environment env;

    public List<WebDevelopmentCoursesDto> findAll() {
        final List<WebDevelopmentCoursesDto> webDevelopmentCoursesList = new ArrayList<>();
        log.debug("Retrieving URL from environment");
        final String URL = env.getProperty("spring.datasource.webdevelopment.url");
        try (final Connection connection = getConnection(Optional.ofNullable(URL).orElseThrow(),
                env.getProperty("spring.datasource.webdevelopment.username"),
                env.getProperty("spring.datasource.webdevelopment.password"))) {
            final PreparedStatement statement = getPreparedStatement(connection);
            getResult(webDevelopmentCoursesList, statement);
        } catch (SQLException e) {
            throw new WebDevelopmentCoursesException("Error - Error while getting web development data source connection");
        }
        return webDevelopmentCoursesList;
    }

    private PreparedStatement getPreparedStatement(final Connection connection) {
        log.debug("Init - Preparing statement to query");
        final String QUERY = "SELECT * FROM webdevelopment";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(QUERY);
        } catch (SQLException e) {
            throw new WebDevelopmentCoursesException("Error - Error while preparing statement");
        }
        return statement;
    }

    private void getResult(final List<WebDevelopmentCoursesDto> webDevelopmentCoursesList,
                           final PreparedStatement statement) {
        log.debug("Init - Executing query to get result");
        try (final ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                mapResult(webDevelopmentCoursesList, resultSet);
            }
        } catch (SQLException e) {
            throw new WebDevelopmentCoursesException("Error - Error while parsing result set");
        }
    }

    private void mapResult(final List<WebDevelopmentCoursesDto> webDevelopmentCoursesList,
                           final ResultSet resultSet) throws SQLException {
        try {
            final WebDevelopmentCoursesDto webDevelopmentCourses = WebDevelopmentCoursesDto.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .url(resultSet.getString("url"))
                    .isPaid(resultSet.getBoolean("ispaid"))
                    .price(resultSet.getString("price"))
                    .numSubscribers(resultSet.getInt("numsubscribers"))
                    .numReviews(resultSet.getInt("numreviews"))
                    .numPublishedLectures(resultSet.getInt("numpublishedlectures"))
                    .instructionalLevel(resultSet.getString("instructionallevel"))
                    .contentInfo(resultSet.getString("contentinfo"))
                    .publishedTime(OffsetDateTime.parse(resultSet.getString("publishedtime")))
                    .total(resultSet.getInt("total"))
                    .build();
            webDevelopmentCoursesList.add(webDevelopmentCourses);
        } catch (DateTimeParseException dateTimeParseException) {
            log.warn(String.format("Warning - Error while parsing publishedTime field: \"%s\"",
                    resultSet.getString("publishedtime")));
        }
    }

}
