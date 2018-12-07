package aoc2018;

import util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Day7 {

    public static void main(String[] args) throws IOException {
        Tasks tasks = new Tasks();
        Files.readAllLines(Paths.get("src/main/resources/2018", "day7.txt")).stream()
                .map(line -> {
                    String first = line.split("Step ")[1].substring(0, 1);
                    String second = line.split("step ")[1].substring(0, 1);
                    return new Pair<>(first, second);
                })
                .forEach(tasks::insert);
        tasks.part1();
        tasks.tasks.values().forEach(Task::reset);
        System.out.println();
        tasks.part2();
    }

    private static class Tasks {

        Map<String, Task> tasks = new HashMap<>();

        void insert(Pair<String, String> pair) {
            Task requirement = tasks.getOrDefault(pair.left, new Task(pair.left));
            Task task = tasks.getOrDefault(pair.right, new Task(pair.right));
            task.addRequirement(requirement);
            tasks.put(task.label, task);
            tasks.put(requirement.label, requirement);
        }

        void part1() {
            List<Task> tasksSorted = this.tasks.values().stream().sorted().collect(toList());
            Optional<Task> nextTask;
            do {
                nextTask = tasksSorted.stream()
                        .filter(task -> !task.finished)
                        .filter(Task::requirementsMet)
                        .findFirst();
                nextTask.ifPresent(task -> {
                    task.finished = true;
                    System.out.print(task.label);
                });
            } while (nextTask.isPresent());
        }

        void part2() {
            List<Task> tasksSorted = this.tasks.values().stream().sorted().collect(toList());
            WorkForce workForce = new WorkForce(5);
            int i = 0;
            while (!tasksSorted.stream().allMatch(Task::isFinished)) {
                tasksSorted.stream()
                        .filter(task -> !task.started)
                        .filter(Task::requirementsMet)
                        .forEach(workForce::submit);
                workForce.work();
                i++;
            }
            System.out.println(i);
        }

    }

    static class WorkForce {

        private List<Worker> workforce;

        WorkForce(int size) {
            workforce = IntStream.rangeClosed(1, size).mapToObj(Worker::new).collect(Collectors.toList());
        }

        void submit(Task task) {
            workforce.stream()
                    .filter(Worker::isAvailable)
                    .findFirst()
                    .ifPresent(worker -> worker.startOn(task));
        }

        void work() {
            workforce.forEach(Worker::work);
        }
    }

    static class Worker {

        private int number;
        private int secondsWorkingOnCurrentTask = 0;
        private Task task;


        Worker(int number) {
            this.number = number;
        }

        boolean isAvailable() {
            return task == null;
        }

        void startOn(Task task) {
            this.task = task;
            task.started = true;
        }

        void work() {
            if (isAvailable()) {
                return;
            }
            secondsWorkingOnCurrentTask++;
            if (secondsWorkingOnCurrentTask == task.duration) {
                task.finished = true;
                task = null;
                secondsWorkingOnCurrentTask = 0;
            }
        }

        private String status() {
            String pattern = "Worker #" + number + " %s";
            return isAvailable() ? String.format(pattern, "available") : String.format(pattern, "working on " + task.label);
        }
    }

    static class Task implements Comparable<Task> {

        private String label;
        private int duration;
        private boolean started = false;
        private boolean finished = false;
        private SortedSet<Task> requirements = new TreeSet<>();


        Task(String label) {
            this.label = label;
            duration = 60 + label.charAt(0) - 64;
        }

        void addRequirement(Task requirement) {
            requirements.add(requirement);
        }

        boolean requirementsMet() {
            return requirements.stream().allMatch(Task::isFinished);
        }

        boolean isFinished() {
            return finished;
        }

        void reset() {
            started = false;
            finished = false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return Objects.equals(label, task.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }

        @Override
        public int compareTo(Task o) {
            return label.compareTo(o.label);
        }

        @Override
        public String toString() {
            return "Task{" + label + ", d=" + duration + ", reqs=" + toSimpleString(requirements) + '}';
        }

        private String toSimpleString(Collection<Task> tasks) {
            return tasks.stream().map(task -> task.label).collect(Collectors.joining(","));
        }
    }
}
