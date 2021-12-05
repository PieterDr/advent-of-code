package util;

import java.util.Objects;

public class Point implements Comparable<Point> {

    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int compareTo(Point o) {
        int xCompare = x - o.x;
        return xCompare == 0 ? y - o.y : xCompare;
    }

    public Point add(Point o) {
        return new Point(x + o.x, y + o.y);
    }

    public Point subtract(Point o) {
        return new Point(x - o.x, y - o.y);
    }

    public Point apply(Direction direction) {
        return switch (direction) {
            case UP -> new Point(x, y + 1);
            case DOWN -> new Point(x, y - 1);
            case LEFT -> new Point(x - 1, y);
            case RIGHT -> new Point(x + 1, y);
        };
    }


    public int distanceTo(Point o) {
        return Math.abs(o.x - x) + Math.abs(o.y - y);
    }

    public Point multiply(int i) {
        return new Point(x * i, y * i);
    }

    public Point slope(Point end) {
        Point difference = end.subtract(this);
        int slopeX = 0;
        int slopeY = 0;
        if (difference.x != 0) {
            slopeX = difference.x / Math.abs(difference.x);
        }
        if (difference.y != 0) {
            slopeY = difference.y / Math.abs(difference.y);
        }
        return new Point(slopeX, slopeY);
    }
}
