package com.apiof.dataworld.udemycourses.repositories;

import com.apiof.dataworld.udemycourses.exceptions.GraphicDesignCoursesException;
import com.apiof.dataworld.udemycourses.models.dtos.GraphicDesignCoursesDto;
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
public class GraphicDesignRepository {
    private final Environment env;

    public List<GraphicDesignCoursesDto> findAll() {
        final List<GraphicDesignCoursesDto> graphicDesignCoursesList = new ArrayList<>();
        log.debug("Retrieving URL from environment");
        final String URL = env.getProperty("spring.datasource.graphicdesign.url");
        log.debug("Opening connection to data source with environment credentials");
        try (final Connection connection = getConnection(Optional.ofNullable(URL).orElseThrow(),
                env.getProperty("spring.datasource.graphicdesign.username"),
                env.getProperty("spring.datasource.graphicdesign.password"))) {
            final PreparedStatement statement = getPreparedStatement(connection);
            getResult(graphicDesignCoursesList, statement);
        } catch (SQLException e) {
            throw new GraphicDesignCoursesException("Error - Error while getting graphic design data source connection");
        }
        return graphicDesignCoursesList;
    }

    private PreparedStatement getPreparedStatement(final Connection connection) {
        log.debug("Init - Preparing statement to query");
        final String QUERY = "SELECT * FROM graphicdesign";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(QUERY);
        } catch (SQLException e) {
            throw new GraphicDesignCoursesException("Error - Error while preparing statement");
        }
        return statement;
    }

    private void getResult(final List<GraphicDesignCoursesDto> graphicDesignCoursesList,
                           final PreparedStatement statement) {
        log.debug("Init - Executing query to get result");
        try (final ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                mapResult(graphicDesignCoursesList, resultSet);
            }
        } catch (SQLException e) {
            throw new GraphicDesignCoursesException("Error - Error while parsing result set");
        }
    }

    private void mapResult(final List<GraphicDesignCoursesDto> graphicDesignCoursesList,
                           final ResultSet resultSet) throws SQLException {
        try {
            final GraphicDesignCoursesDto graphicDesignCourses = GraphicDesignCoursesDto.builder()
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
            graphicDesignCoursesList.add(graphicDesignCourses);
        } catch (DateTimeParseException dateTimeParseException) {
            log.warn(String.format("Warning - Error while parsing publishedTime field: \"%s\"",
                    resultSet.getString("publishedtime")));
        }
    }

}
