package aoc2018;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Day14 {

    private static final String TARGET = "909441";

    private static List<Integer> RECIPES = new ArrayList<>();
    private static int E_1 = 0;
    private static int E_2 = 1;


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        RECIPES.add(E_1, 3);
        RECIPES.add(E_2, 7);
        while (!containsPattern(RECIPES)) {
            addRecipes();
            moveElf1();
            moveElf2();
        }
        System.out.println(
                RECIPES.stream()
                        .map(n -> Integer.toString(n))
                        .collect(Collectors.joining())
                        .indexOf(TARGET)
        );
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static boolean containsPattern(List<Integer> recipes) {
        if (recipes.size() < TARGET.length() + 1) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        recipes.subList(recipes.size() - TARGET.length() - 1, recipes.size()).forEach(sb::append);
        return sb.toString().contains(TARGET);
    }

    private static void addRecipes() {
        int recipes = RECIPES.get(E_1) + RECIPES.get(E_2);
        RECIPES.addAll(split(recipes));
    }

    private static void moveElf1() {
        E_1 += RECIPES.get(E_1) + 1;
        while (E_1 >= RECIPES.size()) {
            E_1 = E_1 - RECIPES.size();
        }
    }

    private static void moveElf2() {
        E_2 += RECIPES.get(E_2) + 1;
        while (E_2 >= RECIPES.size()) {
            E_2 = E_2 - RECIPES.size();
        }
    }

    private static List<Integer> split(int recipes) {
        int units = recipes % 10;
        return recipes < 10
                ? asList(units)
                : asList((recipes - units) / 10, units);
    }
}
