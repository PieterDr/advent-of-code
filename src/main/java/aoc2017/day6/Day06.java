package aoc2017.day6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Day06 {

    public static void main(String[] args) {
        String input = "14\t0\t15\t12\t11\t11\t3\t5\t1\t6\t8\t4\t9\t1\t8\t4";
        List<Integer> list = toIntList(input);
        Set<Memory> states = new HashSet<>();
        Memory currentState = new Memory(list);
        int cycles = 0;
        while(states.add(currentState)) {
            currentState = currentState.performRedistribution();
            cycles++;
        }
        states.clear();
        cycles = 0;
        while(states.add(currentState)) {
            currentState = currentState.performRedistribution();
            cycles++;
        }
        System.out.println(cycles);
    }

    private static List<Integer> toIntList(String input) {
        return stream(input.split("\t"))
                .map(Integer::parseInt)
                .collect(toList());
    }
}
