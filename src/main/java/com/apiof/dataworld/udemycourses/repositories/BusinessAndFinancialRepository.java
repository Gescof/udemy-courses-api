package com.apiof.dataworld.udemycourses.repositories;

import com.apiof.dataworld.udemycourses.exceptions.BusinessAndFinancialCoursesException;
import com.apiof.dataworld.udemycourses.models.entities.BusinessAndFinancialCourses;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

@Repository
@AllArgsConstructor
public class BusinessAndFinancialRepository implements UdemyCoursesRepository<BusinessAndFinancialCourses> {
    private final Environment env;

    @Override
    public List<BusinessAndFinancialCourses> findAll() {
        final List<BusinessAndFinancialCourses> businessAndFinancialCoursesList = new ArrayList<>();
        final String URL = env.getProperty("spring.datasource.businessandfinancial.url");
        try (final Connection connection = getConnection(Optional.ofNullable(URL).orElseThrow(),
                env.getProperty("spring.datasource.businessandfinancial.username"),
                env.getProperty("spring.datasource.businessandfinancial.password"))) {
            final PreparedStatement statement = getPreparedStatement(connection);
            parseResult(businessAndFinancialCoursesList, statement);
        } catch (SQLException e) {
            throw new BusinessAndFinancialCoursesException("Error - Error while getting connection");
        }
        return businessAndFinancialCoursesList;
    }

    private PreparedStatement getPreparedStatement(final Connection connection) {
        final String QUERY = "SELECT * FROM businessfinance";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(QUERY);
        } catch (SQLException e) {
            throw new BusinessAndFinancialCoursesException("Error - Error while preparing statement");
        }
        return statement;
    }

    private void parseResult(List<BusinessAndFinancialCourses> businessAndFinancialCoursesList, PreparedStatement statement) {
        try (final ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                BusinessAndFinancialCourses businessAndFinancialCourses = new BusinessAndFinancialCourses();
                businessAndFinancialCourses.setId(resultSet.getLong("id"));
                businessAndFinancialCourses.setTitle(resultSet.getString("title"));
                businessAndFinancialCourses.setUrl(resultSet.getString("url"));
                businessAndFinancialCourses.setIsPaid(resultSet.getBoolean("ispaid"));
                businessAndFinancialCourses.setPrice(resultSet.getString("price"));
                businessAndFinancialCourses.setNumSubscribers(resultSet.getInt("numsubscribers"));
                businessAndFinancialCourses.setNumReviews(resultSet.getInt("numreviews"));
                businessAndFinancialCourses.setNumPublishedLectures(resultSet.getInt("numpublishedlectures"));
                businessAndFinancialCourses.setInstructionalLevel(resultSet.getString("instructionallevel"));
                businessAndFinancialCourses.setContentInfo(resultSet.getString("contentinfo"));
                businessAndFinancialCourses.setPublishedTime(resultSet.getObject("publishedtime", OffsetDateTime.class));
                businessAndFinancialCourses.setTotal(resultSet.getInt("total"));
                businessAndFinancialCoursesList.add(businessAndFinancialCourses);
            }
        } catch (SQLException e) {
            throw new BusinessAndFinancialCoursesException("Error - Error while parsing result set");
        }
    }
}
