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
    private static final Color selfSnakeColor = Color.GREEN;
    private static final Color snakeColor = Color.CORAL;

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

        // offset
        double xt = 0;
        double yt = 0;
        if (tileSize * field.getSizeX() > tileSize * field.getSizeY()) {
            yt = (tileSize * field.getSizeX() - tileSize * field.getSizeY()) / 2;
        } else {
            xt = (tileSize * field.getSizeY() - tileSize * field.getSizeX()) / 2;
        }

        gc.setFill(borderCanvasColor);
        gc.fillRect(xt - borderWidth, yt - borderWidth, sizeX - 2 * xt + 2 * borderWidth, sizeY - 2 * yt + 2 * borderWidth);

        gc.setFill(freeTileColor);
        gc.fillRect(xt, yt, sizeX - 2 * xt, sizeY - 2 * yt);

        gc.setFill(foodColor);
        for (Food food : field.getFoods()) {
            int x = food.getTile().getPosition().x();
            int y = food.getTile().getPosition().y();
            gc.fillRect(tileSize * x + xt, tileSize * y + yt, tileSize, tileSize);
        }

        gc.setFill(obstacleColor);
        for (Obstacle obstacle : field.getObstacles()) {
            int x = obstacle.getTile().getPosition().x();
            int y = obstacle.getTile().getPosition().y();
            gc.fillRect(tileSize * x + xt, tileSize * y + yt, tileSize, tileSize);
        }

        paintSnake(selfSnakeColor, game.getSelfSnake(), xt, yt);
        for (Snake snake : snakes) {
            if (snake != game.getSelfSnake())
                paintSnake(snakeColor, snake, xt, yt);
        }

    }

    private void paintSnake(Color color, Snake snake, double xt, double yt) {
        for (SnakePart snakePart : snake.getSnakeBody()) {
            gc.setFill(color);
            int x = snakePart.getTile().getPosition().x();
            int y = snakePart.getTile().getPosition().y();
            gc.fillRect(tileSize * x + xt, tileSize * y + yt, tileSize, tileSize);
            if (snakePart.isHead()) {
                // drawing eyes
                gc.setFill(Color.RED);
                switch (snake.getDirection()) {
                    case UP -> {
                        gc.fillRect(tileSize * x + tileSize / 4 + xt, tileSize * y + 2 * tileSize / 3 + yt, 2, 2);
                        gc.fillRect(tileSize * x + 3 * tileSize / 4 + xt, tileSize * y + 2 * tileSize / 3 + yt, 2, 2);
                    }
                    case DOWN -> {
                        gc.fillRect(tileSize * x + tileSize / 4 + xt, tileSize * y + tileSize / 3 + yt, 2, 2);
                        gc.fillRect(tileSize * x + 3 * tileSize / 4 + xt, tileSize * y + tileSize / 3 + yt, 2, 2);
                    }
                    case RIGHT -> {
                        gc.fillRect(tileSize * x + 2 * tileSize / 3 + xt, tileSize * y + tileSize / 4 + yt, 2, 2);
                        gc.fillRect(tileSize * x + 2 * tileSize / 3 + xt, tileSize * y + 3 * tileSize / 4 + yt, 2, 2);
                    }
                    case LEFT -> {
                        gc.fillRect(tileSize * x + tileSize / 3 + xt, tileSize * y + tileSize / 4 + yt, 2, 2);
                        gc.fillRect(tileSize * x + tileSize / 3 + xt, tileSize * y + 3 * tileSize / 4 + yt, 2, 2);
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
