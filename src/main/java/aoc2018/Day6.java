package aoc2018;

import util.Pair;
import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day6 {

    private static Map<Integer, Coordinate> coordinates = new HashMap<>();

    private static int minX = Integer.MAX_VALUE;
    private static int minY = Integer.MAX_VALUE;
    private static int maxX = 0;
    private static int maxY = 0;

    private static Coordinate[][] grid;

    public static void main(String[] args) throws IOException {
        initializeCoordinates();
        initializeDimensions();
        intializeGrid();

//        PART 1
//        labelAllPoints();
//        removeInfiniteAreas();
//        List<Integer> areas = calculateAreaSizes();
//        areas.sort(Comparator.naturalOrder());
//        System.out.println(areas);

        Map<Coordinate, Integer> pointDistanceMap = new HashMap<>();
        for (Coordinate[] points : grid) {
            for (Coordinate point : points) {
                int totalDistanceToAllCoordinates = 0;
                for (Coordinate coordinate : coordinates.values()) {
                    totalDistanceToAllCoordinates += calculateDistance(coordinate, point);
                }
                pointDistanceMap.put(point, totalDistanceToAllCoordinates);
            }
        }

        System.out.println(pointDistanceMap.values().stream()
                .filter(s -> s < 10000)
                .count());


    }

    private static List<Integer> calculateAreaSizes() {
        List<Integer> areas = new ArrayList<>();
        for (Coordinate coordinate : coordinates.values()) {
            int count = 0;
            for (Coordinate[] points : grid) {
                for (Coordinate point : points) {
                    if (point.label == coordinate.label) {
                        count++;
                    }
                }
            }
            areas.add(count);
        }
        return areas;
    }

    private static void removeInfiniteAreas() {
        for (Coordinate[] points : grid) {
            for (Coordinate point : points) {
                if (isAtEdge(point)) {
                    coordinates.remove(point.label);
                }
            }
        }
    }

    private static boolean isAtEdge(Coordinate point) {
        return point.x == minX || point.x == minY || point.y == minY || point.y == maxY;
    }

    private static void labelAllPoints() {
        for (Coordinate[] points : grid) {
            for (Coordinate point : points) {
                List<Pair<Coordinate, Integer>> distances = coordinates.values().stream()
                        .map(coordinate -> new Pair<>(coordinate, calculateDistance(coordinate, point)))
                        .collect(Collectors.toList());
                int lowestDistance = distances.stream()
                        .mapToInt(pair -> pair.right)
                        .min().getAsInt();
                List<Pair<Coordinate, Integer>> lowestDistances = distances.stream()
                        .filter(pair -> pair.right == lowestDistance)
                        .collect(Collectors.toList());
                point.label = lowestDistances.size() == 1 ? lowestDistances.get(0).left.label : -1;
            }
        }
    }

    private static int calculateDistance(Coordinate a, Coordinate b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private static void initializeCoordinates() throws IOException {
        AtomicInteger counter = new AtomicInteger();
        Files.readAllLines(Paths.get("src/main/resources/2018", "day6.txt")).stream()
                .map(line -> new Coordinate(
                        Integer.parseInt(line.split(", ")[0]),
                        Integer.parseInt(line.split(", ")[1]),
                        counter.getAndIncrement()
                ))
                .forEach(coordinate -> coordinates.put(coordinate.label, coordinate));
    }

    private static void intializeGrid() {
        grid = new Coordinate[maxX - minX + 1][maxY - minY + 1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Coordinate(i + minX, j + minY, -1);
            }
        }
    }

    private static void initializeDimensions() {
        for (Coordinate coordinate : coordinates.values()) {
            if (coordinate.x < minX) minX = coordinate.x;
            if (coordinate.y < minY) minY = coordinate.y;
            if (coordinate.x > maxX) maxX = coordinate.x;
            if (coordinate.y > maxY) maxY = coordinate.y;
        }
    }

    private static class Coordinate extends Point {

        int label;

        public Coordinate(int x, int y, int label) {
            super(x, y);
            this.label = label;
        }
    }
}
