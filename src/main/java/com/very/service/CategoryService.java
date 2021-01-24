package com.very.service;

import com.very.dao.CategoryDao;
import com.very.dao.TodoListDao;
import com.very.entity.Category;
import com.very.entity.TodoList;
import com.very.utils.SpringContextUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    public List<Category> list(){
        CategoryDao dao = SpringContextUtils.getBean(CategoryDao.class);
        return dao.list();
    }
    public Map<Integer,Category> map(){
        List<Category> list = list();
        return list.stream().collect(Collectors.toMap(Category::getId, Function.identity(), (key1, key2) -> key2));
    }
}
