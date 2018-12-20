package aoc2018;

import util.Direction;
import util.Pair;
import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Day15 {

    private static SortedMap<Point, Combatant> MAP = new TreeMap<>();
    private static List<Elf> ELVES = new ArrayList<>();
    private static List<Gnome> GNOMES = new ArrayList<>();

    private static void parse() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day15.txt"));
        for (int x = 0; x < input.size(); x++) {
            String line = input.get(x);
            for (int y = 0; y < line.length(); y++) {
                Point position = new Point(x, y);
                switch (line.charAt(y)) {
                    case '#':
                        break;
                    case '.':
                        MAP.put(position, null);
                        break;
                    case 'E':
                        Elf elf = new Elf(position);
                        ELVES.add(elf);
                        MAP.put(position, elf);
                        break;
                    case 'G':
                        Gnome gnome = new Gnome(position);
                        GNOMES.add(gnome);
                        MAP.put(position, gnome);
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
        }
    }

    private static void print() {
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                Point point = new Point(x, y);
                if (!MAP.containsKey(point)) {
                    System.out.print("#   ");
                } else if (MAP.get(point) == null) {
                    System.out.print(".   ");
                } else if (MAP.get(point) instanceof Elf) {
                    System.out.print(String.format("E%3d", MAP.get(point).hp));
                } else if (MAP.get(point) instanceof Gnome) {
                    System.out.print(String.format("G%3d", MAP.get(point).hp));
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        parse();
        int round = 0;
        try {
            while (true) {
                List<Combatant> combatants = MAP.keySet().stream()
                        .map(p -> MAP.get(p))
                        .filter(Objects::nonNull)
                        .collect(toList());
                combatants.forEach(Combatant::takeTurn);
                round++;
            }
        } catch (Exception e) {
            System.out.println(round);
            int hpLeft = MAP.values().stream().filter(Objects::nonNull).mapToInt(combatant -> combatant.hp).sum();
            System.out.println(hpLeft);
            System.out.println(round * hpLeft);

            throw e;
        }
    }

    private static abstract class Combatant {

        int hp = 200;
        Point position;

        Combatant(Point position) {
            this.position = position;
        }

        boolean isAlive() {
            return hp > 0;
        }

        void attack(Pair<Combatant, Point> target) {
            if (!position.equals(target.right)) {
                Point nextPosition = new PathFinder(position, target.right).nextPosition();
                MAP.put(position, null);
                position = nextPosition;
                MAP.put(position, this);
            }
            if (position.equals(target.right)) {
                target.left.takeDamage();
            }
        }

        abstract void takeDamage();

        void takeTurn() {
            if (!isAlive()) {
                return;
            }
            List<Combatant> enemies = enemies().stream().filter(Combatant::isAlive).collect(toList());
            if (enemies.isEmpty()) {
                throw new RuntimeException();
            }
            List<Triple<Combatant, Point, Integer>> targets = enemies.stream()
                    .map(e -> new Pair<>(e, e.getPositionsInRange(this)))
                    .flatMap(pair -> pair.right.stream().map(p -> new Pair<>(pair.left, p)))
                    .map(pair -> new Triple<>(pair, new PathFinder(position, pair.right).distanceTo()))
                    .filter(triple -> triple.right >= 0)
                    .sorted(Comparator.comparing(Triple::right))
                    .collect(toList());
            targets.stream()
                    .filter(target -> target.right == targets.get(0).right)
                    .min((t1, t2) -> {
                        if (!t1.middle.equals(t2.middle)) {
                            return Integer.compare(readIndexOf(t1.middle), readIndexOf(t2.middle));
                        } else {
                            if (t1.left.hp == t2.left.hp){
                                return Integer.compare(readIndexOf(t1.left.position), readIndexOf(t2.left.position));
                            } else {
                                return Integer.compare(t1.left.hp, t2.left.hp);
                            }
                        }
                    })
                    .map(Triple::leftPair)
                    .ifPresent(this::attack);
        }

        private List<Point> getPositionsInRange(Combatant combatant) {
            return Arrays.stream(Direction.values())
                    .map(dir -> position.apply(dir))
                    .filter(p -> MAP.containsKey(p))
                    .filter(p -> MAP.get(p) == null || MAP.get(p) == combatant)
                    .collect(Collectors.toList());
        }

        abstract List<? extends Combatant> enemies();

    }

    private static int readIndexOf(Point point) {
        return new ArrayList<>(MAP.keySet()).indexOf(point);
    }

    private static class PathFinder {

        private Point start;
        private Point target;

        PathFinder(Point start, Point target) {
            this.start = start;
            this.target = target;
        }

        int distanceTo() {
            int distance = 0;
            Set<Point> visited = new HashSet<>();
            visited.add(start);
            Set<Point> currentPoints = new HashSet<>(visited);
            while (!visited.contains(target)) {
                currentPoints = currentPoints.stream()
                        .flatMap(p -> applyDirections(p).stream())
                        .filter(p -> !visited.contains(p))
                        .collect(toSet());
                if (currentPoints.isEmpty()) {
                    return Integer.MIN_VALUE;
                }
                visited.addAll(currentPoints);
                distance++;
            }
            return distance;
        }

        Point nextPosition() {
            Set<Point> neighbours = applyDirections(start);
            return neighbours.stream()
                    .map(n -> new PathFinder(n, target))
                    .map(pathFinder -> new Pair<>(pathFinder, pathFinder.distanceTo()))
                    .filter(pair -> pair.right >= 0)
                    .min((p1, p2) -> {
                        if (p1.right != p2.right) {
                            return Integer.compare(p1.right, p2.right);
                        } else {
                            return Integer.compare(readIndexOf(p1.left.start), readIndexOf(p2.left.start));
                        }
                    })
                    .get().left.start;
        }

        private Set<Point> applyDirections(Point point) {
            Set<Point> points = new HashSet<>();
            for (Direction direction : Direction.values()) {
                Point next = point.apply(direction);
                if (MAP.keySet().contains(next) && MAP.get(next) == null) {
                    points.add(next);
                }
            }
            return points;
        }
    }

    private static class Elf extends Combatant {

        Elf(Point position) {
            super(position);
        }

        @Override
        void takeDamage() {
            hp -= 3;
            if (!isAlive()) throw new RuntimeException("elf died");
        }

        @Override
        List<? extends Combatant> enemies() {
            return GNOMES;
        }

        @Override
        public String toString() {
            return "Elf:" + hp;
        }
    }

    private static class Gnome extends Combatant {
        Gnome(Point position) {
            super(position);
        }

        @Override
        void takeDamage() {
            hp -= 20;
            if (!isAlive()) MAP.put(position, null);
        }

        @Override
        List<? extends Combatant> enemies() {
            return ELVES;
        }

        @Override
        public String toString() {
            return "Gnome:" + hp;
        }
    }

}
