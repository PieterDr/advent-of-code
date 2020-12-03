package aoc2020;

import util.Day;
import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day03 implements Day<List<char[]>> {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of(Day03.class.getClassLoader().getResource("2020/day03.txt").getPath()));
        new Day03().run(parse(input));
    }

    private static List<char[]> parse(List<String> input) {
        return input.stream()
                .map(String::toCharArray)
                .collect(Collectors.toList());
    }

    @Override
    public String part1(List<char[]> input) {
        long trees = getTrees(input, new Point(3, 1));
        return "" + trees;
    }

    @Override
    public String part2(List<char[]> input) {
        long result = getTrees(input, new Point(1, 1))
                * getTrees(input, new Point(3, 1))
                * getTrees(input, new Point(5, 1))
                * getTrees(input, new Point(7, 1))
                * getTrees(input, new Point(1, 2));
        return "" + result;
    }

    private long getTrees(List<char[]> input, Point slope) {
        return IntStream.range(1, input.size())
                .mapToObj(slope::multiply)
                .filter(position -> position.y < input.size())
                .filter(position -> {
                    char[] row = input.get(position.y);
                    return row[position.x % row.length] == '#';
                })
                .count();
    }
}
