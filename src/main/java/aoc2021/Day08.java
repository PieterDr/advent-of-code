package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.primitives.Chars.asList;
import static java.util.stream.Collectors.toMap;

public class Day08 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/2021/day08.txt"));
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        Map<Integer, Integer> collect = input.stream()
                .map(line -> line.split("\\| ")[1])
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .collect(toMap(
                        String::length,
                        part -> 1,
                        Integer::sum
                ));
        System.out.println(collect.get(2) + collect.get(3) + collect.get(4) + collect.get(7));
    }

    private static void part2(List<String> input) {
        System.out.println(input.stream().mapToInt(Day08::solve).sum());
    }

    private static int solve(String input) {
        String[] parts = input.split(" \\| ");
        List<String> patterns = Arrays.stream(parts[0].split(" "))
                .map(Day08::alphabetize)
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        List<String> output = Arrays.stream(parts[1].split(" "))
                .map(Day08::alphabetize)
                .toList();

        String one = patterns.get(0);
        String four = patterns.get(2);
        String seven = patterns.get(1);
        String eight = patterns.get(9);
        List<String> fives = new ArrayList<>(patterns.subList(3, 6));
        List<String> sixes = new ArrayList<>(patterns.subList(6, 9));
        String three = fives.stream().filter(word -> common(word, one) == 2).findFirst().get();
        fives.remove(three);
        String five = fives.stream().filter(word -> common(word, four) == 3).findFirst().get();
        fives.remove(five);
        String two = fives.remove(0);
        String nine = sixes.stream().filter(word -> common(word, three) == 5).findFirst().get();
        sixes.remove(nine);
        String zero = sixes.stream().filter(word -> common(word, one) == 2).findFirst().get();
        sixes.remove(zero);
        String six = sixes.remove(0);

        Map<String, String> map = Map.of(
                zero, "0",
                one, "1",
                two, "2",
                three, "3",
                four, "4",
                five, "5",
                six, "6",
                seven, "7",
                eight, "8",
                nine, "9"
        );

        String result = output.stream()
                .map(map::get)
                .collect(Collectors.joining(""));
        return Integer.parseInt(result);
    }

    private static String alphabetize(String line) {
        return asList(line.toCharArray()).stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    private static long common(String one, String two) {
        return asList(one.toCharArray()).stream()
                .map(String::valueOf)
                .filter(two::contains)
                .count();
    }

}
