package aoc2019;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Day22Test {

    @Test
    void example1() {
        int[] result = new Day22(10)
                .dealWithIncrement(7)
                .dealIntoNewStack()
                .dealIntoNewStack()
                .getDeck();
        assertArrayEquals(new int[]{0, 3, 6, 9, 2, 5, 8, 1, 4, 7}, result);
    }

    @Test
    void example2() {
        int[] result = new Day22(10)
                .cut(6)
                .dealWithIncrement(7)
                .dealIntoNewStack()
                .getDeck();
        assertArrayEquals(new int[]{3, 0, 7, 4, 1, 8, 5, 2, 9, 6}, result);
    }

    @Test
    void example3() {
        int[] result = new Day22(10)
                .dealWithIncrement(7)
                .dealWithIncrement(9)
                .cut(-2)
                .getDeck();
        assertArrayEquals(new int[]{6, 3, 0, 7, 4, 1, 8, 5, 2, 9}, result);
    }

    @Test
    void example4() {
        String input = """
                deal into new stack
                cut -2
                deal with increment 7
                cut 8
                cut -4
                deal with increment 7
                cut 3
                deal with increment 9
                deal with increment 3
                cut -1
                Result: 9 2 5 8 1 4 7 0 3 6
                """;
        assertArrayEquals(new int[]{9, 2, 5, 8, 1, 4, 7, 0, 3, 6}, execute(input, 10));
    }

    @Test
    void part1() {
        String input = """
                cut 3334
                deal into new stack
                deal with increment 4
                cut -342
                deal with increment 30
                cut -980
                deal into new stack
                cut -8829
                deal with increment 10
                cut -7351
                deal with increment 60
                cut -3766
                deal with increment 52
                cut 8530
                deal with increment 35
                cut -6979
                deal with increment 52
                cut -8287
                deal with increment 34
                cut -6400
                deal with increment 24
                deal into new stack
                deal with increment 28
                cut 7385
                deal with increment 32
                cut -1655
                deal with increment 66
                cut -2235
                deal with increment 40
                cut 8121
                deal with increment 71
                cut -2061
                deal with increment 73
                cut 7267
                deal with increment 19
                cut 2821
                deal with increment 16
                cut 7143
                deal into new stack
                deal with increment 31
                cut 695
                deal with increment 26
                cut 9140
                deal with increment 73
                cut -4459
                deal with increment 17
                cut 9476
                deal with increment 70
                cut -9832
                deal with increment 46
                deal into new stack
                deal with increment 62
                cut 6490
                deal with increment 29
                cut 3276
                deal into new stack
                cut 6212
                deal with increment 9
                cut -2826
                deal into new stack
                cut -1018
                deal into new stack
                cut -9257
                deal with increment 39
                cut 4023
                deal with increment 69
                cut -8818
                deal with increment 74
                cut -373
                deal with increment 51
                cut 3274
                deal with increment 38
                cut 1940
                deal into new stack
                cut -3921
                deal with increment 3
                cut -8033
                deal with increment 38
                cut 6568
                deal into new stack
                deal with increment 68
                deal into new stack
                deal with increment 70
                cut -9
                deal with increment 32
                cut -9688
                deal with increment 4
                deal into new stack
                cut -1197
                deal with increment 54
                cut -582
                deal into new stack
                cut -404
                deal into new stack
                cut -8556
                deal with increment 47
                cut 7318
                deal with increment 38
                cut -8758
                deal with increment 48
                """;
        int[] result = execute(input, 10007);
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 2019) {
                System.out.println(i);
                break;
            }
        }
    }

    private int[] execute(String input, int size) {
        Day22 day = new Day22(size);
        stream(input.split("\n"))
                .forEach(line -> {
                    if (line.startsWith("deal into"))
                        day.dealIntoNewStack();
                    if (line.startsWith("deal with"))
                        day.dealWithIncrement(parseInt(line.split(" ")[3]));
                    if (line.startsWith("cut"))
                        day.cut(parseInt(line.split(" ")[1]));
                });
        //System.out.println(Arrays.toString(day.getDeck()));
        return day.getDeck();
    }
}
