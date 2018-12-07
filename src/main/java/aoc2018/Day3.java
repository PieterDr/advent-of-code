package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day3 {

    private static Map<Point, Integer> pointCountMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day3.txt"));
        for (String line : input) {
            convertToPoints(line).forEach(point -> {
                pointCountMap.merge(point, 1, (o, n) -> o + n);
            });
        }
        part1();
        part2(input);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static List<Point> convertToPoints(String line) {
        String startPoint = line.split(": ")[0].split(" @ ")[1];
        String dimensions = line.split(": ")[1];
        int x = Integer.parseInt(startPoint.split(",")[0]);
        int y = Integer.parseInt(startPoint.split(",")[1]);
        int w = Integer.parseInt(dimensions.split("x")[0]);
        int h = Integer.parseInt(dimensions.split("x")[1]);


        List<Point> points = new ArrayList<>();
        for (int i = x; i < x + w; i++) {
            for (int j = y; j < y + h; j++) {
                points.add(new Point(i, j));
            }
        }
        return points;
    }

    private static void part1() {
        int result = 0;
        for (Integer value : pointCountMap.values()) {
            if (value > 1) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static void part2(List<String> input) {
        for (String line : input) {
            List<Point> points = convertToPoints(line);
            boolean b = true;
            for (Point point : points) {
                if (pointCountMap.get(point) != 1) {
                    b = false;
                }
            }
            if (b) {
                System.out.println(line);
            }
        }
    }

    private static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{x= " + x + ", columnb=" + y + "}";
        }

    }
}
