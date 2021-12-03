package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Day03 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/2021/day03.txt"));
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int i = 0; i < input.get(0).length(); i++) {
            Map<Character, Integer> bitCount = countBits(input, i);
            if (bitCount.get('0') < bitCount.get('1')) {
                gamma.append(1);
                epsilon.append(0);
            } else {
                gamma.append(0);
                epsilon.append(1);
            }
        }
        System.out.println(parseInt(gamma.toString(), 2) * parseInt(epsilon.toString(), 2));
    }

    private static void part2(List<String> input) {
        int oxygen = getOxygen(new ArrayList<>(input));
        int co2 = getCO2(new ArrayList<>(input));
        System.out.println(oxygen);
        System.out.println(co2);
        System.out.println(oxygen * co2);
    }

    private static int getOxygen(List<String> input) {
        int length = input.get(0).length();
        for (int i = 0; i < length; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Map<Character, Integer> bitCount = countBits(input, i);
            if (bitCount.get('1') >= bitCount.get('0')) {
                input = input.stream()
                        .filter(line -> line.charAt(index.get()) == '1')
                        .collect(Collectors.toList());
            } else {
                input = input.stream()
                        .filter(line -> line.charAt(index.get()) == '0')
                        .collect(Collectors.toList());
            }
            if (input.size() == 1) {
                return parseInt(input.get(0), 2);
            }
        }
        throw new RuntimeException();
    }

    private static int getCO2(List<String> input) {
        int length = input.get(0).length();
        for (int i = 0; i < length; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Map<Character, Integer> bitCount = countBits(input, i);
            if (bitCount.get('0') <= bitCount.get('1')) {
                input = input.stream()
                        .filter(line -> line.charAt(index.get()) == '0')
                        .collect(Collectors.toList());
            } else {
                input = input.stream()
                        .filter(line -> line.charAt(index.get()) == '1')
                        .collect(Collectors.toList());
            }
            if (input.size() == 1) {
                return parseInt(input.get(0), 2);
            }
        }
        throw new RuntimeException();
    }

    private static Map<Character, Integer> countBits(List<String> input, int i) {
        Map<Character, Integer> bitCount = new HashMap<>(Map.of('0', 0, '1', 0));
        for (String line : input) {
            Character bit = line.charAt(i);
            bitCount.computeIfPresent(bit, (b, count) -> ++count);
        }
        return bitCount;
    }
}
