package aoc2019;

import util.Day;
import util.Direction;
import util.Pair;
import util.Point;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Day03 implements Day<List<String>> {

    public static void main(String[] args) throws Exception {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2019/day03.txt"));
        new Day03().run(input);
    }

    @Override
    public String part1(List<String> input) {
        Point origin = new Point(0, 0);
        Wire wire1 = plotWire(input.get(0));
        Wire wire2 = plotWire(input.get(1));
        int result = wire1.stream()
                .filter(wire2::contains)
                .mapToInt(point -> point.distanceTo(origin))
                .min()
                .getAsInt();
        return String.valueOf(result);
    }

    @Override
    public String part2(List<String> input) {
        Wire wire1 = plotWire(input.get(0));
        Wire wire2 = plotWire(input.get(1));
        int result = wire1.stream()
                .filter(wire2::contains)
                .mapToInt(point -> wire1.indexOf(point) + wire2.indexOf(point))
                .min()
                .getAsInt();
        return String.valueOf(result);
    }

    private Wire plotWire(String input) {
        Wire wire = new Wire();
        stream(input.split(","))
                .map(step -> new Pair<>(Direction.of(step.charAt(0)), Integer.parseInt(step.substring(1))))
                .forEach(step -> IntStream.range(0, step.right).forEach(i -> wire.add(wire.end().apply(step.left))));
        return wire.finished();
    }

    private static class Wire {

        private List<Point> points;
        private Set<Point> pointSet;

        private Wire() {
            this.points = new ArrayList<>();
            points.add(new Point(0, 0));
        }

        private Point end() {
            return points.get(points.size() - 1);
        }

        private void add(Point point) {
            points.add(point);
        }

        public Wire finished() {
            this.points = Collections.unmodifiableList(points);
            this.pointSet = new HashSet<>(points);
            return this;
        }

        private Stream<Point> stream() {
            return points.stream().skip(1);
        }

        public boolean contains(Point point) {
            return pointSet.contains(point);
        }

        public int indexOf(Point point) {
            return points.indexOf(point);
        }
    }
}
