package com.very.service;

import com.very.dao.TodoListDao;
import com.very.entity.TodoList;
import com.very.utils.SpringContextUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    public boolean insert(TodoList e) {
        TodoListDao dao = SpringContextUtils.getBean(TodoListDao.class);
        return dao.insert(e);
    }
    public boolean delete(int id) {
        TodoListDao dao = SpringContextUtils.getBean(TodoListDao.class);
        return dao.delete(id);
    }
    public boolean update(TodoList e) {
        TodoListDao dao = SpringContextUtils.getBean(TodoListDao.class);
        return dao.update(e);
    }
    public TodoList get(int id) {
        TodoListDao dao = SpringContextUtils.getBean(TodoListDao.class);
        return dao.get(id);
    }
    public List<TodoList> list(){
        TodoListDao dao = SpringContextUtils.getBean(TodoListDao.class);
        return dao.list();
    }
}
