package aoc2019;

import aoc2019.intcode.AsyncComputer;
import aoc2019.intcode.Computer;
import util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Day07 implements Day<int[]> {

    public static void main(String[] args) {
        int[] program = {3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 34, 55, 68, 85, 106, 187, 268, 349, 430, 99999, 3, 9, 1001, 9, 5, 9, 1002, 9, 5, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 1001, 9, 2, 9, 1002, 9, 5, 9, 1001, 9, 2, 9, 4, 9, 99, 3, 9, 101, 3, 9, 9, 102, 3, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 5, 9, 101, 3, 9, 9, 102, 5, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 4, 9, 1001, 9, 2, 9, 102, 3, 9, 9, 101, 3, 9, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 99};
        new Day07().run(program);
    }

    @Override
    public String part1(int[] program) {
        try {
            int maxOutput = Files.readAllLines(Paths.get("src/main/resources/2019/day07_permutations.txt")).stream()
                    .map(line -> stream(line.split(",")).mapToInt(Integer::parseInt).toArray())
                    .mapToInt(phaseSetting -> testPhaseSetting(program, phaseSetting))
                    .max()
                    .getAsInt();
            return String.valueOf(maxOutput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int testPhaseSetting(int[] program, int[] phaseSetting) {
        int ampInput = 0;
        for (int setting : phaseSetting) {
            ampInput = Computer.run(program, setting, ampInput).get(0);
        }
        return ampInput;
    }

    @Override
    public String part2(int[] program) {
        try {
            int maxOutput = Files.readAllLines(Paths.get("src/main/resources/2019/day07_permutations2.txt")).stream()
                    .map(line -> stream(line.split(",")).mapToInt(Integer::parseInt).toArray())
                    .mapToInt(phaseSetting -> testFeedbackLoop(program, phaseSetting))
                    .max()
                    .getAsInt();
            return String.valueOf(maxOutput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer testFeedbackLoop(int[] program, int[] phaseSetting) {
        try {
            ArrayBlockingQueue<Integer> aInput = new ArrayBlockingQueue<>(10, true, asList(phaseSetting[0], 0));
            ArrayBlockingQueue<Integer> bInput = new ArrayBlockingQueue<>(10, true, asList(phaseSetting[1]));
            ArrayBlockingQueue<Integer> cInput = new ArrayBlockingQueue<>(10, true, asList(phaseSetting[2]));
            ArrayBlockingQueue<Integer> dInput = new ArrayBlockingQueue<>(10, true, asList(phaseSetting[3]));
            ArrayBlockingQueue<Integer> eInput = new ArrayBlockingQueue<>(10, true, asList(phaseSetting[4]));
            AsyncComputer a = new AsyncComputer(program, aInput, bInput);
            AsyncComputer b = new AsyncComputer(program, bInput, cInput);
            AsyncComputer c = new AsyncComputer(program, cInput, dInput);
            AsyncComputer d = new AsyncComputer(program, dInput, eInput);
            AsyncComputer e = new AsyncComputer(program, eInput, aInput);
            Stream.of(a, b, c, d, e).map(Thread::new).forEach(Thread::start);
            e.join();
            return aInput.poll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
