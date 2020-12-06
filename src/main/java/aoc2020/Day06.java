package aoc2020;

import util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Day06 implements Day<List<String>> {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of(Day06.class.getClassLoader().getResource("2020/day06.txt").getPath()));
        new Day06().run(input);
    }

    @Override
    public String part1(List<String> input) {
        var sum = 0;
        var questions = new HashSet<Character>();
        for (String line : input) {
            if (line.isBlank()) {
                sum += questions.size();
                questions = new HashSet<>();
            }
            for (char c : line.toCharArray()) {
                questions.add(c);
            }
        }
        return "" + sum;
    }

    @Override
    public String part2(List<String> input) {
        var groups = input.stream().collect(Collectors.joining(" ")).split(" {2}");
        var result = 0;
        for (String group : groups) {
            var answers = group.split(" ");
            var groupSize = answers.length;
            var count = 0L;
            for (char c : answers[0].toCharArray()) {
                count = stream(answers)
                        .filter(answer -> answer.contains("" + c))
                        .count();
                if (count == groupSize) {
                    result++;
                }
            }
        }
        return "" + result;
    }
}
