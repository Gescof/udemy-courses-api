package com.apiof.dataworld.udemycourses.repositories;

import com.apiof.dataworld.udemycourses.exceptions.MusicalInstrumentCoursesException;
import com.apiof.dataworld.udemycourses.models.dtos.MusicalInstrumentCoursesDto;
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
public class MusicalInstrumentRepository {
    private final Environment env;

    public List<MusicalInstrumentCoursesDto> findAll() {
        final List<MusicalInstrumentCoursesDto> musicalInstrumentCoursesList = new ArrayList<>();
        log.debug("Retrieving URL from environment");
        final String URL = env.getProperty("spring.datasource.musicalinstrument.url");
        log.debug("Opening connection to data source with environment credentials");
        try (final Connection connection = getConnection(Optional.ofNullable(URL).orElseThrow(),
                env.getProperty("spring.datasource.musicalinstrument.username"),
                env.getProperty("spring.datasource.musicalinstrument.password"))) {
            final PreparedStatement statement = getPreparedStatement(connection);
            getResult(musicalInstrumentCoursesList, statement);
        } catch (SQLException e) {
            throw new MusicalInstrumentCoursesException("Error - Error while getting musical instrument data source connection");
        }
        return musicalInstrumentCoursesList;
    }

    private PreparedStatement getPreparedStatement(final Connection connection) {
        log.debug("Init - Preparing statement to query");
        final String QUERY = "SELECT * FROM musicinstraments";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(QUERY);
        } catch (SQLException e) {
            throw new MusicalInstrumentCoursesException("Error - Error while preparing statement");
        }
        return statement;
    }

    private void getResult(final List<MusicalInstrumentCoursesDto> musicalInstrumentCoursesList,
                           final PreparedStatement statement) {
        log.debug("Init - Executing query to get result");
        try (final ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                mapResult(musicalInstrumentCoursesList, resultSet);
            }
        } catch (SQLException sqlException) {
            throw new MusicalInstrumentCoursesException("Error - Error with musical instrument database");
        }
    }

    private void mapResult(final List<MusicalInstrumentCoursesDto> musicalInstrumentCoursesList,
                           final ResultSet resultSet) throws SQLException {
        try {
            final MusicalInstrumentCoursesDto musicalInstrumentCourses = MusicalInstrumentCoursesDto.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .url(resultSet.getString("url"))
                    .isPaid(Boolean.valueOf(resultSet.getString("ispaid")))
                    .price(resultSet.getString("price"))
                    .numSubscribers(resultSet.getInt("numsubscribers"))
                    .numReviews(resultSet.getInt("numreviews"))
                    .numPublishedLectures(resultSet.getInt("numpublishedlectures"))
                    .instructionalLevel(resultSet.getString("instructionallevel"))
                    .contentInfo(resultSet.getString("contentinfo"))
                    .publishedTime(OffsetDateTime.parse(resultSet.getString("publishedtime")))
                    .total(resultSet.getInt("total"))
                    .build();
            musicalInstrumentCoursesList.add(musicalInstrumentCourses);
        } catch (DateTimeParseException dateTimeParseException) {
            log.warn(String.format("Warning - Error while parsing publishedTime field: \"%s\"",
                    resultSet.getString("publishedtime")));
        }
    }

}
