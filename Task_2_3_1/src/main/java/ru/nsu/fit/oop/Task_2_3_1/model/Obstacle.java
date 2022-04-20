package ru.nsu.fit.oop.Task_2_3_1.model;

public class Obstacle extends Tile {

    private int x, y;

    public Obstacle(int x, int y) {
        super(x, y);
    }

    public void onCollision(Snake snake, Tile[][] field) {
        snake.die();
    }

}
