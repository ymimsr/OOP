package ru.nsu.fit.oop.task_2_3_1.model;

import ru.nsu.fit.oop.task_2_3_1.model.field.Field;

public class Tile {

    private final Position position;
    private Collidable collidable;
    private Field field;

    public Tile(Field field, Position position) {
        this.position = position;
        this.field = field;
    }

    public void setCollidable(Collidable collidable) {
        if (hasCollidable())
            this.collidable.setTile(null);
        this.collidable = collidable;
    }

    public Position getPosition() {
        return position;
    }

    public void onCollision(Snake snake) {
        collidable.onCollision(snake);
    }

    public boolean hasCollidable() {
        return collidable != null;
    }

    public Field getField() {
        return field;
    }
}
