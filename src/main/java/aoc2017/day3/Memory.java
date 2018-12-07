package aoc2017.day3;

import java.util.HashMap;
import java.util.Map;

class Memory {

    static Map<Coordinate, Integer> MEMORY = new HashMap<>();

    void addCoordinateWithValue(Coordinate coordinate, int value) {
        MEMORY.put(coordinate, value);
    }

    Coordinate generateNext(Coordinate lastCoordinate, Direction direction) {
        int x = lastCoordinate.x;
        int y = lastCoordinate.y;
        switch (direction) {
            case UP:
                y++;
                break;
            case DOWN:
                y--;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        Coordinate next = new Coordinate(x, y);
        MEMORY.put(next, calculateValueFor(next));
        return next;
    }

    private int calculateValueFor(Coordinate coordinate) {
        return MEMORY.getOrDefault(new Coordinate(coordinate.x - 1, coordinate.y), 0) +
                MEMORY.getOrDefault(new Coordinate(coordinate.x - 1, coordinate.y + 1), 0) +
                MEMORY.getOrDefault(new Coordinate(coordinate.x - 1, coordinate.y - 1), 0) +
                MEMORY.getOrDefault(new Coordinate(coordinate.x, coordinate.y + 1), 0) +
                MEMORY.getOrDefault(new Coordinate(coordinate.x, coordinate.y - 1), 0) +
                MEMORY.getOrDefault(new Coordinate(coordinate.x + 1, coordinate.y), 0) +
                MEMORY.getOrDefault(new Coordinate(coordinate.x + 1, coordinate.y + 1), 0) +
                MEMORY.getOrDefault(new Coordinate(coordinate.x + 1, coordinate.y - 1), 0);
    }
}
