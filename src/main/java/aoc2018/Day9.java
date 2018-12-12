package aoc2018;

import java.util.*;
import java.util.stream.LongStream;

public class Day9 {

    public static void main(String[] args) {
        Circle circle = new Circle();
        Players players = new Players(464);

        long start = System.currentTimeMillis();

        LongStream.rangeClosed(1, 7173000)
                .forEach(i -> {
                    circle.place(i).ifPresent(players::score);
                    players.next();
                    if (i % 100_000 == 0) {
                        System.out.println(i);
                    }
                });
        System.out.println(players.playerScores.values().stream().mapToLong(i -> i).max());

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static class Circle {

        private List<Long> marbles = new ArrayList<>();
        private int index = 0;

        Circle() {
            marbles.add(index, 0L);
        }

        Optional<Long> place(long marble) {
            if (marble==1) {
                marbles.add(marble);
                index++;
                return Optional.empty();
            }
            if (marble % 23 == 0) {
                index -= 7;
                if (index < 0) {
                    index = marbles.size() + index;
                }
                return Optional.of(marble + marbles.remove(index));
            }
            incrementIndex();
            incrementIndex();
            marbles.add(index, marble);
            return Optional.empty();
        }

        private void incrementIndex() {
            index++;
            if (index > marbles.size()) {
                index = 1;
            }
        }

        @Override
        public String toString() {
            return "Circle{index=" + index + ", marbles=" + marbles + '}';
        }
    }

    private static class Players {

        private int nrOfPlayers;
        private int current;

        Map<Integer, Long> playerScores;

        Players(int nrOfPlayers) {
            this.nrOfPlayers = nrOfPlayers;
            current = 1;
            playerScores = new HashMap<>();
        }

        void next() {
            current++;
            if (current > nrOfPlayers) {
                current = 1;
            }
        }

        void score(long points) {
            playerScores.merge(current, points, (s, p) -> s + p);
        }
    }
}
