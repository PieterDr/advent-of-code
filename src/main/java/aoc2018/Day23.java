package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Day23 {

    public static void main(String[] args) throws IOException {
        List<Nanobot> bots = Files.readAllLines(Paths.get("src/main/resources/2018", "day23.txt")).stream()
                .map(Nanobot::from)
                .sorted(Comparator.comparingInt(Nanobot::getRadius).reversed())
                .collect(Collectors.toList());
        Nanobot strongestBot = bots.get(0);
        long amountInRange = bots.stream().filter(strongestBot::isInRange).count();
        System.out.println(amountInRange);
    }

    private static class Nanobot {

        SpaceCoordinate coordinate;
        int radius;

        Nanobot(SpaceCoordinate coordinate, int radius) {
            this.coordinate = coordinate;
            this.radius = radius;
        }

        static Nanobot from(String s) {
            String[] parts = s.substring(5).split(">, r=");
            return new Nanobot(SpaceCoordinate.from(parts[0]), Integer.parseInt(parts[1]));
        }

        int getRadius() {
            return radius;
        }

        boolean isInRange(Nanobot o) {
            return coordinate.manhattanDistanceTo(o.coordinate)  <= radius;
        }
    }

    private static class SpaceCoordinate {

        int x, y, z;

        SpaceCoordinate(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        static SpaceCoordinate from(String s) {
            String[] parts = s.split(",");
            return new SpaceCoordinate(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2])
            );
        }

        int manhattanDistanceTo(SpaceCoordinate o) {
            return abs(x - o.x) + abs(y - o.y) + abs(z - o.z);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpaceCoordinate that = (SpaceCoordinate) o;
            return x == that.x &&
                    y == that.y &&
                    z == that.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            return "(" + x + y + z + ')';
        }
    }
}
