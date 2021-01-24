package com.very.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

public abstract  class BaseDao {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private String tableName;

    protected BaseDao(String tableName) {

        this.tableName = tableName;
    }

    public BaseDao() {

    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void clearAll() {
        getJdbcTemplate().update("DELETE FROM " + tableName);
    }

    public int count() {
        return getJdbcTemplate().queryForObject( "SELECT count(*) FROM " + tableName, Integer.class);
    }
}
