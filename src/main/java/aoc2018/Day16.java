package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.copyOf;

public class Day16 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day16.txt"));
        Iterator<String> it = input.iterator();
        int n = 0;
        while (it.hasNext()) {
            String before = it.next();
            String command = it.next();
            String after = it.next();
            if (before.trim().isEmpty()) {
                break;
            }
            int[] initial = parseToState(before);
            int[] comm = parseToCommand(command);
            int matches = 0;
            for (Operation operation : Operation.values()) {
                int[] result = operation.apply(copyOf(initial, 4), comm);
                if (Arrays.equals(result, parseToState(after))) {
                    System.out.println(before);
                    System.out.println(command);
                    System.out.println(after);
                    System.out.println(operation.name());
                    System.out.println();
                    matches++;
                }
            }
            if (matches >= 3) {
                n++;
            }
        }
        System.out.println(n);
    }

    private static int[] parseToState(String line) {
        String[] parts = line.substring(9, 19).split(", ");
        return new int[]{
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
        };
    }

    private static int[] parseToCommand(String line) {
        String[] parts = line.split(" ");
        return new int[]{
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
        };
    }

    enum Operation {
        ADDR {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = state[command[1]] + state[command[2]];
                return state;
            }
        },
        ADDI {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = state[command[1]] + command[2];
                return state;
            }
        },
        MULR {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = state[command[1]] * state[command[2]];
                return state;
            }
        },
        MULI {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = state[command[1]] * command[2];
                return state;
            }
        },
        BANR {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = state[command[1]] & state[command[2]];
                return state;
            }
        },
        BANI {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = state[command[1]] & command[2];
                return state;
            }
        },
        BORR {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = state[command[1]] | state[command[2]];
                return state;
            }
        },
        BORI {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = state[command[1]] | command[2];
                return state;
            }
        },
        SETR {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = state[command[1]];
                return state;
            }
        },
        SETI {
            @Override
            public int[] apply(int[] state, int[] command) {
                state[command[3]] = command[1];
                return state;
            }
        },
        GTIR {
            @Override
            public int[] apply(int[] state, int[] command) {
                if (command[1] > state[command[2]]) {
                    state[command[3]] = 1;
                } else {
                    state[command[3]] = 0;
                }
                return state;
            }
        },
        GTRI {
            @Override
            public int[] apply(int[] state, int[] command) {
                if (state[command[1]] > command[2]) {
                    state[command[3]] = 1;
                } else {
                    state[command[3]] = 0;
                }
                return state;
            }
        },
        GTRR {
            @Override
            public int[] apply(int[] state, int[] command) {
                if (state[command[1]] > state[command[2]]) {
                    state[command[3]] = 1;
                } else {
                    state[command[3]] = 0;
                }
                return state;
            }
        },
        EQIR {
            @Override
            public int[] apply(int[] state, int[] command) {
                if (command[1] == state[command[2]]) {
                    state[command[3]] = 1;
                } else {
                    state[command[3]] = 0;
                }
                return state;
            }
        },
        EQRI {
            @Override
            public int[] apply(int[] state, int[] command) {
                if (state[command[1]] == command[2]) {
                    state[command[3]] = 1;
                } else {
                    state[command[3]] = 0;
                }
                return state;
            }
        },
        EQRR {
            @Override
            public int[] apply(int[] state, int[] command) {
                if (state[command[1]] == state[command[2]]) {
                    state[command[3]] = 1;
                } else {
                    state[command[3]] = 0;
                }
                return state;
            }
        };

        public abstract int[] apply(int[] state, int[] command);
    }
}
