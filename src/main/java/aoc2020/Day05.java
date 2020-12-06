package aoc2020;

import util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class Day05 implements Day<List<String>> {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of(Day05.class.getClassLoader().getResource("2020/day05.txt").getPath()));
        new Day05().run(input);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public String part1(List<String> input) {
        return "" + input.stream()
                .mapToInt(this::seatId)
                .max()
                .getAsInt();
    }

    @Override
    public String part2(List<String> input) {
        List<Integer> ids = input.stream()
                .map(this::seatId)
                .sorted()
                .collect(toList());
        var previous = ids.get(0);
        for (int i = 1; i < ids.size(); i++) {
            var id = ids.get(i);
            if (id - previous > 1) return "" + (id - 1);
            previous = id;
        }
        return "" + ids;

    }

    private int seatId(String line) {
        var rows = line.substring(0, 7).toCharArray();
        var columns = line.substring(7).toCharArray();

        int row = 0;
        int column = 0;
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == 'B') row += Math.pow(2, 6 - i);
        }
        for (int i = 0; i < columns.length; i++) {
            if (columns[i] == 'R') column += Math.pow(2, 2 - i);
        }
        return row * 8 + column;
    }
}
