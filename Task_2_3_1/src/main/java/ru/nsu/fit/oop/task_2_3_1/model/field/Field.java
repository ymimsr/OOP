package ru.nsu.fit.oop.task_2_3_1.model.field;

import ru.nsu.fit.oop.task_2_3_1.model.*;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private int sizeX, sizeY;
    private int obstacleCount;
    private int foodCount;

    private Tile[][] field;
    private List<Food> foods = new ArrayList<>();
    private List<Obstacle> obstacles = new ArrayList<>();

    protected Field(int sizeX, int sizeY, int obstacleCount, int foodCount, Tile[][] field) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.obstacleCount = obstacleCount;
        this.foodCount = foodCount;
        this.field = field;
    }

    public Tile getTile(Position position) {
        return field[position.x()][position.y()];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getObstacleCount() {
        return obstacleCount;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public Tile[][] getTileField() {
        return field;
    }

    public void respawnFood(Food food) {
        Position prevPosition = food.getTile().getPosition();
        food.getTile().setCollidable(null);
        while (true) {
            Position position = FieldGenerator.randomPosition(sizeX, sizeY);
            Tile tile = getTile(position);
            if (!tile.hasCollidable() && !prevPosition.equals(position)) {
                food.setTile(tile);
                tile.setCollidable(food);
                break;
            }
        }
    }

    public Snake spawnSnake() {
        while (true) {
            Position position = FieldGenerator.randomPosition(sizeX, sizeY);
            Tile tile = getTile(position);
            if (!tile.hasCollidable()) {
                SnakePart head = new SnakePart(tile, true, true);
                tile.setCollidable(head);
                Snake snake = new Snake(this, head);
                head.setSnake(snake);
                return snake;
            }
        }
    }

}
