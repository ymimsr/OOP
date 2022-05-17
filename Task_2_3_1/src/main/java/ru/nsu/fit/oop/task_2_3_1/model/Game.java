package ru.nsu.fit.oop.task_2_3_1.model;

import ru.nsu.fit.oop.task_2_3_1.model.field.Field;
import ru.nsu.fit.oop.task_2_3_1.model.field.FieldGenerator;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Snake> snakes = new ArrayList<>();
    private List<SnakeAI> snakeAIS = new ArrayList<>();
    private Snake selfSnake;
    private Field field;
    private GameState gameState = GameState.RUNNING;
    private int maxSnakeSize;

    private Game(
            Field field,
            int maxSnakeSize,
            int snakeCount
    ) {
        this.field = field;
        this.maxSnakeSize = maxSnakeSize;
        initSnakes(snakeCount);
        for (Snake snake : snakes) {
            if (snake != selfSnake)
                snakeAIS.add(new SnakeAI(this, snake));
        }
    }

    public Game(
            int sizeX,
            int sizeY,
            int obstacleCount,
            int foodCount,
            int[] foodValues,
            int maxSnakeSize,
            int snakeCount) {
        this(FieldGenerator.generateField(sizeX, sizeY, obstacleCount, foodCount, foodValues), maxSnakeSize, snakeCount);
    }

    public Game(String fileName, int maxSnakeSize, int snakeCount) {
        this(FieldGenerator.generateFieldFromFile(fileName), maxSnakeSize, snakeCount);
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public Field getField() {
        return field;
    }

    public Snake getSelfSnake() {
        return selfSnake;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void initSnakes(int snakeCount) {
        if (field.getSizeX() * field.getSizeY() <= field.getObstacleCount() + field.getFoodCount() + snakeCount) {
            throw new IllegalStateException(
                    "Invalid amount of obstacles and food and snakes\n" +
                            "obstacleCount + foodCount + snakeCount = " +
                            (field.getObstacleCount() + field.getFoodCount() + snakeCount) + "\n" +
                            "sizeX * sizeY = " + (field.getSizeX() * field.getSizeY())
            );
        }

        for (int i = 0; i < snakeCount; i++) {
            Snake snake = field.spawnSnake();
            this.snakes.add(snake);
        }
        this.selfSnake = snakes.get(0);
    }

    public void move() {
        snakeAIS.forEach(SnakeAI::setDirection);
        snakes.forEach(Snake::move);
        snakes.removeIf(snake -> !snake.isAlive());
        snakeAIS.removeIf(snakeAI -> !getSelfSnake().isAlive());

        if (!selfSnake.isAlive()) {
            gameState = GameState.LOST;
        } else if (selfSnake.getSnakeSize() + selfSnake.getGrowingMoves() > maxSnakeSize) {
            gameState = GameState.WON;
        }
    }
}
