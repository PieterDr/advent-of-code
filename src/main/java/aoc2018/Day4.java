package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    private static Pattern GUARD_PATTERN = Pattern.compile("Guard #(.*) begins shift");

    private static ConcurrentHashMap<String, Guard> guards = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day4.txt"));
//        input.sort(Comparator.naturalOrder());
        parse(input);
        part1();

    }

    private static void part1() {
        ArrayList<Guard> guards = new ArrayList<>(Day4.guards.values());
        guards.sort(Comparator.naturalOrder());
        Guard sleepiestGuard = guards.get(guards.size() - 1);
        System.out.println(sleepiestGuard);
        System.out.println(sleepiestGuard.getSleepiestMinute());
        System.out.println("RESULT: " + sleepiestGuard.id * sleepiestGuard.getSleepiestMinute().get());
    }

    private static void parse(List<String> input) {
        List<Integer> shiftBoundaries = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains("Guard")) {
                shiftBoundaries.add(i);
            }
        }
        shiftBoundaries.add(input.size());
        for (int i = 0; i < shiftBoundaries.size() - 1; i++) {
            parseShift(input.subList(shiftBoundaries.get(i), shiftBoundaries.get(i + 1)));
        }
    }

    private static void parseShift(List<String> shift) {
        Matcher matcher = GUARD_PATTERN.matcher(shift.get(0));
        matcher.find();
        String guardId = matcher.group(1);
        Guard guard = guards.getOrDefault(guardId, new Guard(guardId));
        Iterator<String> it = shift.iterator();
        it.next();
        while (it.hasNext()) {
            String asleep = it.next();
            String awake = it.next();
            int asleepMinute = Integer.parseInt(asleep.split(":")[1].substring(0, 2));
            int awakeMinute = Integer.parseInt(awake.split(":")[1].substring(0, 2));
            for (int i = asleepMinute; i < awakeMinute; i++) {
                guard.addAsleepMinute(i);
            }
        }
        guards.put(guardId, guard);
    }

    static class Guard implements Comparable<Guard> {

        int id;
        SortedMap<Integer, Integer> minuteCountMap = new TreeMap<>();

        Guard(String id) {
            this.id = Integer.parseInt(id);
        }

        void addAsleepMinute(int asleepMinute) {
            minuteCountMap.merge(asleepMinute, 1, (o, n) -> o + n);
        }

        private int totalSleepMinutes() {
            return minuteCountMap.values().stream().mapToInt(i -> i).sum();
        }

        private int frequencyOfSleepiestMinute() {
            return getSleepiestMinute().map(i -> minuteCountMap.get(i)).orElse(0);
        }

        public Optional<Integer> getSleepiestMinute() {
            return minuteCountMap.keySet().stream()
                    .reduce((i, j) -> {
                        if (minuteCountMap.get(i) < minuteCountMap.get(j)) {
                            return j;
                        } else {
                            return i;
                        }
                    });
        }

        @Override
        public String toString() {
            return "Guard{id=" + id + "; map=" + minuteCountMap + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Guard guard = (Guard) o;
            return id == guard.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public int compareTo(Guard o) {
            return frequencyOfSleepiestMinute() - o.frequencyOfSleepiestMinute();
        }
    }

}
