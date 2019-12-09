package aoc2019.intcode;

import org.junit.jupiter.api.Test;

class ComputerTest {

    @Test
    void computerEqualTo8() {
        Computer.run(new long[]{3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8}, 4);
        Computer.run(new long[]{3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8}, 8);
        Computer.run(new long[]{3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8}, 9);

        Computer.run(new long[]{3, 3, 1108, -1, 8, 3, 4, 3, 99}, 4);
        Computer.run(new long[]{3, 3, 1108, -1, 8, 3, 4, 3, 99}, 8);
        Computer.run(new long[]{3, 3, 1108, -1, 8, 3, 4, 3, 99}, 9);
    }

    @Test
    void computerLessThan8() {
        Computer.run(new long[]{3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8}, 4);
        Computer.run(new long[]{3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8}, 8);
        Computer.run(new long[]{3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8}, 9);

        Computer.run(new long[]{3, 3, 1107, -1, 8, 3, 4, 3, 99}, 4);
        Computer.run(new long[]{3, 3, 1107, -1, 8, 3, 4, 3, 99}, 8);
        Computer.run(new long[]{3, 3, 1107, -1, 8, 3, 4, 3, 99}, 9);
    }

    @Test
    void computerNonZero() {
        Computer.run(new long[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9}, -1);
        Computer.run(new long[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9}, 0);
        Computer.run(new long[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9}, 1);
        Computer.run(new long[]{3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}, -1);
        Computer.run(new long[]{3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}, 0);
        Computer.run(new long[]{3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}, 1);
    }
}
