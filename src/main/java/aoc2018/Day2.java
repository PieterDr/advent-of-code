package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day2.txt"));
        part1(input);
        System.out.println(part2(input));
    }

    private static void part1(List<String> input) {
        int doubles = 0;
        int triples = 0;
        for (String s : input) {
            List<Character> chars = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            HashSet<Character> set = new HashSet<>(chars);
            boolean hasDouble = false;
            boolean hasTriple = false;
            for (Character c : set) {
                int count = countAppearances(c, chars);
                if (count == 2 && !hasDouble) {
                    doubles++;
                    hasDouble = true;
                    System.out.println("Double: " + s);
                }
                if (count == 3 && !hasTriple) {
                    triples++;
                    hasTriple = true;
                    System.out.println("Triple: " + s);
                }
                if (hasDouble && hasTriple) {
                    System.out.println("Breaking...");
                    break;
                }
            }
        }
        System.out.println(doubles);
        System.out.println(triples);
        System.out.println(doubles * triples);
    }

    private static String part2(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if (i == j) {
                    continue;
                }
                String s = input.get(i);
                String t = input.get(j);
                for (int k = 0; k < s.length(); k++) {
                    String left = s.substring(0, k);
                    String right = s.substring(k + 1);
                    if (t.contains(left) && t.contains(right)) {
                        return left + right;
                    }
                }
            }
        }
        return "fuck off";
    }

    private static int countAppearances(Character c, List<Character> chars) {
        int count = 0;
        for (Character character : chars) {
            if (c == character) {
                count++;
            }
        }
        return count;
    }

}
