package aoc2021;

import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class Day05 {

    public static void main(String[] args) throws IOException {
        List<Vent> input = Files.readAllLines(Path.of("src/main/resources/2021/day05.txt")).stream()
                .map(Vent::from)
                .collect(toList());

        part1(input);
    }

    private static void part1(List<Vent> input) {
        Map<Point, Integer> pointCount = new HashMap<>();
        input.stream()
                .map(Vent::plot)
                .flatMap(Collection::stream)
                .forEach(point -> {
                    Integer value = pointCount.getOrDefault(point, 0);
                    pointCount.put(point, value + 1);
                });
        System.out.println(pointCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .count());
    }

    private static class Vent {

        private final Point start;
        private final Point end;
        private final Point slope;

        private Vent(Point start, Point end) {
            this.start = start;
            this.end = end;
            this.slope = start.slope(end);
        }

        private static Vent from(String line) {
            String[] parts = line.split(" -> ");
            Point start = new Point(parts[0].split(",")[0], parts[0].split(",")[1]);
            Point end = new Point(parts[1].split(",")[0], parts[1].split(",")[1]);
            return new Vent(start, end);
        }

        public List<Point> plot() {
            List<Point> line = new ArrayList<>();
            line.add(start);
            Point next = start.add(slope);
            while (!next.equals(end)) {
                line.add(next);
                next = next.add(slope);
            }
            line.add(end);
            return line;
        }
    }
}
