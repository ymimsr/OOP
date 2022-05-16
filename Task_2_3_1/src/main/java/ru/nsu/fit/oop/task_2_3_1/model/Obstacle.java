package ru.nsu.fit.oop.task_2_3_1.model;

import ru.nsu.fit.oop.task_2_3_1.model.Collidable;
import ru.nsu.fit.oop.task_2_3_1.model.Snake;
import ru.nsu.fit.oop.task_2_3_1.model.Tile;

public class Obstacle extends Collidable {

    public Obstacle(Tile tile) {
        super(tile);
    }

    @Override
    public void onCollision(Snake snake) {
        snake.die();
    }

}
