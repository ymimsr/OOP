package ru.nsu.fit.oop.task_2_3_1.model;

public abstract class Collidable {

    protected Tile tile;

    public Collidable(Tile tile) {
        this.tile = tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public abstract void onCollision(Snake snake);

}
