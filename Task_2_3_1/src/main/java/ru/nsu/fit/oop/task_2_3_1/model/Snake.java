package ru.nsu.fit.oop.task_2_3_1.model;

import ru.nsu.fit.oop.task_2_3_1.model.field.Field;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    private List<SnakePart> snakeBody = new LinkedList<>();
    private SnakePart head;
    private int snakeSize;
    private boolean isAlive = true;
    private Direction direction = Direction.RIGHT;
    private int growingMoves = 0;
    private final Field field;

    public Snake(Field field, SnakePart head) {
        this.field = field;
        this.head = head;
        snakeBody.add(head);
        snakeSize = 1;
    }

    public void move() {
        // process collision
        Tile newHeadTile = field.getTile(head.getTile().getPosition().sumModulo(direction.getDelta(), new Position(field.getSizeX(), field.getSizeY())));
        if (newHeadTile.hasCollidable())
            newHeadTile.onCollision(this);

        if (!isAlive)
            return;

        // create new head
        SnakePart newHead = new SnakePart(newHeadTile, true, false);
        newHead.setSnake(this);
        newHeadTile.setCollidable(newHead);
        snakeBody.get(0).setHead(false);
        snakeBody.add(0, newHead);
        head = newHead;

        // remove tail if snake is not growing
        if (growingMoves == 0) {
            SnakePart tail = snakeBody.get(snakeSize);
            tail.getTile().setCollidable(null);
            snakeBody.remove(snakeSize);
            snakeBody.get(snakeSize - 1).setTail(true);
            snakeSize--;
        } else {
            growingMoves--;
        }

        snakeSize++;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
        for (SnakePart snakePart : snakeBody) {
            snakePart.getTile().setCollidable(null);
            snakePart.setTile(null);
        }
        snakeBody.clear();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case UP -> this.direction = this.direction == Direction.DOWN ? this.direction : direction;
            case DOWN -> this.direction = this.direction == Direction.UP ? this.direction : direction;
            case RIGHT -> this.direction = this.direction == Direction.LEFT ? this.direction : direction;
            case LEFT -> this.direction = this.direction == Direction.RIGHT ? this.direction : direction;
        }
    }

    public int getSnakeSize() {
        return snakeSize;
    }

    public int getGrowingMoves() {
        return growingMoves;
    }

    public List<SnakePart> getSnakeBody() {
        return snakeBody;
    }

    public void addGrowingMoves(int delta) {
        growingMoves += delta;
    }

    public SnakePart getHead() {
        return head;
    }

}
