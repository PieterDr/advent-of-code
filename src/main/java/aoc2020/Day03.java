package aoc2020;

import util.Day;
import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day03 implements Day<List<List<Character>>> {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of(Day03.class.getClassLoader().getResource("2020/day03.txt").getPath()));
        parse(input);
        new Day03().run(parse(input));
    }

    private static List<List<Character>> parse(List<String> input) {
        List<List<Character>> forest = new ArrayList<>();
        for (String line : input) {
            ArrayList<Character> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(c);
            };
            forest.add(row);
        }
        return forest;
    }

    @Override
    public String part1(List<List<Character>> input) {
        long trees = getTrees(input, new Point(3, 1));
        return "" + trees;
    }

    @Override
    public String part2(List<List<Character>> input) {
        long result = getTrees(input, new Point(1, 1))
                * getTrees(input, new Point(3, 1))
                * getTrees(input, new Point(5, 1))
                * getTrees(input, new Point(7, 1))
                * getTrees(input, new Point(1, 2));
        return "" + result;
    }

    private long getTrees(List<List<Character>> input, Point slope) {
        Point position = new Point(0, 0);
        int bottom = input.size() - 1;
        long trees = 0;
        while (position.y < bottom) {
            position = position.add(slope);
            List<Character> row = input.get(position.y);
            if (row.get(position.x % row.size()) == '#') trees++;
        }
        return trees;
    }
}
