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
        //stage.setResizable(false);
        stage.show();


        /*StackPane root = new StackPane();
        Canvas canvas = new Canvas(600, 600);
        gc = canvas.getGraphicsContext2D();

        game = new Game(40, 40, 100, 10, new int[]{1, 2, 3, 5, 4, 2, 5, 5, 6, 6}, 20, 4);
        selfSnake = game.getSelfSnake();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> selfSnake.setDirection(Direction.UP);
                case DOWN -> selfSnake.setDirection(Direction.DOWN);
                case LEFT -> selfSnake.setDirection(Direction.LEFT);
                case RIGHT -> selfSnake.setDirection(Direction.RIGHT);
            }
        });

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setTitle("Snake");
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.setScene(scene);
        stage.show();

        CanvasPainter.paint(game, gc);
        new Thread(() -> {
            while (true) {
                CanvasPainter.paint(game, gc);
                game.move();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }

    public static void main(String[] args) {
        launch();
    }
}