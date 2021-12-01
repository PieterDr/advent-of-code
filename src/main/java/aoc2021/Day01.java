package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Day01 {

    public static void main(String[] args) throws IOException {
        List<Integer> input = Files.readAllLines(Path.of("src/main/resources/2021/day01.txt")).stream().map(Integer::parseInt).collect(toList());
        part1(input);
        part2(input);
    }

    private static void part1(List<Integer> input) {
        int result = 0;
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i) > input.get(i - 1)) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static void part2(List<Integer> input) {
        int result = 0;
        for (int i = 0; i < input.size() - 3; i++) {
            int sum1 = input.get(i) + input.get(i + 1) + input.get(i + 2);
            int sum2 = input.get(i+1) + input.get(i + 2) + input.get(i + 3);
            if (sum1 < sum2) {
                result++;
            }
        }
        System.out.println(result);
    }
}
