package com.very.controller;

import com.very.entity.Category;
import com.very.entity.Select;
import com.very.entity.TodoList;
import com.very.service.CategoryService;
import com.very.service.TodoListService;
import com.very.utils.DateUtils;
import com.very.utils.SpringContextUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class TodoListEditController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField title;
    @FXML
    private TextArea content;
    @FXML
    private TextField director;
    @FXML
    private DatePicker deadline;
    @FXML
    private TextArea remark;
    @FXML
    private ChoiceBox<Select> quadrant_choice;
    @FXML
    private ChoiceBox<Category> category_choice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initUI();
        } catch (Exception ex) {
            Logger.getLogger(TodoListEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void initData(TodoList e) {
        title.setText(e.getTitle());
        content.setText(e.getContent());
        director.setText(e.getDirector());
        deadline.setValue(DateUtils.asLocalDate(e.getDeadline()));
        remark.setText(e.getRemark());
        category_choice.getSelectionModel().select(new Category(e.getCategoryId(), e.getCategoryName()));
        quadrant_choice.getSelectionModel().select(getQuadrantMap().get(e.getQuadrant()));
        id.setText(String.valueOf(e.getId()));

    }

    @FXML
    public void add(ActionEvent event) {
        TodoListService service = SpringContextUtils.getBean(TodoListService.class);
        TodoList e = new TodoList();
        e.setCategoryId(category_choice.getValue().getId());
        e.setCategoryName(category_choice.getValue().getName());
        e.setContent(content.getText());
        e.setDeadline(DateUtils.localDate2Date(deadline.getValue()));
        e.setDirector(director.getText());
        e.setRemark(remark.getText());
        e.setTitle(title.getText());
        if (service.insert(e)) {
            Button btn = ( Button ) event.getSource();
            Stage stage = ( Stage ) btn.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    public void edit(ActionEvent event) {
        TodoListService service = SpringContextUtils.getBean(TodoListService.class);
        TodoList e = new TodoList();
        e.setCategoryId(category_choice.getValue().getId());
        e.setCategoryName(category_choice.getValue().getName());
        e.setContent(content.getText());
        e.setDeadline(DateUtils.localDate2Date(deadline.getValue()));
        e.setDirector(director.getText());
        e.setRemark(remark.getText());
        e.setTitle(title.getText());
        e.setId(Integer.parseInt(id.getText()));
        if (service.update(e)) {
            Button btn = ( Button ) event.getSource();
            Stage stage = ( Stage ) btn.getScene().getWindow();
            stage.close();
        }

    }

    private List<Select> quadrantList() {
        List<Select> list = new ArrayList();
        list.add(new Select(1, "紧急&重要"));
        list.add(new Select(2, "紧急&不重要"));
        list.add(new Select(3, "重要&不紧急"));
        list.add(new Select(4, "不紧急&不重要"));
        return list;
    }

    public Map<Integer, Select> getQuadrantMap() {
        List<Select> quadrants = quadrantList();
        return quadrants.stream().collect(Collectors.toMap(Select::getValue, Function.identity(), (key1, key2) -> key2));
    }

    public void initUI() {
        quadrant_choice.getItems().addAll(quadrantList());
        quadrant_choice.converterProperty().set(new StringConverter<Select>() {
            @Override
            public String toString(Select object) {
                return object.getName();
            }

            @Override
            public Select fromString(String string) {
                return null;
            }
        });
        CategoryService categoryService = SpringContextUtils.getBean(CategoryService.class);
        categoryService.list().stream().forEach(e -> category_choice.getItems().add(e));
        category_choice.converterProperty().set(new StringConverter<Category>() {
            @Override
            public String toString(Category object) {
                return object.getName();
            }

            @Override
            public Category fromString(String string) {
                return null;
            }
        });
    }
}
