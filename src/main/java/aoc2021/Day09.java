package aoc2021;

import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day09 {

    public static void main(String[] args) throws IOException {
        List<List<Integer>> input = Files.readAllLines(Path.of("src/main/resources/2021/day09.txt")).stream()
                .map(line -> Arrays.stream(line.split(""))
                        .map(Integer::parseInt)
                        .toList())
                .toList();
        List<Point> minimums = part1(input);
        part2(input, minimums);
    }

    private static List<Point> part1(List<List<Integer>> input) {
        List<Point> minimums = new ArrayList<>();
        int result = 0;
        for (int r = 0; r < input.size(); r++) {
            List<Integer> row = input.get(r);
            for (int c = 0; c < row.size(); c++) {
                Integer value = row.get(c);
                List<Integer> neighbours = getNeighbours(input, r, c);
                OptionalInt min = neighbours.stream()
                        .mapToInt(i -> i)
                        .filter(i -> i <= value)
                        .findFirst();
                if (min.isEmpty()) {
                    result += value + 1;
                    minimums.add(new Point(r, c));
                }
            }
        }
        System.out.println(result);
        return minimums;
    }

    private static void part2(List<List<Integer>> input, List<Point> minimums) {
        int result = minimums.stream()
                .map(point -> getBasin(input, point))
                .map(Collection::size)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce((a, b) -> a * b)
                .get();
        System.out.println(result);
    }

    private static Set<Point> getBasin(List<List<Integer>> input, Point minimum) {
        Set<Point> basin = new HashSet<>(List.of(minimum));
        Set<Point> neighbours = new HashSet<>(basin);
        do {
            neighbours = new HashSet<>(neighbours.stream()
                    .flatMap(point -> getNeighbourPoints(input, point.x, point.y).stream())
                    .filter(point -> input.get(point.x).get(point.y) != 9)
                    .filter(point -> !basin.contains(point))
                    .toList());
        } while (basin.addAll(neighbours));
        return basin;
    }

    private static List<Integer> getNeighbours(List<List<Integer>> input, int r, int c) {
        List<Integer> neighbours = new ArrayList<>();
        if (r == 0) {
            neighbours.addAll(List.of(input.get(r + 1).get(c)));
        } else if (r == input.size() - 1) {
            neighbours.addAll(List.of(input.get(r - 1).get(c)));
        } else {
            neighbours.addAll(List.of(
                    input.get(r + 1).get(c),
                    input.get(r - 1).get(c)
            ));
        }
        if (c == 0) {
            neighbours.addAll(List.of(input.get(r).get(c + 1)));
        } else if (c == input.get(r).size() - 1) {
            neighbours.addAll(List.of(input.get(r).get(c - 1)));
        } else {
            neighbours.addAll(List.of(
                    input.get(r).get(c + 1),
                    input.get(r).get(c - 1)
            ));
        }
        return neighbours;
    }

    private static List<Point> getNeighbourPoints(List<List<Integer>> input, int r, int c) {
        List<Point> neighbours = new ArrayList<>();
        if (r == 0) {
            neighbours.addAll(List.of(new Point(r + 1, c)));
        } else if (r == input.size() - 1) {
            neighbours.addAll(List.of(new Point(r - 1, c)));
        } else {
            neighbours.addAll(List.of(
                    new Point(r + 1, c),
                    new Point(r - 1, c)
            ));
        }
        if (c == 0) {
            neighbours.addAll(List.of(new Point(r, c + 1)));
        } else if (c == input.get(r).size() - 1) {
            neighbours.addAll(List.of(new Point(r, c - 1)));
        } else {
            neighbours.addAll(List.of(
                    new Point(r, c + 1),
                    new Point(r, c - 1)
            ));
        }
        return neighbours;
    }

}
