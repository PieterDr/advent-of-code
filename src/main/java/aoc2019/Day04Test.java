package aoc2019;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class Day04Test {

    private Day04 day = new Day04();

    @Test
    void digitsAreInAscendingOrder() {
        Assertions.assertTrue(day.digitsAreInAscendingOrder("112233".toCharArray()));
        Assertions.assertTrue(day.digitsAreInAscendingOrder("111122".toCharArray()));
        Assertions.assertTrue(day.digitsAreInAscendingOrder("123444".toCharArray()));
        Assertions.assertTrue(day.digitsAreInAscendingOrder("123789".toCharArray()));
        Assertions.assertTrue(day.digitsAreInAscendingOrder("111111".toCharArray()));

        Assertions.assertFalse(day.digitsAreInAscendingOrder("223450".toCharArray()));
    }

    @Test
    void containsExactly2EqualConsecutiveDigits() {
        Assertions.assertTrue(day.containsExactly2EqualConsecutiveDigits("112233".toCharArray()));
        Assertions.assertTrue(day.containsExactly2EqualConsecutiveDigits("111122".toCharArray()));

        Assertions.assertFalse(day.containsExactly2EqualConsecutiveDigits("123444".toCharArray()));
        Assertions.assertFalse(day.containsExactly2EqualConsecutiveDigits("124444".toCharArray()));
        Assertions.assertFalse(day.containsExactly2EqualConsecutiveDigits("123789".toCharArray()));
        Assertions.assertFalse(day.containsExactly2EqualConsecutiveDigits("111111".toCharArray()));
    }

}
