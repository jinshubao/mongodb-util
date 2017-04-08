package com.jean.mongodb.controller;

import com.jean.mongodb.repository.PersonRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainController implements Initializable {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @FXML
    private TreeView<Object> dbTreeView;
    @FXML
    private VBox dataList;
    @FXML
    private SplitPane splitPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        splitPane.setDividerPosition(0, 0.2);
        TreeItem<Object> root = new TreeItem<>("数据库列表");
        dbTreeView.setRoot(root);
        dbTreeView.setShowRoot(false);
        dbTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Object>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Object>> observable, TreeItem<Object> oldValue,
                                TreeItem<Object> newValue) {
            }
        });
    }
}
