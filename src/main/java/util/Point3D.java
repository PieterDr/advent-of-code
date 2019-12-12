package util;

import java.util.Objects;

public class Point3D extends Point {

    public int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public static Point3D copy(Point3D point) {
        return new Point3D(point.x, point.y, point.z);
    }

    public Point3D add(Point3D other) {
        return new Point3D(x + other.x, y + other.y, z + other.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return z == point3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), z);
    }


    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}
