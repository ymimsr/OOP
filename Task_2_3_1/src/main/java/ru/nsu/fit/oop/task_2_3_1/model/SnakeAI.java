package ru.nsu.fit.oop.task_2_3_1.model;

import ru.nsu.fit.oop.task_2_3_1.model.field.Field;

import java.util.*;
import java.util.stream.Collectors;

public class SnakeAI {

    private Game game;
    private Snake selfSnake;
    private Field field;
    private List<Snake> snakes;

    private Deque<Direction> curPath = new ArrayDeque<>();

    public SnakeAI(Game game, Snake selfSnake) {
        this.game = game;
        this.selfSnake = selfSnake;
        this.field = game.getField();
        this.snakes = new ArrayList<>();
        updateSnakes();
    }

    public Snake getSelfSnake() {
        return selfSnake;
    }

    private void updateSnakes() {
        snakes.clear();
        for (Snake curSnake : game.getSnakes()) {
            if (curSnake != selfSnake)
                snakes.add(curSnake);
        }
    }

    public void setDirection() {
        if (curPath.isEmpty())
            safeStrategy();

        selfSnake.setDirection(Objects.requireNonNull(curPath.poll()));
    }

    private void safeStrategy() {
        updateSnakes();
        List<Position> foodPositions = getFoodPositions();
        Map<Position, Deque<Direction>> curPaths = closestFoodInRegardsToOtherSnakes(foodPositions);

        if (!curPaths.isEmpty()) {
            curPath = curPaths.entrySet()
                    .stream()
                    .min(Comparator.comparing(pair -> ((Food) field.getTile(pair.getKey()).getCollidable()).getFoodValue()))
                    .get()
                    .getValue();
        } else {
            curPath.add(Direction.RIGHT);
        }
    }

    private List<Position> getFoodPositions() {
        return field.getFoods()
                .stream()
                .map(food -> food.getTile().getPosition())
                .collect(Collectors.toList());
    }

    private Map<Position, Deque<Direction>> closestFoodInRegardsToOtherSnakes(List<Position> foodPositions) {
        Map<Position, Deque<Direction>> result = new HashMap<>();

        Map<Position, Deque<Direction>> selfFoodPaths = dijkstra(foodPositions, this.selfSnake);
        List<Map<Position, Deque<Direction>>> otherSnakesFoodPaths = new ArrayList<>();
        for (Snake snake : snakes) {
            otherSnakesFoodPaths.add(dijkstra(foodPositions, snake));
        }

        for (Position foodPosition : foodPositions) {
            int minPathSize = otherSnakesFoodPaths
                    .stream()
                    .mapToInt(
                            map -> map.get(foodPosition).size()
                    )
                    .min()
                    .orElse(Integer.MAX_VALUE);
            if (selfFoodPaths.get(foodPosition).size() < minPathSize)
                result.put(foodPosition, selfFoodPaths.get(foodPosition));
        }

        return result;
    }

    /**
     * Finds the shortest paths to all food
     * @param target list of food
     * @return list of paths
     */
    private Map<Position, Deque<Direction>> dijkstra(List<Position> target, Snake curSnake) {
        Position source = curSnake.getHead().getTile().getPosition();
        Map<Position, Integer> distance = new HashMap<>();
        Map<Position, Deque<Direction>> paths = new HashMap<>();
        List<Position> positions = new ArrayList<>();

        for (int x = 0; x < field.getSizeX(); x++) {
            for (int y = 0; y < field.getSizeY(); y++) {
                Position position = new Position(x, y);
                distance.put(position, Integer.MAX_VALUE);
                paths.put(position, new ArrayDeque<>());
                positions.add(position);
            }
        }
        distance.replace(source, 0);

        while (!positions.isEmpty()) {
            Position smallestDistPos = positions
                    .stream()
                    .min(Comparator.comparing(distance::get))
                    .get();
            positions.remove(smallestDistPos);
            Direction direction;
            if (paths.get(smallestDistPos).isEmpty()) {
                direction = curSnake.getDirection();
            } else {
                direction = paths.get(smallestDistPos).getLast();
            }

            for (Map.Entry<Direction, Position> neighbour : getNeighbours(smallestDistPos, direction).entrySet()) {
                if (positions.contains(neighbour.getValue())) {
                    int alternativeDist = distance.get(smallestDistPos) + 1;
                    if (alternativeDist < distance.get(neighbour.getValue())){
                        distance.replace(neighbour.getValue(), alternativeDist);
                        paths.get(neighbour.getValue()).clear();
                        paths.get(neighbour.getValue()).addAll(paths.get(smallestDistPos));
                        paths.get(neighbour.getValue()).add(neighbour.getKey());
                    }
                }
            }
        }

        return paths
                .entrySet()
                .stream()
                .filter(pair -> target.contains(pair.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Direction, Position> getNeighbours(Position position, Direction direction) {
        Map<Direction, Position> neighbours = new HashMap<>();
        switch (direction) {
            case UP -> {
                addNeighbour(neighbours, position, Direction.UP);
                addNeighbour(neighbours, position, Direction.RIGHT);
                addNeighbour(neighbours, position, Direction.LEFT);
            }
            case DOWN -> {
                addNeighbour(neighbours, position, Direction.DOWN);
                addNeighbour(neighbours, position, Direction.RIGHT);
                addNeighbour(neighbours, position, Direction.LEFT);
            }
            case RIGHT -> {
                addNeighbour(neighbours, position, Direction.RIGHT);
                addNeighbour(neighbours, position, Direction.UP);
                addNeighbour(neighbours, position, Direction.DOWN);
            }
            case LEFT -> {
                addNeighbour(neighbours, position, Direction.LEFT);
                addNeighbour(neighbours, position, Direction.UP);
                addNeighbour(neighbours, position, Direction.DOWN);
            }
        }

        return neighbours;
    }

    private void addNeighbour(Map<Direction, Position> neighbours, Position source, Direction nextDirection) {
        Position modulo = new Position(field.getSizeX(), field.getSizeY());
        Position nextPosition = source.sumModulo(nextDirection.getDelta(), modulo);

        if (isSafePosition(nextPosition))
            neighbours.put(nextDirection, nextPosition);
    }

    private boolean isSafePosition(Position position) {
        Collidable collidable = field.getTile(position).getCollidable();
        return !(collidable instanceof Obstacle || collidable instanceof SnakePart);
    }

}
