package com.apiof.dataworld.udemycourses.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class UdemyCoursesDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.businessandfinancial")
    public DataSourceProperties businessAndFinancialDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.graphicdesign")
    public DataSourceProperties graphicDesignDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.musicalinstrument")
    public DataSourceProperties musicalInstrumentDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.webdevelopment")
    public DataSourceProperties webDevelopmentDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("businessandfinancial")
    public DataSource businessAndFinancialDataSource() {
        return businessAndFinancialDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Qualifier("graphicdesign")
    public DataSource graphicDesignDataSource() {
        return graphicDesignDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Qualifier("musicalinstrument")
    public DataSource musicalInstrumentDataSource() {
        return musicalInstrumentDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Qualifier("webdevelopment")
    public DataSource webdevelopmentDataSource() {
        return webDevelopmentDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
