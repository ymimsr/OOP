package ru.nsu.fit.oop.task_2_3_1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.nsu.fit.oop.task_2_3_1.SnakeApplication;

import java.io.IOException;
import java.util.Objects;

public class StartSceneController {

    @FXML
    private void customMapButtonClicked(ActionEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(SnakeApplication.class.getResource("custom_map_scene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newRoot);
        stage.hide();
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    private void levelButtonClicked(ActionEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(SnakeApplication.class.getResource("level_scene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newRoot);
        stage.hide();
        stage.setScene(newScene);
        stage.show();
    }

}
