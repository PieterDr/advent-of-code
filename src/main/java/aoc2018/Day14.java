package aoc2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                        .map(n -> "" + n)
                        .collect(Collectors.joining())
                        .indexOf(TARGET)
        );
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static boolean containsPattern(List<Integer> recipes) {
        if (recipes.size() < TARGET.length() * 2) {
            return false;
        }
        return recipes.subList(recipes.size() - TARGET.length() * 2, recipes.size()).stream().map(n -> "" + n).collect(Collectors.joining("")).contains(TARGET);
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
        return Arrays.stream(String.format("%d", recipes).split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
