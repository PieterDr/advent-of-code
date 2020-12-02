package aoc2020;

import util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 implements Day<List<String>> {

    private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]*)");

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of(Day02.class.getClassLoader().getResource("2020/day02.txt").getPath()));
        new Day02().run(input);
    }

    @Override
    public String part1(List<String> input) {
        return "" + input.stream()
                .filter(this::isValid1)
                .count();
    }

    private boolean isValid1(String input) {
        Matcher matcher = PATTERN.matcher(input);
        matcher.matches();
        String password = matcher.group(4);
        long charCount = password.chars()
                .filter(c -> c == matcher.group(3).charAt(0))
                .count();
        return charCount >= Long.parseLong(matcher.group(1)) && charCount <= Long.parseLong(matcher.group(2));
    }

    @Override
    public String part2(List<String> input) {
        return "" + input.stream()
                .filter(this::isValid2)
                .count();
    }

    private boolean isValid2(String input) {
        Matcher matcher = PATTERN.matcher(input);
        matcher.matches();
        String password = matcher.group(4);
        char expectedChar = matcher.group(3).charAt(0);
        return password.charAt(Integer.parseInt(matcher.group(1)) - 1) == expectedChar ^ password.charAt(Integer.parseInt(matcher.group(2)) - 1) == expectedChar;
    }

}
