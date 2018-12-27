package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day18 {

    private static final int SIZE = 50;
    private static final int STEPS = 1000000000;
    private static char[][] map = new char[SIZE][SIZE];
    private static List<Integer> products = new ArrayList<>(STEPS + STEPS / 4);

    public static void main(String[] args) throws IOException {
        parse();
        boolean cont = true;
        while (cont) {
            step();
            int product = calculateProduct();
            int firstIndex = products.indexOf(product);
            int lastIndex = products.lastIndexOf(product);
            if (firstIndex != -1 && firstIndex != lastIndex) {
                List<Integer> sublist = new ArrayList<>(products.subList(firstIndex, lastIndex));
                if (sublist.equals(products.subList(lastIndex, products.size()))) {
                    int steps = STEPS / sublist.size();
                    for (int j = 0; j < steps; j++) {
                        products.addAll(sublist);
                    }
                    cont = false;
                }
            }
            products.add(product);
        }
        System.out.println(products.get(STEPS - 1));
    }

    private static int calculateProduct() {
        int trees = 0;
        int lumberyards = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (map[row][col] == '|') trees++;
                if (map[row][col] == '#') lumberyards++;
            }
        }
        return trees * lumberyards;
    }

    private static void step() {
        char[][] newMap = new char[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char c = map[row][col];
                newMap[row][col] = dispatch(c, row, col);
            }
        }
        map = newMap;
    }

    private static char dispatch(char c, int row, int col) {
        switch (c) {
            case '.':
                return open(row, col);
            case '|':
                return trees(row, col);
            case '#':
                return lumberyard(row, col);
            default:
                throw new RuntimeException();
        }
    }

    private static char open(int row, int col) {
        long adjacentTrees = getNeighbours(row, col).stream()
                .filter(c -> c == '|')
                .count();
        return adjacentTrees >= 3 ? '|' : '.';
    }

    private static char trees(int row, int col) {
        long adjacentLumberyards = getNeighbours(row, col).stream()
                .filter(c -> c == '#')
                .count();
        return adjacentLumberyards >= 3 ? '#' : '|';
    }

    private static char lumberyard(int row, int col) {
        List<Character> neighbours = getNeighbours(row, col);
        long adjacentTrees = neighbours.stream().filter(c -> c == '|').count();
        long adjacentLumberyards = neighbours.stream().filter(c -> c == '#').count();
        return adjacentTrees >= 1 && adjacentLumberyards >= 1 ? '#' : '.';
    }

    private static List<Character> getNeighbours(int row, int col) {
        List<Character> neighbours = new ArrayList<>();
        getValue(row - 1, col - 1).ifPresent(neighbours::add);
        getValue(row - 1, col).ifPresent(neighbours::add);
        getValue(row - 1, col + 1).ifPresent(neighbours::add);
        getValue(row, col - 1).ifPresent(neighbours::add);
        getValue(row, col + 1).ifPresent(neighbours::add);
        getValue(row + 1, col - 1).ifPresent(neighbours::add);
        getValue(row + 1, col).ifPresent(neighbours::add);
        getValue(row + 1, col + 1).ifPresent(neighbours::add);
        return neighbours;
    }

    private static Optional<Character> getValue(int row, int col) {
        try {
            return Optional.of(map[row][col]);
        } catch (IndexOutOfBoundsException ex) {
            return Optional.empty();
        }
    }

    private static void parse() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day18.txt"));
        for (int r = 0; r < SIZE; r++) {
            String line = input.get(r);
            for (int c = 0; c < SIZE; c++) {
                map[r][c] = line.charAt(c);
            }
        }
    }
}
