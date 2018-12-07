package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get("src/main/resources/2018", "day5.txt")).get(0);
        part1(input);
    }

    private static void part1(String input) {
        List<Integer> lengths = new ArrayList<>();
        String originalInput = input;
        char[] chars = new char[] {'a', 'b', 'c','d','e','f','g','h','i','j','l','k','m','n','o','p','q','r','s','t','v','w','x','y','z'};
        for (char c : chars) {
            String regex = new String(new char[]{c}).toUpperCase();
            input = input.replaceAll(regex, "");
            input = input.replaceAll(regex.toLowerCase(), "");
            try {
                String previousInput = input;
                String newInput = "";
                while (!newInput.equals(previousInput)) {
                    previousInput = newInput;
                    newInput = react(input);
                    input = newInput;
                }
            } catch (Exception e) {
                System.out.println(input.length());
                lengths.add(input.length());
            }
            input = originalInput;
        }
        lengths.sort(Comparator.naturalOrder());
        System.out.println(lengths.get(0));

    }

    private static String react(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            char d = input.charAt(i + 1);
            if (c + 32 == d || c - 32 == d) {
                return input.substring(0, i) + input.substring(i + 2);
            }
        }
        return input;
    }
}
