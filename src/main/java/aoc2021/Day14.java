package aoc2021;

import util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.primitives.Chars.asList;
import static java.util.stream.Collectors.toMap;

public class Day14 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/2021/day14.txt"));
        String template = lines.get(0);
        Map<String, String> rules = lines.stream()
                .skip(2)
                .map(line -> line.split(" -> "))
                .collect(toMap(
                        parts -> parts[0],
                        parts -> parts[1]
                ));
        System.out.println(template);
        System.out.println(rules);
//        part1(template, rules);
        part2(template, rules);
    }

    private static void part1(String template, Map<String, String> rules) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            template = apply(template, rules);
        }
        List<Character> chars = asList(template.toCharArray());
        List<Map.Entry<Character, Integer>> sorted = chars.stream()
                .collect(toMap(
                        c -> c,
                        c -> 1,
                        Integer::sum
                ))
                .entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .toList();
        System.out.println(sorted.get(sorted.size() - 1).getValue() - sorted.get(0).getValue());
    }

    private static void part2(String template, Map<String, String> rules) {
        Map<String, Long> pairs = splitInPairs(template).stream()
                .collect(toMap(
                        pair -> pair,
                        pair -> 1L,
                        Long::sum));
        System.out.println(pairs);
        Map<Character, Long> elementCount = asList(template.toCharArray()).stream()
                .collect(toMap(
                        c -> c,
                        c -> 1L,
                        Long::sum
                ));


        for (int i = 0; i < 40; i++) {
            pairs = pairs.entrySet().stream()
                    .flatMap(entry -> {
                        String pair = entry.getKey();
                        String element = rules.getOrDefault(pair, "");
                        Long value = elementCount.getOrDefault(element.charAt(0), 0L);
                        elementCount.put(element.charAt(0), value + entry.getValue());
                        return Stream.of(
                                new Pair<>(pair.charAt(0) + element, entry.getValue()),
                                new Pair<>(element + pair.charAt(1), entry.getValue())
                        );
                    })
                    .collect(toMap(
                            pair -> pair.left,
                            pair -> pair.right,
                            Long::sum)
                    );
        }
        List<Map.Entry<Character, Long>> sorted = elementCount.entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry::getValue))
                .toList();
        System.out.println(sorted.get(sorted.size() - 1).getValue() - sorted.get(0).getValue());
    }

    private static String apply(String template, Map<String, String> rules) {
        return splitInPairs(template).stream()
                .map(pair -> {
                    String element = rules.getOrDefault(pair, "");
                    return pair.charAt(0) + element;
                })
                .collect(Collectors.joining("")) + template.charAt(template.length() - 1);
    }

    private static List<String> splitInPairs(String template) {
        return IntStream.range(0, template.length() - 1)
                .mapToObj(i -> template.substring(i, i + 2))
                .toList();
    }

}
