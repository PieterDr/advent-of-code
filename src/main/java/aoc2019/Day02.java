package aoc2019;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.stream;

public class Day02 {

    public static void main(String[] args) throws Exception {
        String[] input = Files.readAllLines(Paths.get("src/main/resources/2019/day02.txt")).get(0).split(",");
        int[] program = stream(input).mapToInt(Integer::parseInt).toArray();
        // JVM warm-up
        part1(program);
        part2(program);

        long start = System.currentTimeMillis();
        System.out.println(part1(program));
        long part1 = System.currentTimeMillis();
        System.out.println(part2(program));
        long end = System.currentTimeMillis();
        System.out.println("Part 1 duration (ms): " + (part1 - start));
        System.out.println("Part 2 duration (ms): " + (end - part1));
    }

    private static int part1(int[] program) {
        int[] programCopy = new int[program.length];
        System.arraycopy(program, 0, programCopy, 0, program.length);
        programCopy[1] = 12;
        programCopy[2] = 2;
        executeProgram(programCopy);
        return programCopy[0];
    }

    private static int part2(int[] program) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                int[] programCopy = new int[program.length];
                System.arraycopy(program, 0, programCopy, 0, program.length);
                programCopy[1] = noun;
                programCopy[2] = verb;
                executeProgram(programCopy);
                if (programCopy[0] == 19690720) {
                    return 100 * noun + verb;
                }
            }
        }
        throw new RuntimeException("No valid noun and verb found");
    }

    private static void executeProgram(int[] program) {
        int i = 0;
        while (true) {
            switch (program[i]) {
                case 1:
                    program[program[i + 3]] = program[program[i + 1]] + program[program[i + 2]];
                    break;
                case 2:
                    program[program[i + 3]] = program[program[i + 1]] * program[program[i + 2]];
                    break;
                case 99:
                    return;
                default:
                    throw new RuntimeException("Invalid opcode: " + program[i]);
            }
            i += 4;
        }
    }
}
