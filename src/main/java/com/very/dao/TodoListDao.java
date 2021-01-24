package com.very.dao;

import com.very.entity.TodoList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoListDao extends BaseDao {
    private final String TABLE_NAME = "todo_list";

    public List<TodoList> list() {
        return getJdbcTemplate().query("select id,category_id,category_name,title,content,director,deadline,remark,quadrant from todo_list", new BeanPropertyRowMapper<>(TodoList.class));
    }

    public boolean update(TodoList e) {
        return getJdbcTemplate().update("update todo_list set category_id = ?,category_name = ?,title = ?,content = ?,director=?,deadline = ?,remark = ?,quadrant=?  where id = ?",
                e.getCategoryId(), e.getCategoryName(), e.getTitle(), e.getContent(), e.getDirector(),e.getDeadline(), e.getRemark(), e.getQuadrant(),e.getId()) > 0 ? true : false;
    }

    public boolean delete(int id) {
        return getJdbcTemplate().update("delete from todo_list  where id = ?",id) > 0 ? true : false;
    }


    public boolean exist(String name) {
        return getJdbcTemplate().queryForObject("select count(*) from todo_list where name = ?", new Object[]{name}, Integer.class) > 0 ? true : false;
    }

    public TodoList get(int id) {
       return getJdbcTemplate().queryForObject("select id,category_id,category_name,title,content,director,deadline,remark,quadrant from todo_list where id = ?", new BeanPropertyRowMapper<>(TodoList.class), id);
    }

    public boolean insert(TodoList e) {
        return getJdbcTemplate().update("insert into todo_list(category_id,category_name,title,content,director,deadline,remark,quadrant) VALUES(?,?,?,?,?,?,?,?)",
                e.getCategoryId(), e.getCategoryName(), e.getTitle(), e.getContent(),e.getDirector(), e.getDeadline(), e.getRemark(),e.getQuadrant()) > 0 ? true : false;
    }


}
