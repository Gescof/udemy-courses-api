package com.apiof.dataworld.udemycourses.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class UdemyCoursesJdbcConfiguration {
    @Bean
    public JdbcTemplate businessAndFinancialJdbcTemplate(@Qualifier("businessandfinancial") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate graphicDesignJdbcTemplate(@Qualifier("graphicdesign") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate musicalInstrumentJdbcTemplate(@Qualifier("musicalinstrument") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate webDevelopmentJdbcTemplate(@Qualifier("webdevelopment") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
