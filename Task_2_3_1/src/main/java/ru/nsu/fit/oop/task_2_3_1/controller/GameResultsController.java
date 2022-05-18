package ru.nsu.fit.oop.task_2_3_1.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.nsu.fit.oop.task_2_3_1.SnakeApplication;
import ru.nsu.fit.oop.task_2_3_1.model.GameState;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameResultsController implements Initializable {

    private static GameState gameState;
    private static int selfSize;
    private static int maxBotSize;
    private static int needToWin;

    public static void init(GameState gameState, int selfSize, int maxBotSize, int needToWin) {
        GameResultsController.gameState = gameState;
        GameResultsController.selfSize = selfSize;
        GameResultsController.maxBotSize = maxBotSize;
        GameResultsController.needToWin = needToWin;
    }


    @FXML
    private Label resultLabel;
    @FXML
    private Label selfSizeLabel;
    @FXML
    private Label maxBotSizeLabel;
    @FXML
    private Label needToWinLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (gameState == GameState.WON) {
            resultLabel.setText("You won!");
        } else if (gameState == GameState.LOST) {
            resultLabel.setText("You lost");
            selfSizeLabel.setText("Your size: " + selfSize);
            maxBotSizeLabel.setText("Max bot size: " + maxBotSize);
            needToWinLabel.setText("Needed to win: " + needToWin);
            selfSizeLabel.setVisible(true);
            maxBotSizeLabel.setVisible(true);
            needToWinLabel.setVisible(true);
        }
    }

    @FXML
    private void restartButtonClicked(ActionEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(SnakeApplication.class.getResource("start_scene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newRoot);
        stage.hide();
        stage.setScene(newScene);
        stage.show();
    }

}
