package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day1 {

    public static void main(String[] args) throws IOException {
        List<Integer> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day1.txt"))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        part1(input);
        part2(input);
    }

    private static void part1(List<Integer> input) {
        int sum = input.stream().reduce(0, (i, j) -> i + j);
        System.out.println(sum);
    }

    private static void part2(List<Integer> input) {
        int freq = 0;
        Set<Integer> set = new HashSet<>();
        boolean cont = true;
        while (cont) {
            for (Integer i : input) {
                freq += i;
                if (!set.add(freq)) {
                    cont = false;
                    break;
                }
            }
        }
        System.out.println(freq);
    }
}
