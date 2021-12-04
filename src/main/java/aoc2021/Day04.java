package aoc2021;

import util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Day04 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/2021/day04.txt"));
        List<Integer> numbers = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Board> boards = new ArrayList<>();
        for (int i = 2; i < input.size(); i += 6) {
            boards.add(new Board(input.subList(i, i + 5)));
        }
        play(numbers, boards);
    }

    private static void play(List<Integer> numbers, List<Board> boards) {
        for (Integer number : numbers) {
            for (Board board : boards) {
                if (board.playing) {
                    board.mark(number);
                }
            }
        }
    }

    private static class Board {

        private final Map<Integer, List<Integer>> rows = Map.of(
                0, new ArrayList<>(List.of(0, 0, 0, 0, 0)),
                1, new ArrayList<>(List.of(0, 0, 0, 0, 0)),
                2, new ArrayList<>(List.of(0, 0, 0, 0, 0)),
                3, new ArrayList<>(List.of(0, 0, 0, 0, 0)),
                4, new ArrayList<>(List.of(0, 0, 0, 0, 0))
        );
        private final Map<Integer, List<Integer>> columns = Map.of(
                0, new ArrayList<>(List.of(0, 0, 0, 0, 0)),
                1, new ArrayList<>(List.of(0, 0, 0, 0, 0)),
                2, new ArrayList<>(List.of(0, 0, 0, 0, 0)),
                3, new ArrayList<>(List.of(0, 0, 0, 0, 0)),
                4, new ArrayList<>(List.of(0, 0, 0, 0, 0))
        );
        private final Map<Integer, Pair<Integer, Integer>> index = new HashMap<>();
        private boolean playing = true;

        public Board(List<String> input) {
            List<List<Integer>> board = input.stream()
                    .map(line -> line.split(" "))
                    .map(line -> Arrays.stream(line)
                            .filter(s -> !s.isBlank())
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
                    )
                    .collect(Collectors.toList());
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    Integer value = board.get(r).get(c);
                    index.put(value, new Pair<>(r, c));
                    rows.get(r).set(c, value);
                    columns.get(c).set(r, value);
                }
            }
        }

        public void mark(Integer number) {
            Pair<Integer, Integer> coordinate = index.get(number);
            if (coordinate != null) {
                List<Integer> row = rows.get(coordinate.left);
                row.remove(number);
                List<Integer> column = columns.get(coordinate.right);
                column.remove(number);
                if (row.isEmpty() || column.isEmpty()) {
                    playing = false;
                }
            }
            if (!playing) {
                System.out.println(score(number));
            }
        }

        private int score(Integer number) {
            return number * rows.values().stream()
                    .flatMap(Collection::stream)
                    .mapToInt(i -> i)
                    .sum();
        }
    }
}
