package ru.nsu.fit.oop.Task_2_3_1.model;

public record SnakePart(int x, int y) {
    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }
}
