package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Day06 {

    public static void main(String[] args) throws IOException {
        List<Integer> input = Arrays.stream(Files.readAllLines(Path.of("src/main/resources/2021/day06.txt")).get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        part1(input);
        part2(input);
    }

    private static void part1(List<Integer> input) {
        List<Fish> fish = input.stream()
                .map(Fish::new)
                .collect(Collectors.toList());

        for (int i = 0; i < 80; i++) {
            fish.forEach(Fish::countDown);
            for (Fish fishie : new ArrayList<>(fish)) {
                if (fishie.counter == -1) {
                    fish.add(new Fish());
                    fishie.counter = 6;
                }
            }
        }
        System.out.println(fish.size());
    }

    private static void part2(List<Integer> input) {
        Map<Integer, Long> fishCount = input.stream().collect(toMap(i -> i, i -> 1L, (a, b) -> a + 1L));
        for (int i = 0; i < 256; i++) {
            Long spawn = fishCount.get(0);
            fishCount = fishCount.entrySet().stream()
                    .collect(toMap(
                            entry -> entry.getKey() == 0 ? 6 : entry.getKey() - 1,
                            Map.Entry::getValue,
                            Long::sum
                    ));
            if (spawn != null) fishCount.put(8, spawn);
        }
        System.out.println(fishCount);
        System.out.println(fishCount.values().stream().mapToLong(i -> i).sum());
    }

    private static class Fish {

        private int counter;

        public Fish() {
            counter = 8;
        }

        public Fish(int counter) {
            this.counter = counter;
        }

        public void countDown() {
            counter--;
        }

        @Override
        public String toString() {
            return "" + counter;
        }
    }
}
