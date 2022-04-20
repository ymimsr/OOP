package ru.nsu.fit.oop.Task_2_3_1.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Snake> snakes = new ArrayList<>();
    private Snake playerSnake;

    private final int width, height;
    private final int foodCount;
    private final int obstacleCount;
    private final int maxSnakeSize;
    private final int tickTime;

    // TODO: create enum GameState
    private boolean gameState;

    public Game(int width, int height, int foodCount, int obstacleCount, int maxSnakeSize, int tickTime) {
        this.width = width;
        this.height = height;
        this.foodCount = foodCount;
        this.obstacleCount = obstacleCount;
        this.maxSnakeSize = maxSnakeSize;
        this.tickTime = tickTime;
        this.gameState = true;

        init();
    }

    private Tile[][] field;

    private void init() {
        field = new Tile[width][height];
        generateField();

        //for (int i = 0; )
    }

    // TODO: decompose this method by creating FieldGenerator class for Tile
    private void generateField() {
        for (int i = 0; i < foodCount; i++) {
            int x = randomX();
            int y = randomY();

            if (field[x][y] != null) {
                i--;
            } else {
                Food newFood = new Food(x, y, 1);
                field[x][y] = newFood;
            }
        }

        // TODO: modify to prevent creating dead end obstacle maze
        for (int i = 0; i < obstacleCount; i++) {
            int x = randomX();
            int y = randomY();

            if (field[x][y] != null) {
                i--;
            } else {
                Obstacle newObstacle = new Obstacle(x, y);
                field[x][y] = newObstacle;
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (field[x][y] == null)
                    field[x][y] = new FreeTile(x, y);
            }
        }
    }

    private int randomX() {
        return (int) (Math.random() * width);
    }

    private int randomY() {
        return (int) (Math.random() * height);
    }

    public void start() {
        while (gameState) {
            for (Snake snake : snakes) {
                int headX = snake.getHead().x(), headY = snake.getHead().y();
                field[headX][headY].onCollision(snake, field);

                if (!snake.isAlive())
                    snakes.remove(snake);
                else if (snake.size() == maxSnakeSize)
                    end();
            }

            if (!playerSnake.isAlive())
                end();

            try {
                Thread.sleep(tickTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void end() {
        gameState = false;
    }

}
