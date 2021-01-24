package com.very.configuration;

import com.very.utils.C3P0Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
class DatabaseConfiguration {
    @Bean
    public DataSource dataSource() {
        boolean databaseIsEmbedded = true;
        DataSource dataSource;
        if (databaseIsEmbedded) {
            dataSource = getEmbeddedHsqlDataSource();
        }else{
            dataSource = C3P0Utils.getDataSource();
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


    private DataSource getEmbeddedHsqlDataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("classpath:db/hsql_init.sql").build();
    }

}