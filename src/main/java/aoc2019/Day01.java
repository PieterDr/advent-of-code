package aoc2019;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Math.floor;

public class Day01 {

    public static void main(String[] args) throws Exception {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2019/day01.txt"));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    private static double part1(List<String> input) {
        return input.stream()
                .map(Double::parseDouble)
                .map(Day01::fuelFormula)
                .mapToDouble(x -> x)
                .sum();
    }

    private static double part2(List<String> input) {
        return input.stream()
                .map(Double::parseDouble)
                .map(Day01::fuelFormula)
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
                .sum();
    }

    private static double fuelFormula(Double mass) {
        return floor(mass / 3) - 2;
    }
}
