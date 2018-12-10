package aoc2018;

import util.Pair;
import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class Day10 {

    private static final Pattern PATTERN = Pattern.compile("<(.*)>.*<(.*)>");

    public static void main(String[] args) throws IOException {
        List<Pair<Point, Point>> input = parse();
        int i = 0;
        while (calculateHeight(input) > 10) {
            input = step(input);
            i++;
        }
        print(input);
        System.out.println(i);
    }

    private static List<Pair<Point, Point>> parse() throws IOException {
        return Files.readAllLines(Paths.get("src/main/resources/2018", "day10.txt")).stream()
                .map(PATTERN::matcher)
                .map(matcher -> {
                    matcher.find();
                    String position = matcher.group(1);
                    String velocity = matcher.group(2);
                    return new Pair<>(
                            new Point(position.split(",")[0], position.split(",")[1]),
                            new Point(velocity.split(",")[0], velocity.split(",")[1])
                    );
                })
                .collect(toList());
    }

    private static int calculateHeight(List<Pair<Point, Point>> input) {
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Pair<Point, Point> point : input) {
            Point location = point.left;
            if (location.y < minY) minY = location.y;
            if (location.y > maxY) maxY = location.y;
        }
        return maxY - minY;
    }

    private static List<Pair<Point, Point>> step(List<Pair<Point, Point>> input) {
        return input.stream()
                .map(point -> new Pair<>(point.left.add(point.right), point.right))
                .collect(toList());
    }

    private static void print(List<Pair<Point, Point>> input) {
        char[][] grid = new char[200][200];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = '.';
            }
        }
        input.stream().map(Pair::left).forEach(point -> grid[point.y][point.x] = '#');
        for (char[] chars : grid) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

}
