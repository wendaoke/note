package com.very.dao;

import com.very.entity.Category;
import com.very.entity.TodoList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDao extends BaseDao {
    private final String TABLE_NAME = "category";
    public List<Category> list() {
        return getJdbcTemplate().query("select id,name from category", new BeanPropertyRowMapper<>(Category.class));
    }

    public boolean update(String name, String value) {
        return getJdbcTemplate().update("update category(name) VALUES(?)",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, name);

                    }
                }) > 0 ? true : false;
    }

    public boolean exist(String name) {
        return getJdbcTemplate().queryForObject("select count(*) from category where name = ?", new Object[]{name}, Integer.class) > 0 ? true : false;
    }

    public String get(int id) {
        final String[] val = {null};
         getJdbcTemplate().query("select name from category where id = ?", new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                val[0] = rs.getString("name");
            }}) ;
        return val[0];
    }

    public boolean insert(String name, String value) {
        return getJdbcTemplate().update("insert into category(name) VALUES(?)",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, name);
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
