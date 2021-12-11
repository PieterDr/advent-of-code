package aoc2021;

import util.Pair;
import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day11 {

    private static Map<Point, Octopus> octopi = new HashMap<>();

    public static void main(String[] args) throws IOException {
        List<List<Integer>> input = Files.readAllLines(Path.of("src/main/resources/2021/day11.txt")).stream()
                .map(line -> Arrays.stream(line.split(""))
                        .map(Integer::parseInt)
                        .toList())
                .toList();
        for (int x = 0; x < input.size(); x++) {
            List<Integer> row = input.get(x);
            for (int y = 0; y < row.size(); y++) {
                Point point = new Point(x, y);
                octopi.put(point, new Octopus(row.get(y), point));
            }
        }
//        part1();
        part2();
    }

    private static void part1() {
        int flashes = 0;
        for (int i = 1; i <= 100; i++) {
            for (Point point : Point.generate(10,10).toList()) {
                Octopus octopus = octopi.get(point);
                flashes += octopus.flash();
            }
            for (Octopus octopus : octopi.values()) {
                octopus.flashed = false;
            }
            print(i, octopi);
        }
        System.out.println(flashes);
    }

    private static void part2() {
        Pair<Integer, Integer> step = IntStream.iterate(1, i -> i + 1)
                .mapToObj(i -> {
                    int flashes = 0;
                    for (Point point : Point.generate(10, 10).toList()) {
                        Octopus octopus = octopi.get(point);
                        flashes += octopus.flash();
                    }
                    for (Octopus octopus : octopi.values()) {
                        octopus.flashed = false;
                    }
                    return new Pair<>(i, flashes);
                })
                .filter(pair -> pair.right == octopi.values().size())
                .findFirst()
                .get();
        System.out.println(step);
    }

    private static void print(int step, Map<Point, Octopus> octopi) {
        System.out.println("STEP " + step);
        int[][] grid = new int[10][10];
        octopi.entrySet().forEach(entry -> grid[entry.getKey().x][entry.getKey().y] = entry.getValue().energy);
        for (int[] ints : grid) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static class Octopus {

        private int energy;
        private boolean flashed;
        private final Point point;
        private final List<Point> neighbours;

        Octopus(int energy, Point point) {
            this.energy = energy;
            this.flashed = false;
            this.point = point;
            this.neighbours = point.neighbours(true);
        }

        public int flash() {
            if (!flashed) {
                energy++;
            }
            if (energy > 9) {
                energy = 0;
                flashed = true;
                return 1 + neighbours.stream()
                        .map(octopi::get)
                        .filter(Objects::nonNull)
                        .mapToInt(Octopus::flash)
                        .sum();
            }
            return 0;
        }
    }

}
