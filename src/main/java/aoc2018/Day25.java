package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.toList;

public class Day25 {

    private static List<Constellation> CONSTELLATIONS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Files.readAllLines(Paths.get("src/main/resources/2018", "day25.txt")).stream()
                .map(SpaceTimeCoordinate::from)
                .forEach(coordinate -> {
                    List<Constellation> constellations = CONSTELLATIONS.stream()
                            .filter(constellation -> constellation.canJoin(coordinate))
                            .collect(toList());
                    CONSTELLATIONS.removeAll(constellations);
                    CONSTELLATIONS.add(merge(coordinate, constellations));
                });
        System.out.println(CONSTELLATIONS.size());
    }

    private static Constellation merge(SpaceTimeCoordinate coordinate, List<Constellation> constellations) {
        if (constellations.isEmpty()) {
            return new Constellation(coordinate);
        } else if (constellations.size() == 1) {
            return constellations.get(0).join(coordinate);
        } else {
            Constellation second = constellations.remove(1);
            Constellation first = constellations.remove(0);
            constellations.add(first.merge(second));
            return merge(coordinate, constellations);
        }
    }

    private static class Constellation {

        private List<SpaceTimeCoordinate> coordinates = new ArrayList<>();

        Constellation(SpaceTimeCoordinate coordinate) {
            coordinates.add(coordinate);
        }

        @SafeVarargs
        private Constellation(List<SpaceTimeCoordinate>... coordinates) {
            Arrays.stream(coordinates).flatMap(Collection::stream).forEach(this.coordinates::add);
        }

        boolean canJoin(SpaceTimeCoordinate coordinate) {
            return coordinates.stream().anyMatch(coord -> coord.manhattanDistanceTo(coordinate) <= 3);
        }

        Constellation join(SpaceTimeCoordinate coordinate) {
            coordinates.add(coordinate);
            return this;
        }

        Constellation merge(Constellation second) {
            return new Constellation(coordinates, second.coordinates);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Constellation that = (Constellation) o;
            return Objects.equals(coordinates, that.coordinates);
        }

        @Override
        public int hashCode() {
            return Objects.hash(coordinates);
        }
    }

    private static class SpaceTimeCoordinate {

        private int x, y, z, t;

        SpaceTimeCoordinate(int x, int y, int z, int t) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.t = t;
        }


        static SpaceTimeCoordinate from(String s) {
            String[] parts = s.split(",");
            return new SpaceTimeCoordinate(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3])
            );
        }

        int manhattanDistanceTo(SpaceTimeCoordinate other) {
            return abs(x - other.x) + abs(y - other.y) + abs(z - other.z) + abs(t - other.t);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + "," + z + "," + t + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpaceTimeCoordinate that = (SpaceTimeCoordinate) o;
            return x == that.x &&
                    y == that.y &&
                    z == that.z &&
                    t == that.t;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, t);
        }
    }
}
