package aoc2019;

import com.google.common.collect.Sets;
import util.Pair;
import util.Point3D;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Day12 {

    static int part1(List<Moon> moons, int iterations) {
        Set<Moon> moonSet = new HashSet<>(moons);
        for (int i = 1; i <= iterations; i++) {
            simulate(moonSet);
        }
        return moonSet.stream().mapToInt(Moon::energy).sum();
    }

    static long part2(List<Moon> moons) {
        List<Integer> initX = moons.stream().map(moon -> moon.position.x).collect(toList());
        List<Integer> initY = moons.stream().map(moon -> moon.position.y).collect(toList());
        List<Integer> initZ = moons.stream().map(moon -> moon.position.z).collect(toList());
        List<Integer> noVelocity = asList(0, 0, 0, 0);

        long stepsX = 0;
        long stepsY = 0;
        long stepsZ = 0;
        long steps = 0;

        Set<Moon> moonSet = new HashSet<>(moons);
        while (stepsX == 0 || stepsY == 0 || stepsZ == 0) {
            steps++;
            simulate(moonSet);
            if (stepsX == 0 && initX.equals(moons.stream().map(moon -> moon.position.x).collect(toList()))
                    && noVelocity.equals(moons.stream().map(moon -> moon.velocity.x).collect(toList())))
                stepsX = steps;
            if (stepsY == 0 && initY.equals(moons.stream().map(moon -> moon.position.y).collect(toList()))
                    && noVelocity.equals(moons.stream().map(moon -> moon.velocity.y).collect(toList())))
                stepsY = steps;
            if (stepsZ == 0 && initZ.equals(moons.stream().map(moon -> moon.position.z).collect(toList()))
                    && noVelocity.equals(moons.stream().map(moon -> moon.velocity.z).collect(toList())))
                stepsZ = steps;
        }
        long lcmXY = lcm(stepsX, stepsY);
        return lcm(lcmXY, stepsZ);
    }

    private static void simulate(Set<Moon> moons) {
        moons.stream()
                .map(moon -> new Pair<>(moon, moon.calculateNewVelocity(Sets.difference(moons, Sets.newHashSet(moon)))))
                .collect(toMap(Pair::left, Pair::right))
                .entrySet()
                .forEach(entry -> {
                    entry.getKey().apply(entry.getValue());
                });
    }

    static long lcm(long a, long b) {
        return a * b / gcd(a, b);

    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static class Moon {

        private Point3D position;
        private Point3D velocity;

        public Moon(Point3D position) {
            this.position = Point3D.copy(position);
            this.velocity = new Point3D(0, 0, 0);
        }

        public Point3D calculateNewVelocity(Set<Moon> moons) {
            int xVel = velocity.x;
            int yVel = velocity.y;
            int zVel = velocity.z;
            for (Moon other : moons) {
                if (position.x > other.position.x) xVel--;
                if (position.x < other.position.x) xVel++;
                if (position.y > other.position.y) yVel--;
                if (position.y < other.position.y) yVel++;
                if (position.z > other.position.z) zVel--;
                if (position.z < other.position.z) zVel++;
            }
            return new Point3D(xVel, yVel, zVel);
        }

        public void apply(Point3D velocity) {
            this.velocity = velocity;
            position = position.add(velocity);
        }

        public int energy() {
            return (abs(position.x) + abs(position.y) + abs(position.z)) * (abs(velocity.x) + abs(velocity.y) + abs(velocity.z));
        }

        @Override
        public String toString() {
            return String.format("pos=<x=%d, y=%d, z=%d>, vel=<x=%d, y=%d, z= %d> E=%d",
                    position.x, position.y, position.z,
                    velocity.x, velocity.y, velocity.z,
                    energy());
        }
    }
}
