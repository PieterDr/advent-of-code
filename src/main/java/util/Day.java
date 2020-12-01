package util;

public interface Day<T> {

    default void run(T input) {
        // JVM warm-up
        part1(input);
        part2(input);

        long start = System.currentTimeMillis();

        System.out.println(String.format("Part 1 result: %s", part1(input)));
        long part1 = System.currentTimeMillis();
        System.out.println("Part 1 duration (ms): " + (part1 - start));

        System.out.println();

        System.out.println(String.format("Part 2 result: %s", part2(input)));
        long end = System.currentTimeMillis();
        System.out.println("Part 2 duration (ms): " + (end - part1));
    }

    String part1(T input);
    String part2(T input);
}
