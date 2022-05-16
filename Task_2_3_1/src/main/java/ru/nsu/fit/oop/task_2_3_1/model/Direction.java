package ru.nsu.fit.oop.task_2_3_1.model;

public enum Direction {

    UP(new Position(0, -1)),
    DOWN(new Position(0, 1)),
    RIGHT(new Position(1, 0)),
    LEFT(new Position(-1, 0));

    private Position delta;

    Direction(Position delta) {
        this.delta = delta;
    }

    public Position getDelta() {
        return delta;
    }
}
