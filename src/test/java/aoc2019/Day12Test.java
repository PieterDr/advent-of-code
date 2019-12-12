package aoc2019;

import org.junit.jupiter.api.Test;
import util.Point3D;

import java.util.List;

import static aoc2019.Day12.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void leastCommonMultiple() {
        assertEquals(6, lcm(2, 3));
        assertEquals(30, lcm(10, lcm(2, 3)));
    }

    @Test
    void example1() {
        assertEquals(179, part1(example1Moons(), 10));
        assertEquals(2772, part2(example1Moons()));
    }

    private List<Moon> example1Moons() {
        Moon io = new Moon(new Point3D(-1, 0, 2));
        Moon europe = new Moon(new Point3D(2, -10, -7));
        Moon ganymede = new Moon(new Point3D(4, -8, 8));
        Moon callisto = new Moon(new Point3D(3, 5, -1));
        return asList(io, europe, ganymede, callisto);
    }

    @Test
    void example2() {
        assertEquals(1940, part1(example2Moons(), 100));
        assertEquals(4686774924L, part2(example2Moons()));
    }

    private List<Moon> example2Moons() {
        Moon io = new Moon(new Point3D(-8, -10, 0));
        Moon europe = new Moon(new Point3D(5, 5, 10));
        Moon ganymede = new Moon(new Point3D(2, -7, 3));
        Moon callisto = new Moon(new Point3D(9, -8, -3));
        return asList(io, europe, ganymede, callisto);
    }

    @Test
    void input() {
        assertEquals(5517, part1(inputMoons(), 1000));
        assertEquals(303070460651184L, part2(inputMoons()));
    }

    private List<Moon> inputMoons() {
        Moon io = new Moon(new Point3D(-16, -1, -12));
        Moon europe = new Moon(new Point3D(0, -4, -17));
        Moon ganymede = new Moon(new Point3D(-11, 11, 0));
        Moon callisto = new Moon(new Point3D(2, 2, -6));
        return asList(io, europe, ganymede, callisto);
    }
}
