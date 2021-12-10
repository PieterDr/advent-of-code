package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static java.util.stream.Collectors.toList;

public class Day10 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/2021/day10.txt"));
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        System.out.println(input.stream()
                .map(line -> {
                    Stack<Character> stack = new Stack<>();
                    for (char c : line.toCharArray()) {
                        if (c == '(') stack.push(')');
                        if (c == '[') stack.push(']');
                        if (c == '{') stack.push('}');
                        if (c == '<') stack.push('>');
                        if (c == ')' && stack.pop() != ')') return c;
                        if (c == ']' && stack.pop() != ']') return c;
                        if (c == '}' && stack.pop() != '}') return c;
                        if (c == '>' && stack.pop() != '>') return c;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .mapToInt(c -> switch (c) {
                    case ')' -> 3;
                    case ']' -> 57;
                    case '}' -> 1197;
                    case '>' -> 25137;
                    default -> throw new RuntimeException();
                })
                .sum());
    }

    private static void part2(List<String> input) {
        List<Long> scores = input.stream()
                .map(line -> {
                    Stack<String> stack = new Stack<>();
                    for (char c : line.toCharArray()) {
                        if (c == '(') stack.push(")");
                        if (c == '[') stack.push("]");
                        if (c == '{') stack.push("}");
                        if (c == '<') stack.push(">");
                        if (c == ')' && stack.pop() != ")") return null;
                        if (c == ']' && stack.pop() != "]") return null;
                        if (c == '}' && stack.pop() != "}") return null;
                        if (c == '>' && stack.pop() != ">") return null;
                    }
                    StringBuilder completion = new StringBuilder();
                    while (!stack.isEmpty()) {
                        completion.append(stack.pop());
                    }
                    return completion.toString();
                })
                .filter(Objects::nonNull)
                .map(completion -> {
                    System.out.println(completion);
                    long score = 0;
                    for (char c : completion.toCharArray()) {
                        int value = switch (c) {
                            case ')' -> 1;
                            case ']' -> 2;
                            case '}' -> 3;
                            case '>' -> 4;
                            default -> throw new RuntimeException();
                        };
                        score = score * 5 + value;
                    }
                    return score;
                })
                .sorted()
                .collect(toList());
        System.out.println(scores.get(scores.size()/2));
    }

}
