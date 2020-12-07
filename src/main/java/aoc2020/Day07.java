package aoc2020;

import util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toSet;

public class Day07 implements Day<List<String>> {

    private static final Pattern CONSTRAINT_PATTERN = Pattern.compile("(\\d+) ([a-z]+ [a-z]+) bag.*");

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of(Day07.class.getClassLoader().getResource("2020/day07.txt").getPath()));
        new Day07().run(input);
    }

    @Override
    public String part1(List<String> input) {
        Map<String, Node> nodes = parse(input);
        Node node = nodes.get("shiny gold");
        return "" + node.getParentsRecursive(new HashSet<>()).size();
    }

    @Override
    public String part2(List<String> input) {
        Map<String, Node> nodes = parse(input);
        Node node = nodes.get("shiny gold");
        return "" + node.countChildrenRecursive();
    }

    private Map<String, Node> parse(List<String> input) {
        Map<String, Node> nodes = new HashMap<>();
        for (String line : input) {
            String[] parts = line.split(" bags contain ");
            String[] constraints = parts[1].split(", ");
            Node node = nodes.computeIfAbsent(parts[0], Node::new);
            for (String constraint : constraints) {
                Matcher matcher = CONSTRAINT_PATTERN.matcher(constraint);
                if (matcher.matches()) {
                    node.addChild(nodes.computeIfAbsent(matcher.group(2), Node::new), matcher.group(1));
                }
            }
        }
        return nodes;
    }

    static class Node {

        private final String name;
        private final List<Node> parents = new ArrayList<>();
        private final Map<Node, Integer> children = new HashMap<>();

        private Node(String name) {
            this.name = name;
        }

        public void addChild(Node child, String n) {
            children.put(child, Integer.parseInt(n));
            child.parents.add(this);
        }

        public Set<Node> getParentsRecursive(Set<Node> accumulator) {
            if (parents.isEmpty()) {
                return accumulator;
            }
            accumulator.addAll(parents);
            return parents.stream()
                    .map(parent -> parent.getParentsRecursive(accumulator))
                    .flatMap(Collection::stream)
                    .collect(toSet());
        }

        public int countChildrenRecursive() {
            if (children.isEmpty()) {
                return 0;
            }
            int count = 0;
            for (Map.Entry<Node, Integer> entry : children.entrySet()) {
                count += entry.getValue();
                count += entry.getValue() * entry.getKey().countChildrenRecursive();
            }
            return count;
        }
    }
}
