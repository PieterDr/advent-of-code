package aoc2018;

import util.Pair;

public class Triple<L, M, R> {

    L left;
    M middle;
    R right;

    public Triple(Pair<L, M> pair, R r) {
        this.left = pair.left;
        this.middle = pair.right;
        this.right = r;
    }

    public L left() {
        return left;
    }

    public M middle() {
        return middle;
    }

    public R right() {
        return right;
    }

    public Pair<L, M> leftPair() {
        return new Pair<>(left, middle);
    }
}
