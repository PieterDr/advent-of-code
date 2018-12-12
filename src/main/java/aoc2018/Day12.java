package aoc2018;


import util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Day12 {

    private static Map<String, String> PATTERNS = new HashMap<>();
    private static long STEPS = 50_000_000L;
    private static long leftPadding = 0;

    public static void main(String[] args) throws IOException {
        String pots = setup();
        long start = System.currentTimeMillis();
        Pair<String, Long> previousState = new Pair<>(pots, leftPadding);
        long stepsDone;
        for (stepsDone = 0; stepsDone < STEPS; stepsDone++) {
            previousState = new Pair<>(pots, leftPadding);
            pots = prependEmptyPots(pots);
            pots = appendEmptyPots(pots);
            pots = trimLeadingPots(pots);
            StringBuilder sb = new StringBuilder(pots.substring(0, 2));
            splitInGroupsOf5(pots).forEach(group -> sb.append(PATTERNS.getOrDefault(group, ".")));
            sb.append(pots.substring(pots.length() - 2));
            pots = sb.toString();
            if (pots.equals(previousState.left)) {
                stepsDone++;
                break;
            }
        }
        long paddingDiff = leftPadding - previousState.right;
        if (stepsDone < STEPS) leftPadding += paddingDiff * (STEPS - stepsDone);

        long sum = 0;
        for (int j = 0; j < pots.length(); j++) {
            if (pots.charAt(j) == '#') {
                sum += j - leftPadding;
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(end - start);
    }

    private static List<String> splitInGroupsOf5(String pots) {
        return IntStream.rangeClosed(2, pots.length()-3)
                .mapToObj(i -> pots.substring(i-2, i+3))
                .collect(toList());
    }

    private static String prependEmptyPots(String pots) {
        int potsToAdd = 5;
        for (int i = 0; i != 5; i++) {
            if (pots.charAt(i) == '#') {
                break;
            }
            potsToAdd--;
        }
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, potsToAdd).forEach(i -> {
            sb.append(".");
            leftPadding++;
        });
        sb.append(pots);
        return sb.toString();
    }

    private static String appendEmptyPots(String pots) {
        int potsToAdd = 5;
        for (int i = 1; i != 6; i++) {
            if (pots.charAt(pots.length() - i) == '#') {
                break;
            }
            potsToAdd--;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(pots);
        IntStream.range(0, potsToAdd).forEach(i -> sb.append("."));
        return sb.toString();
    }

    private static String trimLeadingPots(String pots) {
        int leadingEmptyPots = 0;
        while (pots.charAt(leadingEmptyPots) == '.') {
            leadingEmptyPots++;
        }
        if (leadingEmptyPots > 4) {
            leftPadding -= leadingEmptyPots - 4;
            return pots.substring(leadingEmptyPots - 4);
        }
        return pots;
    }

    private static String setup() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day12.txt"));
        String pots = input.remove(0);
        input.stream()
                .map(s -> s.split(" => "))
                .forEach(s -> PATTERNS.put(s[0], s[1]));
        return pots;
    }
}
