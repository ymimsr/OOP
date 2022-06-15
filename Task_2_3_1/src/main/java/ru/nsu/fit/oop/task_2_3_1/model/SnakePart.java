package ru.nsu.fit.oop.task_2_3_1.model;

public class SnakePart extends Collidable {

    private Snake snake;
    private boolean isHead = false;
    private boolean isTail = false;

    public SnakePart(Tile tile, boolean isHead, boolean isTail) {
        super(tile);
        this.isHead = isHead;
        this.isTail = isTail;
    }

    public boolean isHead() {
        return isHead;
    }

    public boolean isTail() {
        return isTail;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public void setTail(boolean tail) {
        isTail = tail;
    }

    // has to be initialized before onCollision() can be called
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void onCollision(Snake snake) {
        if (this.snake == snake) {
            snake.die();
            return;
        }

        if (isHead) {
            if (
                    snake.getDirection().getDelta()
                            .sumModulo(this.snake.getDirection().getDelta(), new Position(4, 4))
                            .equals(new Position(0, 0))
            ) {
                snake.die();
            }
            this.snake.die();
        } else {
            this.snake.removeSnakePart(this);
        }
    }

}
