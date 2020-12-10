package aoc2020;

import util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.naturalOrder;

public class Day09 implements Day<Long[]> {

    public static final int PREAMBLE_SIZE = 25;

    public static void main(String[] args) throws IOException {
        Long[] input = Files.readAllLines(Path.of(Day08.class.getClassLoader().getResource("2020/day09.txt").getPath()))
                .stream()
                .map(Long::parseLong)
                .toArray(Long[]::new);
        new Day09().run(input);
    }

    @Override
    public String part1(Long[] input) {
        return "" + findInvalidNumber(input);
    }

    @Override
    public String part2(Long[] input) {
        Long invalid = findInvalidNumber(input);
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i].equals(invalid)) continue;
            List<Long> numbers = new ArrayList<>();
            int j = i;
            while(numbers.stream().mapToLong(Long::longValue).sum() < invalid) {
                numbers.add(input[j--]);
            }
            if (numbers.stream().mapToLong(Long::longValue).sum() == invalid) {
                return "" + (numbers.stream().min(naturalOrder()).get() + numbers.stream().max(naturalOrder()).get());
            }
        }
        throw new RuntimeException();
    }

    private Long findInvalidNumber(Long[] input) {
        for (int i = PREAMBLE_SIZE; i < input.length; i++) {
            boolean success = false;
            Long sum = input[i];
            for (int x = i - PREAMBLE_SIZE; x < i - 1; x++) {
                for (int y = i - (PREAMBLE_SIZE - 1); y < i; y++) {
                    if (input[x] + input[y] == sum) {
                        success = true;
                        break;
                    }
                }
            }
            if (!success) return sum;
        }
        throw new RuntimeException();
    }
}
