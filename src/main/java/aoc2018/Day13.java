package aoc2018;

import util.Direction;
import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static util.Direction.*;

public class Day13 {

    private static SortedMap<Point, Track> TRACKS = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        parse();

        while (true) {
            List<Track> tracksWithCart = TRACKS.values().stream()
                    .filter(Track::hasCart)
                    .collect(Collectors.toList());
            if (tracksWithCart.size() == 1) {
                System.out.println("Last cart at: " + tracksWithCart.get(0).point);
                break;
            } else {
                tracksWithCart.forEach(Track::moveCart);
            }
        }
    }

    private static void parse() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day13.txt"));
        for (int r = 0; r < input.size(); r++) {
            String line = convert(input.get(r));
            System.out.println(line);
            for (int c = 0; c < line.length(); c++) {
                Track.from(new Point(c, r), line.charAt(c)).ifPresent(track -> TRACKS.put(track.point, track));
            }
        }
    }

    private static String convert(String line) {
        return line
                .replaceAll("-/", "-╝")
                .replaceAll("\\+/", "+╝")
                .replaceAll("</", "<╝")
                .replaceAll(">/", ">╝")
                .replaceAll("-\\\\", "-╗")
                .replaceAll("\\+\\\\", "+╗")
                .replaceAll("<\\\\", "<╗")
                .replaceAll(">\\\\", ">╗")
                .replaceAll("/-", "╔-")
                .replaceAll("/\\+", "╔+")
                .replaceAll("/<", "╔<")
                .replaceAll("/>", "╔>")
                .replaceAll("\\\\-", "╚-")
                .replaceAll("\\\\\\+", "╚+")
                .replaceAll("\\\\<", "╚<")
                .replaceAll("\\\\>", "╚>");
    }

    private static class Track {

        Point point;
        Cart cart;
        Set<Direction> directions;

        Track(Point point, Direction... directions) {
            this(point, null, directions);
        }

        Track(Point point, Cart cart, Direction... directions) {
            this.point = point;
            this.cart = cart;
            this.directions = new HashSet<>(asList(directions));
        }

        static Optional<Track> from(Point point, char c) {
            switch (c) {
                case '^':
                    return Optional.of(new Track(point, new Cart(UP), UP, DOWN));
                case 'v':
                    return Optional.of(new Track(point, new Cart(DOWN), UP, DOWN));
                case '>':
                    return Optional.of(new Track(point, new Cart(RIGHT), LEFT, RIGHT));
                case '<':
                    return Optional.of(new Track(point, new Cart(LEFT), LEFT, RIGHT));
                case '|':
                    return Optional.of(new Track(point, UP, DOWN));
                case '-':
                    return Optional.of(new Track(point, LEFT, RIGHT));
                case '╚':
                    return Optional.of(new Track(point, UP, RIGHT));
                case '╔':
                    return Optional.of(new Track(point, DOWN, RIGHT));
                case '╝':
                    return Optional.of(new Track(point, UP, LEFT));
                case '╗':
                    return Optional.of(new Track(point, DOWN, LEFT));
                case '+':
                    return Optional.of(new Track(point, UP, DOWN, LEFT, RIGHT));
                default:
                    return Optional.empty();
            }
        }

        boolean hasCart() {
            return cart != null;
        }

        void moveCart() {
            if (hasCart()) {
                Track next = TRACKS.get(point.apply(cart.heading));
                next.arrive(cart);
                this.cart = null;
            }
        }

        private void arrive(Cart cart) {
            if (hasCart()) {
                this.cart = null;
            } else {
                this.cart = cart;
                cart.changeHeading(new HashSet<>(directions));
            }
        }
    }

    private static class Cart {

        Direction heading;
        int turns = 0;

        Cart(Direction heading) {
            this.heading = heading;
        }

        void changeHeading(Set<Direction> directions) {
            directions.remove(heading.opposite());
            if (directions.size() == 1) {
                heading = directions.stream().findFirst().get();
            } else {
                heading = heading.turnLeft();
                for (int i = 0; i < turns % 3; i++) {
                    heading = heading.turnRight();
                }
                turns++;
            }
        }
    }

}
