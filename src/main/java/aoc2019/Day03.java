package aoc2019;

import util.Direction;
import util.Point;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Day03 {

    private static final Point ORIGIN = new Point(0, 0);

    public static void main(String[] args) throws Exception {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2019/day03.txt"));
        // JVM warm-up
        part1(input.get(0), input.get(1));
        part2(input.get(0), input.get(1));

        long start = System.currentTimeMillis();
        System.out.println(part1(input.get(0), input.get(1)));
        long part1 = System.currentTimeMillis();
        System.out.println(part2(input.get(0), input.get(1)));
        long end = System.currentTimeMillis();

        System.out.println("Part 1 duration (ms): " + (part1 - start));
        System.out.println("Part 2 duration (ms): " + (end - part1));
    }

    private static int part1(String input1, String input2) {
        Set<Point> wire1 = new HashSet<>(plotWire(input1));
        Set<Point> wire2 = new HashSet<>(plotWire(input2));
        return wire1.stream()
                .filter(point -> !point.equals(ORIGIN))
                .filter(wire2::contains)
                .mapToInt(point -> point.distanceTo(ORIGIN))
                .min()
                .getAsInt();
    }

    private static int part2(String input1, String input2) {
        List<Point> wire1 = plotWire(input1);
        List<Point> wire2 = plotWire(input2);
        Set<Point> wire2Points = new HashSet<>(wire2);
        return wire1.stream()
                .filter(point -> !point.equals(ORIGIN))
                .filter(wire2Points::contains)
                .mapToInt(point -> wire1.indexOf(point) + wire2.indexOf(point))
                .min()
                .getAsInt();
    }

    private static List<Point> plotWire(String input) {
        List<Point> wire = new ArrayList<>();
        Point end = ORIGIN;
        wire.add(end);
        for (String step: input.split(",")) {
            Direction direction = Direction.of(step.charAt(0));
            int n = Integer.parseInt(step.substring(1));
            for (int i = 0; i < n; i++) {
                Point next = end.apply(direction);
                wire.add(next);
                end = next;
            }
        }
        return wire;
    }
}
