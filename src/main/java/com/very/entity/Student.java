package com.very.entity;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();

    public Student(String id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
