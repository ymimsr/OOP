package ru.nsu.fit.oop.task_2_3_1.model;

public record Position(int x, int y) {

    public Position sumModulo(Position delta, Position modulo) {
        int newX = (x + delta.x) >= 0 ? (x + delta.x) % modulo.x : (x + delta.x) + modulo.x;
        int newY = (y + delta.y) >= 0 ? (y + delta.y) % modulo.y : (y + delta.y) + modulo.y;
        return new Position(newX, newY);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position position))
            return false;

        return position.x == x && position.y == y;
    }
}
