package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day19 {

    private static final List<Command> COMMANDS = new ArrayList<>();
    private static final int IP_REGISTER = 2;

    private static int IP = 0;

    public static void main(String[] args) throws IOException {
        parse();
        int[] state = new int[]{0, 0, 0, 0, 0, 0};
        while (IP < COMMANDS.size()) {
            state[IP_REGISTER] = IP;
            state = COMMANDS.get(IP).execute(state);
            IP = state[IP_REGISTER] + 1;
        }
        System.out.println(Arrays.toString(state));
    }

    private static void parse() throws IOException {
        Files.readAllLines(Paths.get("src/main/resources/2018", "day19.txt")).stream()
                .map(Command::new)
                .forEach(COMMANDS::add);
    }

    private static class Command {

        Operation operation;
        int[] command;

        Command(String s) {
            String[] parts = s.split(" ");
            operation = Operation.valueOf(parts[0].toUpperCase());
            command = new int[]{
                    0,
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]),
            };
        }

        int[] execute(int[] state) {
            return operation.apply(Arrays.copyOf(state, state.length), command);
        }
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
