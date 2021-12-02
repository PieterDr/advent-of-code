package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day02 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/2021/day02.txt"));
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        int x = 0;
        int y = 0;
        for (String line : input) {
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "forward" -> x += Integer.parseInt(parts[1]);
                case "up" -> y -= Integer.parseInt(parts[1]);
                case "down" -> y += Integer.parseInt(parts[1]);
            }
        }
        System.out.println(x * y);
    }

    private static void part2(List<String> input) {
        int x = 0;
        int y = 0;
        int aim = 0;
        for (String line : input) {
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "forward" -> {
                    int value = Integer.parseInt(parts[1]);
                    x += value;
                    y += value * aim;
                }
                case "up" -> aim -= Integer.parseInt(parts[1]);
                case "down" -> aim += Integer.parseInt(parts[1]);
            }
        }
        System.out.println(x * y);
    }
}
