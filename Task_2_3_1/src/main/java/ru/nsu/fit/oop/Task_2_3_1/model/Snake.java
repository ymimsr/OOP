package ru.nsu.fit.oop.Task_2_3_1.model;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    private List<SnakePart> snakeParts;
    private SnakePart head;

    private boolean alive = true;

    public Snake(SnakePart head) {
        this.head = head;
        snakeParts = new LinkedList<>();
        snakeParts.add(head);
    }

    private int growMoves = 0;
    private Direction direction = Direction.RIGHT;

    public void move() {
        SnakePart newHead;
        switch (direction) {
            case UP -> newHead = new SnakePart(head.x(), head.y() + 1);
            case DOWN -> newHead = new SnakePart(head.x(), head.y() - 1);
            case RIGHT -> newHead = new SnakePart(head.x() + 1, head.y());
            case LEFT -> newHead = new SnakePart(head.x() - 1, head.y());
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }

        changeHead(newHead);
        if (growMoves == 0)
            snakeParts.remove(snakeParts.size() - 1);
        else
            growMoves--;
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    private void changeHead(SnakePart head) {
        this.head = head;
        snakeParts.add(0, head);
    }

    public SnakePart getHead() {
        return head;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int size() {
        return snakeParts.size();
    }

    public void increaseGrowth(int growMovesIncr) {
        this.growMoves += growMovesIncr;
    }
}
