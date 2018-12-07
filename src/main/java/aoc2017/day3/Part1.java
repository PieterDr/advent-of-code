package aoc2017.day3;

import java.util.stream.IntStream;

public class Part1 {

    public static void main(String[] args) {
        int input = 347991;
        int root = findSquare(input);
        int radius = (root - 1) / 2;
        int previousSquare = (root - 2) * (root - 2);
        int positionInCircle = input - previousSquare;
        int stepsToSquareCorner = radius * 2;

        System.out.println(calculateSteps(stepsToSquareCorner, positionInCircle, radius));
    }

    private static int calculateSteps(int initialSteps, int positionInCircle, int radius) {
        while(positionInCircle > radius) {
            positionInCircle -= radius;
        }
        return initialSteps - (radius - positionInCircle);
    }

    private static int findSquare(int input) {
        return IntStream.iterate(1, i -> i + 2)
                .filter(i -> i * i > input)
                .findFirst()
                .getAsInt();
    }
}
