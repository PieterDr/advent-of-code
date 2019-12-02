package aoc2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Day02 {

    public static void main(String[] args) throws Exception {
        String[] input = Files.readAllLines(Paths.get("src/main/resources/2019/day02.txt")).get(0).split(",");
        part1(input);
        System.out.println(part2(input));
    }

    private static void part1(String[] input) throws IOException {
        int[] program = stream(input).mapToInt(Integer::parseInt).toArray();
        program[1] = 12;
        program[2] = 2;
        executeProgram(program);
    }

    private static int part2(String[] input) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                int[] program = stream(input).mapToInt(Integer::parseInt).toArray();
                program[1] = noun;
                program[2] = verb;
                try {
                    executeProgram(program);
                } catch (IOException e) {
                    System.out.println("Invalid program for noun " + noun + " and verb " + verb);
                }
                if (program[0] == 19690720) {
                    return 100 * noun + verb;
                }
            }
        }
        throw new RuntimeException("No valid noun and verb found");
    }

    private static void executeProgram(int[] program) throws IOException {
        int i = 0;
        System.out.println("Start: " + Arrays.toString(program));
        while (program[i] != 99) {
            switch (program[i]) {
                case 1:
                    program[program[i + 3]] = program[program[i + 1]] + program[program[i + 2]];
                    break;
                case 2:
                    program[program[i + 3]] = program[program[i + 1]] * program[program[i + 2]];
                    break;
                default:
                    throw new IOException("Invalid opcode: " + program[i]);
            }
            i += 4;
        }
        System.out.println("End: " + Arrays.toString(program));
    }
}
