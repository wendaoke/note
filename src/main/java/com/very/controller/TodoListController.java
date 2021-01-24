package com.very.controller;

import com.very.App;
import com.very.entity.TodoList;
import com.very.service.TodoListService;
import com.very.utils.SpringContextUtils;
import com.very.vo.TodoListVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TodoListController implements Initializable {
    //    @Resource
//    private TodoListService todoListService;
    @FXML
    private TextField tf_studentId;
    @FXML
    private TextField tf_studentName;

    @FXML
    private TableView tview_todolist;

    @FXML
    private TableColumn col_id;
    @FXML
    private TableColumn col_category;
    @FXML
    private TableColumn col_title;
    @FXML
    private TableColumn col_content;
    @FXML
    private TableColumn col_director;
    @FXML
    private TableColumn col_deadline;
    @FXML
    private TableColumn col_remark;
    ObservableList<TodoListVO> tablelst = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initView();
            initData();
        } catch (Exception ex) {
            Logger.getLogger(TodoListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void initView() throws Exception {
        col_id.setCellValueFactory(new PropertyValueFactory("id"));// 映射
        col_category.setCellValueFactory(new PropertyValueFactory("categoryName"));// 映射
        col_title.setCellValueFactory(new PropertyValueFactory("title"));// 映射
        col_content.setCellValueFactory(new PropertyValueFactory("content"));// 映射
        col_director.setCellValueFactory(new PropertyValueFactory("director"));// 映射
        col_deadline.setCellValueFactory(new PropertyValueFactory("deadline"));// 映射
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));// 映射
    }

    @FXML
    public void add(ActionEvent event) throws IOException {
        Parent target = FXMLLoader.load(App.class.getClassLoader().getResource("fxml/todolist_add.fxml"));//载入窗口B的定义文件；<span style="white-space:pre">	</span>
        Scene scene = new Scene(target); //创建场景；
        Stage stg = new Stage();//创建舞台；
        scene.getStylesheets().addAll(
                BootstrapFX.bootstrapFXStylesheet(),
                "org/kordamp/bootstrapfx/sampler/sampler.css",
                "org/kordamp/bootstrapfx/sampler/xml-highlighting.css");
        stg.getIcons().add(new Image(App.class.getClassLoader().getResourceAsStream("img/note.png")));
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setTitle("新增任务");
        stg.setIconified(false);
        stg.setScene(scene); //将场景载入舞台；
        stg.show(); //显示窗口；
    }

    @FXML
    public void edit(ActionEvent event) throws IOException {
        if (tview_todolist.getSelectionModel().getSelectedItems() == null || tview_todolist.getSelectionModel().getSelectedItems().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.NONE, "请选择记录", new ButtonType[]{ButtonType.CLOSE});
            alert.initOwner((( Button ) event.getSource()).getScene().getWindow());
            alert.show();
            return;
        }
        FXMLLoader loader = new FXMLLoader(
                App.class.getClassLoader().getResource("fxml/todolist_edit.fxml")
        );
        Parent target = loader.load();//载入窗口B的定义文件；<span style="white-space:pre">	</span>
        Scene scene = new Scene(target); //创建场景；
        Stage stg = new Stage();//创建舞台；
        scene.getStylesheets().addAll(
                BootstrapFX.bootstrapFXStylesheet(),
                "org/kordamp/bootstrapfx/sampler/sampler.css",
                "org/kordamp/bootstrapfx/sampler/xml-highlighting.css");
        stg.getIcons().add(new Image(App.class.getClassLoader().getResourceAsStream("img/note.png")));
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setTitle("编辑任务");
        stg.setIconified(false);
        stg.setScene(scene); //将场景载入舞台；
        TodoListEditController controller = loader.getController();
        TodoListVO vo = ( TodoListVO ) tview_todolist.getSelectionModel().getSelectedItem();
        TodoListService todoListService = SpringContextUtils.getBean(TodoListService.class);
        TodoList e = todoListService.get(vo.getId());
        controller.initData(e);
        stg.show(); //显示窗口；
    }

    @FXML
    public void query(ActionEvent event) {
        initData();
    }

    @FXML
    public void delete(ActionEvent event) {
        if (tview_todolist.getSelectionModel().getSelectedItems() == null || tview_todolist.getSelectionModel().getSelectedItems().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.NONE, "请选择记录", new ButtonType[]{ButtonType.CLOSE});
            alert.initOwner((( Button ) event.getSource()).getScene().getWindow());
            alert.show();
            return;
        }
        ObservableList<TodoListVO> selectedLines = tview_todolist.getSelectionModel().getSelectedItems();
        TodoListService todoListService = SpringContextUtils.getBean(TodoListService.class);
        for (Iterator<TodoListVO> it = selectedLines.iterator(); it.hasNext(); ) {
            TodoListVO vo = ( TodoListVO ) it.next();
            todoListService.delete(vo.getId());
        }
        tablelst.removeAll(selectedLines);
    }

    public void initData() {
        tablelst.clear();
        TodoListService todoListService = SpringContextUtils.getBean(TodoListService.class);
        List<TodoList> list = todoListService.list();
        for (TodoList t : list) {
            System.out.println(t.toString());
            tablelst.add(new TodoListVO(t));
        }
        tview_todolist.setItems(tablelst);
    }
}
