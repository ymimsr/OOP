package ru.nsu.fit.oop.task_2_3_1.model;

import ru.nsu.fit.oop.task_2_3_1.model.field.Field;
import ru.nsu.fit.oop.task_2_3_1.model.field.FieldGenerator;
import ru.nsu.fit.oop.task_2_3_1.view.Painter;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Snake> snakes = new ArrayList<>();
    private List<SnakeAI> snakeAIS = new ArrayList<>();
    private Snake selfSnake;
    private Field field;
    private GameState gameState = GameState.RUNNING;
    private int maxSnakeSize;

    private Painter painter;

    private Game(
            Field field,
            int maxSnakeSize,
            int snakeCount,
            Painter painter) {
        this.field = field;
        this.maxSnakeSize = maxSnakeSize;
        this.painter = painter;
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
            int snakeCount, Painter painter) {
        this(FieldGenerator.generateField(sizeX, sizeY, obstacleCount, foodCount, foodValues), maxSnakeSize, snakeCount, painter);
    }

    public Game(String fileName, int maxSnakeSize, int snakeCount, Painter painter) {
        this(FieldGenerator.generateFieldFromFile(fileName), maxSnakeSize, snakeCount, painter);
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

    public int getMaxSnakeSize() {
        return maxSnakeSize;
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
        painter.paint(this);
        snakeAIS.forEach(SnakeAI::setDirection);
        snakes.forEach(Snake::move);
        snakes.removeIf(snake -> !snake.isAlive());
        snakeAIS.removeIf(snakeAI -> !snakeAI.getSelfSnake().isAlive());

        if (!selfSnake.isAlive()) {
            gameState = GameState.LOST;
        } else if (selfSnake.getSnakeSize() >= maxSnakeSize) {
            gameState = GameState.WON;
        } else {
            if (snakes
                    .stream()
                    .filter(snake -> snake != selfSnake)
                    .anyMatch(snake -> snake.getSnakeSize() >= maxSnakeSize)) {
                gameState = GameState.LOST;
            }
        }
    }
}
