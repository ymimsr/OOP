package ru.nsu.fit.oop.task_2_3_1.model;

import ru.nsu.fit.oop.task_2_3_1.model.field.Field;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SnakeAI implements Runnable {

    private Game game;
    private Snake snake;

    public SnakeAI(Game game, Snake snake) {
        this.game = game;
        this.snake = snake;
    }

    @Override
    public void run() {

    }

    private List<Direction> closestFoodInRegardsToOtherSnakes() {
        List<Direction> path = new LinkedList<>();

    }

    private Map<Integer, List<Pair<Food, List<Direction>>>> dijkstra(Snake snake, List<Food> foods) {
        Map<Integer, List<Pair<Food, List<Direction>>>> targetPaths = new TreeMap<>();
        Map<Integer, List<Pair<Tile, List<Direction>>>> allPaths = new TreeMap<>();
        Field field = game.getField();

        for (int i = 0; i < field.getSizeX(); i++) {
            for (int k = 0; k < field.getSizeY(); k++) {
                allPaths.put(Integer.MAX_VALUE, new LinkedList<>());
            }
        }
        allPaths.put(I)

        for ()

        return paths;
    }

    private static class Pair<K, V> {
        K l;
        V r;

        public Pair(K l, V r) {
            this.l = l;
            this.r = r;
        }

        public K left() {
            return l;
        }

        public V right() {
            return r;
        }

    }

}
