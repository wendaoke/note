package com.very.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class C3P0Utils {
    private static  ComboPooledDataSource dataSource;
    private C3P0Utils() {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setUser("sa");
            dataSource.setPassword("");
//            dataSource.setJdbcUrl("jdbc:hsqldb:mem:very");
            dataSource.setJdbcUrl("jdbc:hsqldb:hsql://localhost/");
            dataSource.setDriverClass("org.hsqldb.jdbcDriver");
            dataSource.setInitialPoolSize(1);
            dataSource.setMinPoolSize(1);
            dataSource.setMaxPoolSize(3);
            dataSource.setMaxIdleTime(20);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static  ComboPooledDataSource getDataSource() {
        try {
            return dataSource ;
        } catch (Exception e) {
            throw new RuntimeException("can not get  database dataSource  ", e);
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("can not get  database connection  ", e);
        }
    }
}
