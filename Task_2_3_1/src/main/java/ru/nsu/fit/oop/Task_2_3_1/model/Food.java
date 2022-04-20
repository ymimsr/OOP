package ru.nsu.fit.oop.Task_2_3_1.model;

public class Food extends Tile {

    private int x, y;
    private int value;

    public Food(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }

    @Override
    public void onCollision(Snake snake, Tile[][] field) {
        field[x][y] = new FreeTile(x, y);
        // TODO: generate new Food
        snake.increaseGrowth(value);
        snake.move();
    }

}
