package ru.nsu.fit.oop.task_2_3_1.model.field;

public enum Level {

    LEVEL_1("level1.txt", 2, 15, 400),
    LEVEL_2("level2.txt", 3, 25, 300),
    LEVEL_3("level3.txt", 5, 30, 200);


    Level(String fileName, int snakeCount, int maxSnakeSize, int deltaTime) {
        this.fileName = fileName;
        this.snakeCount = snakeCount;
        this.maxSnakeSize = maxSnakeSize;
        this.deltaTime = deltaTime;
    }

    private final String fileName;
    private final int snakeCount;
    private final int maxSnakeSize;
    private final int deltaTime;

    public String getFileName() {
        return fileName;
    }

    public int getSnakeCount() {
        return snakeCount;
    }

    public int getMaxSnakeSize() {
        return maxSnakeSize;
    }

    public int getDeltaTime() {
        return deltaTime;
    }
}
