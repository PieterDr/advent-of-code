package aoc2019;

import util.Day;
import util.Pair;
import util.Point;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@SuppressWarnings({"SuspiciousNameCombination", "OptionalGetWithoutIsPresent"})
public class Day10 implements Day<List<Point>> {

    public static void main(String[] args) {
        String input = """
                ..#..###....#####....###........#
                .##.##...#.#.......#......##....#
                #..#..##.#..###...##....#......##
                ..####...#..##...####.#.......#.#
                ...#.#.....##...#.####.#.###.#..#
                #..#..##.#.#.####.#.###.#.##.....
                #.##...##.....##.#......#.....##.
                .#..##.##.#..#....#...#...#...##.
                .#..#.....###.#..##.###.##.......
                .##...#..#####.#.#......####.....
                ..##.#.#.#.###..#...#.#..##.#....
                .....#....#....##.####....#......
                .#..##.#.........#..#......###..#
                #.##....#.#..#.#....#.###...#....
                .##...##..#.#.#...###..#.#.#..###
                .#..##..##...##...#.#.#...#..#.#.
                .#..#..##.##...###.##.#......#...
                ...#.....###.....#....#..#....#..
                .#...###..#......#.##.#...#.####.
                ....#.##...##.#...#........#.#...
                ..#.##....#..#.......##.##.....#.
                .#.#....###.#.#.#.#.#............
                #....####.##....#..###.##.#.#..#.
                ......##....#.#.#...#...#..#.....
                ...#.#..####.##.#.........###..##
                .......#....#.##.......#.#.###...
                ...#..#.#.........#...###......#.
                .#.##.#.#.#.#........#.#.##..#...
                .......#.##.#...........#..#.#...
                .####....##..#..##.#.##.##..##...
                .#.#..###.#..#...#....#.###.#..#.
                ............#...#...#.......#.#..
                .........###.#.....#..##..#.##...
                """;
        String[] lines = input.split("\n");
        List<Point> asteroids = IntStream.range(0, lines.length)
                .mapToObj(y -> new Pair<>(lines[y], y))
                .flatMap(pair ->
                        IntStream.range(0, pair.left.length())
                                .filter(x -> pair.left.charAt(x) == '#')
                                .mapToObj(x -> new Point(x, pair.right)))
                .collect(toList());
        new Day10().run(asteroids);
    }

    @Override
    public String part1(List<Point> asteroids) {
        DecimalFormat decimalFormat = new DecimalFormat("#.######");
        List<Integer> list = new ArrayList<>();
        for (Point asteroid : asteroids) {
            int canSee = 0;
            candidate:
            for (Point candidate : asteroids) {
                if (asteroid == candidate) {
                    continue;
                }
                String totalDistance = decimalFormat.format(distance(asteroid, candidate));
                canSee++;
                for (Point other : asteroids) {
                    if (asteroid != other && candidate != other) {
                        double a = distance(asteroid, other);
                        double b = distance(candidate, other);
                        String sumOfDistances = decimalFormat.format(a + b);
                        if (totalDistance.equals(sumOfDistances)) {
                            canSee--;
                            continue candidate;
                        }
                    }
                }
            }
            list.add(canSee);
        }
        return String.valueOf(list.stream().mapToInt(i -> i).max().getAsInt());
    }

    @Override
    public String part2(List<Point> asteroids) {
        Point base = new Point(27, 19);
        Map<Pair, Point> coordMap = new HashMap<>();

        Map<Double, List<Pair<Double, Double>>> collect = asteroids.stream()
                .map(point -> {
                    Point relative = new Point(base.y - point.y, base.x - point.x);
                    Pair<Double, Double> carthesian = carthesian(relative);
                    coordMap.put(carthesian, point);
                    return carthesian;
                })
                .collect(groupingBy(Pair::right));
        collect.values().forEach(list -> list.sort(Comparator.comparingDouble(Pair::left)));
        List<Double> angles = collect.keySet().stream().sorted().collect(toList());
        Pair<Double, Double> key = collect.get(angles.get(199)).get(0);
        return coordMap.get(key).toString();
    }

    private Pair<Double, Double> carthesian(Point point) {
        double theta = Math.atan2(point.y, point.x) * 180 / PI;
        return new Pair<>(
                Math.sqrt(pow(point.x, 2) + pow(point.y, 2)),
                theta > 0 ? 360 - theta : theta < 0 ? abs(theta) : 0
        );
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt(pow(a.x - b.x, 2) + pow(a.y - b.y, 2));
    }
}
