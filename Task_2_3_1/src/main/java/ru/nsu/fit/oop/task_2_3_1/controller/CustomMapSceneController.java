package ru.nsu.fit.oop.task_2_3_1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import ru.nsu.fit.oop.task_2_3_1.SnakeApplication;

import java.io.IOException;
import java.util.Objects;

public class CustomMapSceneController {

    @FXML
    private Slider widthSlider;
    @FXML
    private Slider heightSlider;
    @FXML
    private Slider obstaclesSlider;
    @FXML
    private Slider foodSlider;
    @FXML
    private Slider botsSlider;
    @FXML
    private Slider maxSizeSlider;
    @FXML
    private Slider deltaTimeSlider;

    @FXML
    private void startButtonClicked(ActionEvent event) throws IOException {
        initGameScene();
        loadGameScene();
    }

    private void loadGameScene() throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(SnakeApplication.class.getResource("game_scene.fxml")));
        Stage stage = (Stage) widthSlider.getScene().getWindow();
        Scene newScene = new Scene(newRoot);
        stage.hide();
        stage.setScene(newScene);
        stage.show();
    }

    private void initGameScene() {
        GameSceneController.sizeX = (int) widthSlider.getValue();
        GameSceneController.sizeY = (int) heightSlider.getValue();
        GameSceneController.obstaclesCount = (int) obstaclesSlider.getValue();
        GameSceneController.foodCount = (int) foodSlider.getValue();
        GameSceneController.foodValues = new int[GameSceneController.foodCount];
        for (int i = 0; i < GameSceneController.foodCount; i++) {
            GameSceneController.foodValues[i] = 1;
        }
        GameSceneController.maxSnakeSize = (int) maxSizeSlider.getValue();
        GameSceneController.snakeCount = 1 + ((int) botsSlider.getValue());
        GameSceneController.deltaTime = (int) deltaTimeSlider.getValue();
    }

}
