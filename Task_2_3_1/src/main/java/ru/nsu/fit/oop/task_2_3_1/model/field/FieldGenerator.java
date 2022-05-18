package ru.nsu.fit.oop.task_2_3_1.model.field;

import ru.nsu.fit.oop.task_2_3_1.model.Food;
import ru.nsu.fit.oop.task_2_3_1.model.Obstacle;
import ru.nsu.fit.oop.task_2_3_1.model.Position;
import ru.nsu.fit.oop.task_2_3_1.model.Tile;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class FieldGenerator {

    private static final Logger logger = Logger.getLogger(FieldGenerator.class.getName());

    public static Field generateFieldFromFile(String fileName) {
        try (
                InputStream is = FieldGenerator.class.getClassLoader().getResourceAsStream(
                        "ru/nsu/fit/oop/task_2_3_1/model/levels/" + fileName
                );
                Scanner scanner = new Scanner(is)
        ) {
            int sizeX = scanner.nextInt();
            int sizeY = scanner.nextInt();
            int obstacleCount = scanner.nextInt();
            int foodCount = scanner.nextInt();

            Tile[][] tileField = new Tile[sizeX][sizeY];
            Field field = new Field(sizeX, sizeY, obstacleCount, foodCount, tileField);
            for (int k = 0; k < sizeY; k++) {
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                for (int i = 0; i < sizeX; i++) {
                    tileField[i][k] = new Tile(field, new Position(i, k));
                    scanner.useDelimiter("");
                    String symbol = scanner.next();
                    switch (symbol) {
                        // free tile
                        case "+":
                            // do nothing
                            break;
                        // food
                        case "1":
                        case "2":
                        case "3":
                        case "4":
                        case "5":
                        case "6":
                        case "7":
                        case "8":
                        case "9":
                            int foodValue = Integer.parseInt(symbol);
                            Food food = new Food(tileField[i][k], foodValue);
                            tileField[i][k].setCollidable(food);
                            field.getFoods().add(food);
                            break;
                        // obstacle
                        case "-":
                            Obstacle obstacle = new Obstacle(tileField[i][k]);
                            tileField[i][k].setCollidable(obstacle);
                            field.getObstacles().add(obstacle);
                            break;
                        default:
                            logger.severe("Unexpected symbol in field config file, symbol: " + symbol);
                    }
                }
            }

            return field;
        } catch (IOException exception) {
            logger.severe(
                    "Generating field from file failed due to unexpected exception, fileName: " + fileName + "\n"
                    + "Exception message: " + exception.getMessage()
            );

            return null;
        }
    }

    public static Field generateField(int sizeX, int sizeY, int obstacleCount, int foodCount, int[] foodValues) {
        if (obstacleCount + foodCount > sizeX * sizeY) {
            throw new IllegalStateException(
                    "Invalid amount of obstacles and food\n" +
                            "obstacleCount + foodCount = " + (obstacleCount + foodCount) + "\n" +
                            "sizeX * sizeY = " + (sizeX * sizeY)
            );
        }

        Tile[][] tileField = new Tile[sizeX][sizeY];
        Field field = new Field(sizeX, sizeY, obstacleCount, foodCount, tileField);
        for (int i = 0; i < sizeX; i++) {
            for (int k = 0; k < sizeY; k++) {
                tileField[i][k] = new Tile(field, new Position(i, k));
            }
        }

        // generating food
        for (int i = 0; i < foodCount; i++) {
            Position randomPos = randomPosition(sizeX, sizeY);
            if (tileField[randomPos.x()][randomPos.y()].hasCollidable()) {
                i--;
            } else {
                Food food = new Food(tileField[randomPos.x()][randomPos.y()], foodValues[i]);
                tileField[randomPos.x()][randomPos.y()].setCollidable(food);
                field.getFoods().add(food);
            }
        }

        // generating obstacles
        for (int i = 0; i < obstacleCount; i++) {
            Position randomPos = randomPosition(sizeX, sizeY);
            if (tileField[randomPos.x()][randomPos.y()].hasCollidable()) {
                i--;
            } else {
                Obstacle obstacle = new Obstacle(tileField[randomPos.x()][randomPos.y()]);
                tileField[randomPos.x()][randomPos.y()].setCollidable(obstacle);
                field.getObstacles().add(obstacle);
            }
        }

        return field;
    }

    public static Position randomPosition(int maxX, int maxY) {
        int randomX = (int) (Math.random() * maxX);
        int randomY = (int) (Math.random() * maxY);

        return new Position(randomX, randomY);
    }

}
