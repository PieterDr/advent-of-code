package aoc2019;

import util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day06 implements Day<HashMap<String, Day06.Body>> {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2019/day06.txt"));
        HashMap<String, Body> orbits = new HashMap<>();
        input.stream()
                .map(line -> line.split("\\)"))
                .forEach(entry -> {
                    Body center = orbits.computeIfAbsent(entry[0], Body::new);
                    Body satellite = orbits.computeIfAbsent(entry[1], Body::new);
                    satellite.setParent(center);
                });
        new Day06().run(orbits);
    }

    @Override
    public String part1(HashMap<String, Body> orbitMap) {
        long checksum = orbitMap.values().stream()
                .mapToInt(Body::countOrbits)
                .sum();
        return String.valueOf(checksum);
    }

    @Override
    public String part2(HashMap<String, Body> orbitMap) {
        List<Body> myParents = orbitMap.get("YOU").getParents();
        List<Body> santasParents = orbitMap.get("SAN").getParents();
        for (Body parent : myParents) {
            if (santasParents.contains(parent)) {
                return String.valueOf(myParents.indexOf(parent) + santasParents.indexOf(parent));
            }
        }
        throw new RuntimeException("No common parent found");
    }

    static class Body {

        private String name;
        private Body parent;

        public Body(String name) {
            this.name = name;
        }

        void setParent(Body parent) {
            this.parent = parent;
        }

        int countOrbits() {
            Body parent = this.parent;
            int i = 0;
            while (parent != null) {
                i++;
                parent = parent.parent;
            }
            return i;
        }

        List<Body> getParents() {
            List<Body> parents = new ArrayList<>();
            Body parent = this.parent;
            while (parent != null) {
                parents.add(parent);
                parent = parent.parent;
            }
            return parents;
        }
    }
}
