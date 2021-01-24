package com.very.dao;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AppDao extends BaseDao {
    private final String TABLE_NAME = "app_config";

    public boolean update(String name, String value) {
        return getJdbcTemplate().update("update app_config(name,value) VALUES(?,?)",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, name);
                        ps.setString(2, value);
                    }
                }) > 0 ? true : false;
    }

    public boolean exist(String name) {
        return getJdbcTemplate().queryForObject("select count(*) from app_config where name = ?", new Object[]{name}, Integer.class) > 0 ? true : false;
    }

    public String get(String name) {
        final String[] val = {null};
         getJdbcTemplate().query("select value from app_config where name = ?", new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                val[0] = rs.getString("value");
            }}) ;
        return val[0];
    }

    public boolean insert(String name, String value) {
        return getJdbcTemplate().update("insert into app_config(name,value) VALUES(?,?)",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, name);
                        ps.setString(2, value);
                    }
                }) > 0 ? true : false;
    }

    public boolean put(String name, String value) {
        if (exist(name)) {
            return update(name, value);
        } else {
            return insert(name, value);
        }
    }
}
