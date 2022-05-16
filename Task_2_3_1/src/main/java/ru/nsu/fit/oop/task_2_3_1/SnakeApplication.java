package ru.nsu.fit.oop.task_2_3_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.fit.oop.task_2_3_1.model.Direction;
import ru.nsu.fit.oop.task_2_3_1.model.Game;
import ru.nsu.fit.oop.task_2_3_1.model.GameState;
import ru.nsu.fit.oop.task_2_3_1.model.Snake;
import ru.nsu.fit.oop.task_2_3_1.view.Painter;

import java.io.IOException;

public class SnakeApplication extends Application {

    private int sizeX, sizeY;
    private GraphicsContext gc;
    private Game game;
    private Snake selfSnake;

    @Override
    public void start(Stage stage)
    {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(600, 600);
        gc = canvas.getGraphicsContext2D();

        game = new Game("field1.txt", 20, 1);
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

        Painter.paint(game, gc);
        new Thread(() -> {
            while (true) {
                Painter.paint(game, gc);
                game.move();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        launch();
    }
}