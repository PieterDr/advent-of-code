package aoc2018;

import java.util.HashMap;
import java.util.Map;

public class Day9Improved {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Players players = new Players(464);
        Marble current = Marble.marble0();
        for (long i = 1; i <= 7173000; i++) {
            if (i % 23 == 0) {
                current = current.left.left.left.left.left.left.left;
                players.score(i + current.value);
                current.remove();
                current = current.right;
            } else {
                Marble marble = new Marble(i);
                current.right.insertRight(marble);
                current = marble;
            }
            players.next();
        }
        System.out.println(players.playerScores.values().stream().mapToLong(i -> i).max());

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static class Marble {

        private long value;
        private Marble left;
        private Marble right;

        Marble(long value) {
            this.value = value;
        }

        static Marble marble0() {
            Marble marble = new Marble(0);
            marble.left = marble;
            marble.right = marble;
            return marble;
        }

        void remove() {
            left.right = right;
            right.left = left;
        }

        void insertRight(Marble other) {
            other.left = this;
            other.right = right;
            this.right.left = other;
            this.right = other;
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
