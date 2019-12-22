package aoc2019;

import java.util.stream.IntStream;

public class Day22 {

    private int[] deck;
    private final int size;

    public Day22(int size) {
        this.deck = new int[size];
        this.size = size;
        IntStream.range(0, size).forEach(i -> deck[i] = i);
    }

    Day22 dealIntoNewStack() {
        int[] newDeck = new int[size];
        for (int i = 0; i < size; i++) {
            newDeck[size - i - 1] = deck[i];
        }
        deck = newDeck;
        return this;
    }

    Day22 cut(int n) {
        int[] newDeck = new int[size];
        if (n >= 0) {
            System.arraycopy(deck, 0, newDeck, size - n, n);
            System.arraycopy(deck, n, newDeck, 0, size - n);
        } else {
            n = Math.abs(n);
            System.arraycopy(deck, size - n, newDeck, 0, n);
            System.arraycopy(deck, 0, newDeck, n, size - n);
        }
        deck = newDeck;
        return this;
    }

    Day22 dealWithIncrement(int increment) {
        int[] newDeck = new int[size];
        int i = 0;
        for (int card : deck) {
            newDeck[i] = card;
            i = (i + increment) % size;
        }
        deck = newDeck;
        return this;
    }

    int[] getDeck() {
        return deck;
    }
}
