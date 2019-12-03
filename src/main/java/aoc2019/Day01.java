package aoc2019;

import util.Day;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Math.floor;

public class Day01 implements Day<List<String>> {

    public static void main(String[] args) throws Exception {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2019/day01.txt"));
        new Day01().run(input);
    }

    @Override
    public String part1(List<String> input) {
        return String.valueOf(input.stream()
                .map(Double::parseDouble)
                .map(this   ::fuelFormula)
                .mapToDouble(x -> x)
                .sum());
    }
    @Override
    public String part2(List<String> input) {
        return String.valueOf(input.stream()
                .map(Double::parseDouble)
                .map(this::fuelFormula)
                .map(totalFuel -> {
                    double fuel = totalFuel;
                    while (fuel > 0) {
                        double fuelForFuel = fuelFormula(fuel);
                        if (fuelForFuel > 0) totalFuel += fuelForFuel;
                        fuel = fuelForFuel;
                    }
                    return totalFuel;

                })
                .mapToDouble(x -> x)
                .sum());
    }

    private double fuelFormula(Double mass) {
        return floor(mass / 3) - 2;
    }
}
