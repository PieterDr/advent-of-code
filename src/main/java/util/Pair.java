package util;

public class Pair<L, R> {

    public L left;
    public R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "\nPair{" + left + "," + right + "}";
    }

    public L left() {
        return left;
    }

    public R right() {
        return right;
    }
}
