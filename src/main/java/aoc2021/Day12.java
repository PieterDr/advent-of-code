package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day12 {

    public static void main(String[] args) throws IOException {
        Map<String, Cave> caves = new HashMap<>();
        Files.readAllLines(Path.of("src/main/resources/2021/day12.txt")).forEach(line -> {
            String[] parts = line.split("-");
            Cave cave1 = caves.getOrDefault(parts[0], new Cave(parts[0]));
            Cave cave2 = caves.getOrDefault(parts[1], new Cave(parts[1]));
            cave1.connections.add(cave2);
            cave2.connections.add(cave1);
            caves.put(parts[0], cave1);
            caves.put(parts[1], cave2);
        });
        Cave start = caves.get("start");
        Cave end = caves.get("end");

        System.out.println(start.goTo(end, new ArrayList<>()));
    }

    private static class Cave {

        private final String name;
        private final boolean small;
        private final List<Cave> connections;

        private Cave(String name) {
            this.name = name;
            this.small = name.equals(name.toLowerCase());
            this.connections = new ArrayList<>();
        }

        public int goTo(Cave end, List<Cave> route) {
            if (!allowedV2(route)) {
                route.add(this);
                return 0;
            }
            route.add(this);
            if (this == end) {
                return 1;
            }
            return connections.stream()
                    .mapToInt(connection -> connection.goTo(end, new ArrayList<>(route)))
                    .sum();
        }

        private boolean allowedV1(List<Cave> route) {
            return !small || !route.contains(this);
        }

        private boolean allowedV2(List<Cave> route) {
            if (name.equals("start") && route.size() != 0) return false;
            Map<String, ArrayList<Cave>> caves = route.stream()
                    .filter(cave -> cave.small)
                    .collect(Collectors.toMap(
                            cave -> cave.name,
                            cave -> new ArrayList<>(List.of(cave)),
                            (l1, l2) -> {
                                l1.addAll(l2);
                                return l1;
                            }
                    ));
            boolean noDuplicateSmallCave = caves
                    .entrySet().stream()
                    .noneMatch(entry -> entry.getValue().size() > 1);
            return !small || noDuplicateSmallCave || !route.contains(this);
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
