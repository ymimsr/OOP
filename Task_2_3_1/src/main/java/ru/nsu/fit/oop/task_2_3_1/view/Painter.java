package ru.nsu.fit.oop.task_2_3_1.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.task_2_3_1.model.*;
import ru.nsu.fit.oop.task_2_3_1.model.field.Field;

import java.util.List;

public class Painter {

    private static final Color freeTileColor = Color.BLACK;
    private static final Color foodColor = Color.YELLOW;
    private static final Color obstacleColor = Color.RED;
    private static final Color snakeColor = Color.GREEN;

    private static final int sizeX = 600;
    private static final int sizeY = 600;

    private static int tileSize;

    public static void paint(Game game, GraphicsContext gc) {
        Field field = game.getField();
        List<Snake> snakes = game.getSnakes();

        gc.setFill(freeTileColor);
        gc.fillRect(0, 0, sizeX, sizeY);

        calcTileSize(field);

        gc.setFill(foodColor);
        for (Food food : field.getFoods()) {
            int x = food.getTile().getPosition().x();
            int y = food.getTile().getPosition().y();
            gc.fillRect(tileSize * x, tileSize * y, tileSize, tileSize);
        }

        gc.setFill(obstacleColor);
        for (Obstacle obstacle : field.getObstacles()) {
            int x = obstacle.getTile().getPosition().x();
            int y = obstacle.getTile().getPosition().y();
            gc.fillRect(tileSize * x, tileSize * y, tileSize, tileSize);
        }

        gc.setFill(snakeColor);
        for (Snake snake : snakes) {
            for (SnakePart snakePart : snake.getSnakeBody()) {
                int x = snakePart.getTile().getPosition().x();
                int y = snakePart.getTile().getPosition().y();
                gc.fillRect(tileSize * x, tileSize * y, tileSize, tileSize);
            }
        }

    }

    private static void calcTileSize(Field field) {
        int fieldSizeX = field.getSizeX();
        int fieldSizeY = field.getSizeY();

        if (fieldSizeX > fieldSizeY) {
            tileSize = sizeX / fieldSizeX;
        } else {
            tileSize = sizeY / fieldSizeY;
        }
    }

}
