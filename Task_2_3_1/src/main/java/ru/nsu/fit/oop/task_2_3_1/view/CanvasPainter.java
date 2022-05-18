package ru.nsu.fit.oop.task_2_3_1.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.task_2_3_1.model.*;
import ru.nsu.fit.oop.task_2_3_1.model.field.Field;

import java.util.List;

public class CanvasPainter implements Painter {

    private static final Color freeTileColor = Color.BLACK;
    private static final Color borderCanvasColor = Color.GREEN;
    private static final Color foodColor = Color.YELLOW;
    private static final Color obstacleColor = Color.RED;
    private static final Color selfSnakeColor = Color.CORAL;
    private static final Color snakeColor = Color.GREEN;

    private static final int sizeX = 600;
    private static final int sizeY = 600;
    private double tileSize;
    private double borderWidth = 10;

    private GraphicsContext gc;
    private Label selfSizeLabel;
    private Label maxBotSizeLabel;
    private Label needToWinLabel;

    public CanvasPainter(GraphicsContext gc, Label selfSizeLabel, Label maxBotSizeLabel, Label needToWinLabel) {
        this.gc = gc;
        this.selfSizeLabel = selfSizeLabel;
        this.maxBotSizeLabel = maxBotSizeLabel;
        this.needToWinLabel = needToWinLabel;
    }

    public void paint(Game game) {
        Field field = game.getField();
        List<Snake> snakes = game.getSnakes();

        selfSizeLabel.setText("Your size: " + game.getSelfSnake().getSnakeSize());
        maxBotSizeLabel.setText("Max bot size: "
                + game.getSnakes()
                .stream()
                .filter(snake -> snake != game.getSelfSnake())
                .mapToInt(Snake::getSnakeSize)
                .max()
                .orElse(0)
        );
        needToWinLabel.setText("Need to win: " + game.getMaxSnakeSize());

        calcTileSize(field);

        gc.setFill(borderCanvasColor);
        if (tileSize * field.getSizeX() > tileSize * field.getSizeY()) {
            double t = (tileSize * field.getSizeX() - tileSize * field.getSizeY()) / 2;
            gc.fillRect(0, t - borderWidth, sizeX + borderWidth, sizeY - 2 * t + borderWidth);
        } else {
            double t = (tileSize * field.getSizeY() - tileSize * field.getSizeX()) / 2;
            gc.fillRect(t - borderWidth, 0, sizeX - 2 * t + borderWidth, sizeY + borderWidth);
        }

        gc.setFill(freeTileColor);
        gc.fillRect(0, 0, sizeX, sizeY);

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

        paintSnake(selfSnakeColor, game.getSelfSnake());
        for (Snake snake : snakes) {
            if (snake != game.getSelfSnake())
                paintSnake(snakeColor, snake);
        }

    }

    private void paintSnake(Color color, Snake snake) {
        for (SnakePart snakePart : snake.getSnakeBody()) {
            gc.setFill(color);
            int x = snakePart.getTile().getPosition().x();
            int y = snakePart.getTile().getPosition().y();
            gc.fillRect(tileSize * x, tileSize * y, tileSize, tileSize);
            if (snakePart.isHead()) {
                // drawing eyes
                gc.setFill(Color.RED);
                switch (snake.getDirection()) {
                    case UP -> {
                        gc.fillRect(tileSize * x + tileSize / 4, tileSize * y + 2 * tileSize / 3, 2, 2);
                        gc.fillRect(tileSize * x + 3 * tileSize / 4, tileSize * y + 2 * tileSize / 3, 2, 2);
                    }
                    case DOWN -> {
                        gc.fillRect(tileSize * x + tileSize / 4, tileSize * y + tileSize / 3, 2, 2);
                        gc.fillRect(tileSize * x + 3 * tileSize / 4, tileSize * y + tileSize / 3, 2, 2);
                    }
                    case RIGHT -> {
                        gc.fillRect(tileSize * x + 2 * tileSize / 3, tileSize * y + tileSize / 4, 2, 2);
                        gc.fillRect(tileSize * x + 2 * tileSize / 3, tileSize * y + 3 * tileSize / 4, 2, 2);
                    }
                    case LEFT -> {
                        gc.fillRect(tileSize * x + tileSize / 3, tileSize * y + tileSize / 4, 2, 2);
                        gc.fillRect(tileSize * x + tileSize / 3, tileSize * y + 3 * tileSize / 4, 2, 2);
                    }
                }
            }
        }
    }

    private void calcTileSize(Field field) {
        int fieldSizeX = field.getSizeX();
        int fieldSizeY = field.getSizeY();

        if (fieldSizeX > fieldSizeY) {
            tileSize = sizeX * 1.0 / fieldSizeX;
        } else {
            tileSize = sizeY * 1.0 / fieldSizeY;
        }
    }

}
