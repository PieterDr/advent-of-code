package aoc2019;

import util.Day;
import util.Direction;
import util.Point;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Day03 implements Day<List<String>> {

    private final Point ORIGIN = new Point(0, 0);

    public static void main(String[] args) throws Exception {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2019/day03.txt"));
        new Day03().run(input);
    }

    @Override
    public String part1(List<String> input) {
        Set<Point> wire1 = new HashSet<>(plotWire(input.get(0)));
        Set<Point> wire2 = new HashSet<>(plotWire(input.get(1)));
        int result = wire1.stream()
                .filter(point -> !point.equals(ORIGIN))
                .filter(wire2::contains)
                .mapToInt(point -> point.distanceTo(ORIGIN))
                .min()
                .getAsInt();
        return String.valueOf(result);
    }

    @Override
    public String part2(List<String> input) {
        List<Point> wire1 = plotWire(input.get(0));
        List<Point> wire2 = plotWire(input.get(1));
        Set<Point> wire2Points = new HashSet<>(wire2);
        int result = wire1.stream()
                .filter(point -> !point.equals(ORIGIN))
                .filter(wire2Points::contains)
                .mapToInt(point -> wire1.indexOf(point) + wire2.indexOf(point))
                .min()
                .getAsInt();
        return String.valueOf(result);
    }

    private List<Point> plotWire(String input) {
        List<Point> wire = new ArrayList<>();
        Point end = ORIGIN;
        wire.add(end);
        for (String step : input.split(",")) {
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
