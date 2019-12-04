package aoc2019;

import util.Day;
import util.Pair;

import java.nio.CharBuffer;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Day04 implements Day<Pair<Integer, Integer>> {

    public static void main(String[] args) {
        new Day04().run(new Pair<>(246540, 787419));
    }

    @Override
    public String part1(Pair<Integer, Integer> input) {
        long count = IntStream.rangeClosed(input.left, input.right)
                .mapToObj(i -> String.valueOf(i).toCharArray())
                .filter(this::digitsAreInAscendingOrder)
                .filter(this::containsAtLeast2EqualConsecutiveDigits)
                .count();
        return String.valueOf(count);
    }

    @Override
    public String part2(Pair<Integer, Integer> input) {
        long count = IntStream.rangeClosed(input.left, input.right)
                .mapToObj(i -> String.valueOf(i).toCharArray())
                .filter(this::digitsAreInAscendingOrder)
                .filter(this::containsExactly2EqualConsecutiveDigits)
                .count();
        return String.valueOf(count);
    }

    boolean digitsAreInAscendingOrder(char[] chars) {
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] > chars[i]) {
                return false;
            }
        }
        return true;
    }

    boolean containsAtLeast2EqualConsecutiveDigits(char[] chars) {
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i]) {
                return true;
            }
        }
        return false;
    }

    boolean containsExactly2EqualConsecutiveDigits(char[] chars) {
        return CharBuffer.wrap(chars).chars()
                .boxed()
                .collect(groupingBy(identity(), counting()))
                .containsValue(2L);
    }
}
