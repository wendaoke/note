package com.very.vo;

import com.mchange.v2.codegen.bean.SimpleProperty;
import com.very.entity.TodoList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;

public class TodoListVO {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty categoryId = new SimpleIntegerProperty();
    private SimpleStringProperty categoryName = new SimpleStringProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty content = new SimpleStringProperty();
    private SimpleStringProperty director = new SimpleStringProperty();
    private SimpleStringProperty deadline = new SimpleStringProperty();
    private SimpleStringProperty  remark= new SimpleStringProperty();

    public TodoListVO(TodoList todoList) {
        this.setId(todoList.getId());
        this.setCategoryId(todoList.getCategoryId());
        this.setCategoryName(todoList.getCategoryName());
        this.setTitle(todoList.getTitle());
        this.setContent(todoList.getContent());
        this.setDirector(todoList.getDirector());
        this.setDeadline(DateFormat.getDateInstance(DateFormat.DEFAULT).format(todoList.getDeadline()));
        this.setRemark(todoList.getRemark());
    }
    public TodoListVO(Integer id,Integer categoryId,String categoryName,String title,String content,String director,String deadline,String remark) {
        this.setId(id);
        this.setCategoryId(categoryId);
        this.setCategoryName(categoryName);
        this.setTitle(title);
        this.setContent(content);
        this.setDirector(director);
        this.setDeadline(deadline);
        this.setRemark(remark);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getCategoryId() {
        return categoryId.get();
    }

    public SimpleIntegerProperty categoryIdProperty() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId.set(categoryId);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public SimpleStringProperty categoryNameProperty() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getDirector() {
        return director.get();
    }

    public SimpleStringProperty directorProperty() {
        return director;
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public String getDeadline() {
        return deadline.get();
    }

    public SimpleStringProperty deadlineProperty() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline.set(deadline);
    }

    public String getRemark() {
        return remark.get();
    }

    public SimpleStringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }
}
