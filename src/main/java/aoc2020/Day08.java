package aoc2020;

import util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day08 implements Day<List<String>> {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of(Day08.class.getClassLoader().getResource("2020/day08.txt").getPath()));
        new Day08().run(input);
    }

    @Override
    public String part1(List<String> input) {
        int i = 0;
        long acc = 0;
        Set<Integer> pointers = new HashSet<>();
        while (pointers.add(i)) {
            String[] instruction = input.get(i).split(" ");
            switch (instruction[0]) {
                case "acc" -> {
                    acc += Long.parseLong(instruction[1]);
                    i++;
                }
                case "jmp" -> i += Integer.parseInt(instruction[1]);
                case "nop" -> i++;
            }
        }
        return "" + acc;
    }

    @Override
    public String part2(List<String> input) {
        Map<Integer, String> permutations = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.startsWith("nop")) permutations.put(i, line.replace("nop", "jmp"));
            if (line.startsWith("jmp")) permutations.put(i, line.replace("jmp", "nop"));
        }
        for (Map.Entry<Integer, String> entry : permutations.entrySet()) {
            List<String> code = new ArrayList<>(input);
            code.set(entry.getKey(), entry.getValue());
            Optional<Long> result = runCode(code);
            if (result.isPresent())
                return "" + result.get();
        }
        return null;
    }

    private Optional<Long> runCode(List<String> code) {
        int i = 0;
        long acc = 0;
        Set<Integer> pointers = new HashSet<>();
        while (pointers.add(i)) {
            if (i == code.size()) {
                return Optional.of(acc);
            }
            String[] instruction = code.get(i).split(" ");
            switch (instruction[0]) {
                case "acc" -> {
                    acc += Long.parseLong(instruction[1]);
                    i++;
                }
                case "jmp" -> i += Integer.parseInt(instruction[1]);
                case "nop" -> i++;
            }
        }
        return Optional.empty();
    }

}
