package aoc2021;

import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toSet;

public class Day13 {

    public static void main(String[] args) throws IOException {
        Set<Point> points = Files.readAllLines(Path.of("src/main/resources/2021/day13.txt")).stream()
                .filter(line -> line.contains(","))
                .map(line -> line.split(","))
                .map(parts -> new Point(parts[0], parts[1]))
                .collect(toSet());
        System.out.println(points);

        List<Consumer<Set<Point>>> instructions = Files.readAllLines(Path.of("src/main/resources/2021/day13.txt")).stream()
                .filter(line -> line.contains("fold"))
                .map(line -> {
                    if (line.charAt(11) == 'x') {
                        return (Consumer<Set<Point>>) set -> foldLeft(set, parseInt(line.substring(13)));
                    }
                    if (line.charAt(11) == 'y') {
                        return (Consumer<Set<Point>>) set -> foldUp(set, parseInt(line.substring(13)));
                    }
                    throw new RuntimeException();
                })
                .toList();

        for (Consumer<Set<Point>> instruction : instructions) {
            instruction.accept(points);
            System.out.println(points.size());
        }
        print(points);
    }

    private static void foldUp(Set<Point> points, int fold) {
        List<Point> toFold = points.stream()
                .filter(point -> point.y > fold)
                .toList();
        List<Point> folded = toFold.stream()
                .map(point -> new Point(point.x, fold - (point.y - fold)))
                .toList();
        toFold.forEach(points::remove);
        points.addAll(folded);
    }

    private static void foldLeft(Set<Point> points, int fold) {
        List<Point> toFold = points.stream()
                .filter(point -> point.x > fold)
                .toList();
        List<Point> folded = toFold.stream()
                .map(point -> new Point(fold - (point.x - fold), point.y))
                .toList();
        toFold.forEach(points::remove);
        points.addAll(folded);
    }

    private static void print(Set<Point> points) {
        List<Point> list = points.stream().sorted(Comparator.naturalOrder()).toList();

        int columns = list.stream().mapToInt(Point::getX).max().getAsInt() +1 ;
        int rows = list.stream().mapToInt(Point::getY).max().getAsInt()+1;

        String[][] paper = new String[rows][columns];
        list.forEach(point -> paper[point.y][point.x] = "#");
        for (String[] line : paper) {
            for (String s : line) {
                System.out.print(s != null ? s : " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
