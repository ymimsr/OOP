package ru.nsu.fit.oop.task_2_3_1.model;

public class Food extends Collidable {

    private int foodValue;

    public Food(Tile tile, int foodValue) {
        super(tile);
        this.foodValue = foodValue;
    }

    @Override
    public void onCollision(Snake snake) {
        snake.addGrowingMoves(foodValue);
        getTile().getField().respawnFood(this);
    }

}
