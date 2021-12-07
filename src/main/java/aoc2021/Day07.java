package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.math.Quantiles.median;

public class Day07 {

    public static void main(String[] args) throws IOException {
        List<Integer> input = Arrays.stream(Files.readAllLines(Path.of("src/main/resources/2021/day07.txt")).get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        part1(input);
        part2(input);
    }

    private static void part1(List<Integer> input) {
        double median = median().compute(input);
        double fuel = input.stream()
                .mapToDouble(i -> i - median)
                .map(Math::abs)
                .sum();
        System.out.println(fuel);
    }

    private static void part2(List<Integer> input) {
        double trueAvg = input.stream()
                .mapToInt(i -> i)
                .average()
                .getAsDouble();
        System.out.println(trueAvg);
        int avg = (int) Math.floor(trueAvg); // Why do I have to floor() instead of round()? :confused:
        System.out.println(avg);
        double fuel = input.stream()
                .mapToInt(i -> i)
                .map(i -> i - avg)
                .map(Math::abs)
                .map(i -> (i * (i+1))/2)
                .sum();
        System.out.println((int) fuel);
    }

}
