package ru.nsu.fit.oop.task_2_3_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.fit.oop.task_2_3_1.model.Direction;
import ru.nsu.fit.oop.task_2_3_1.model.Game;
import ru.nsu.fit.oop.task_2_3_1.model.Snake;
import ru.nsu.fit.oop.task_2_3_1.view.CanvasPainter;

import java.io.IOException;
import java.util.Objects;

public class SnakeApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(SnakeApplication.class.getResource("start_scene.fxml")));
        stage.setTitle("Snake");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}