package aoc2018;

import util.Pair;
import util.Point;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class Day11 {

    private static int SIZE = 300;
    private static final int SERIAL_NUMBER = 6392;
    private static int[][] GRID = new int[SIZE][SIZE];

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                GRID[i][j] = calculatePowerLevel(i, j);
            }
        }


        long start = System.currentTimeMillis();

        IntStream.rangeClosed(1, SIZE)
                .parallel()
                .mapToObj(x -> IntStream.rangeClosed(1, SIZE).mapToObj(y -> new Point(x, y)))
                .flatMap(Function.identity())
                .flatMap(point -> IntStream.range(0, SIZE)
                        .mapToObj(n -> new Pair<>(
                                new Pair<>(point, n),
                                calculateGridScore(point, n)
                        ))
                )
                .max(Comparator.comparingInt(Pair::right))
                .ifPresent(System.out::println);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static int calculateGridScore(Point point, int gridSize) {
        if (point.x + gridSize >= SIZE || point.y + gridSize >= SIZE) {
            return Integer.MIN_VALUE;
        }
        return IntStream.range(point.x, point.x + gridSize)
                .flatMap(x -> stream(GRID[x], point.y, point.y + gridSize))
                .sum();
    }

    private static int calculatePowerLevel(int x, int y) {
        int rackId = x + 10;
        return ((rackId * y + SERIAL_NUMBER) * rackId / 100 % 10) - 5;
    }

}
