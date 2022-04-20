package ru.nsu.fit.oop.Task_2_3_1.model;

public abstract class Tile {

    private int x, y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // TODO: remake onCollision contract (it shouldn't take field as a parameter)
    protected abstract void onCollision(Snake snake, Tile[][] field);

}
