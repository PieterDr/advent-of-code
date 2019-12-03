package aoc2019;

import util.Day;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.stream;

public class Day02 implements Day<int[]> {

    public static void main(String[] args) throws Exception {
        String[] input = Files.readAllLines(Paths.get("src/main/resources/2019/day02.txt")).get(0).split(",");
        int[] program = stream(input).mapToInt(Integer::parseInt).toArray();
        new Day02().run(program);
    }

    @Override
    public String part1(int[] program) {
        int[] programCopy = new int[program.length];
        System.arraycopy(program, 0, programCopy, 0, program.length);
        programCopy[1] = 12;
        programCopy[2] = 2;
        executeProgram(programCopy);
        return String.valueOf(programCopy[0]);
    }

    @Override
    public String part2(int[] program) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                int[] programCopy = new int[program.length];
                System.arraycopy(program, 0, programCopy, 0, program.length);
                programCopy[1] = noun;
                programCopy[2] = verb;
                executeProgram(programCopy);
                if (programCopy[0] == 19690720) {
                    return String.valueOf(100 * noun + verb);
                }
            }
        }
        throw new RuntimeException("No valid noun and verb found");
    }

    private void executeProgram(int[] program) {
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
