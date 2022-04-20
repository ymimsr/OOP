package ru.nsu.fit.oop.Task_2_3_1.model;

public class FreeTile extends Tile {

    public FreeTile(int x, int y) {
        super(x, y);
    }

    @Override
    protected void onCollision(Snake snake, Tile[][] field) {
        snake.move();
    }

}
