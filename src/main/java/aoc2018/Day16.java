package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toList;

public class Day16 {

    private static final SortedMap<Integer, List<Operation>> OPCODE_TEMP_MAP = new TreeMap<>();
    private static final SortedMap<Integer, Operation> OPCODE_MAP = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/2018", "day16_1.txt"));
        Iterator<String> it = input.iterator();
        int i = 0;
        while (it.hasNext()) {
            int[] initial = parseToState(it.next());
            int[] comm = parseToCommand(it.next());
            int[] after = parseToState(it.next());
            Set<Operation> matches = new HashSet<>();
            for (Operation operation : Operation.values()) {
                int[] result = operation.apply(copyOf(initial, 4), comm);
                if (Arrays.equals(result, after)) {
                    matches.add(operation);
                }
            }
            if (matches.size() >= 3) {
                i++;
            }
            OPCODE_TEMP_MAP.merge(comm[0], new ArrayList<>(matches), (o, n) -> o.stream().filter(n::contains).collect(toList()));
        }
        System.out.println(i);
        System.out.println(OPCODE_TEMP_MAP);

        while (tempMapNotClear()) {
            for (Map.Entry<Integer, List<Operation>> entry : OPCODE_TEMP_MAP.entrySet()) {
                if (entry.getValue().size() == 1) {
                    Operation operation = entry.getValue().get(0);
                    OPCODE_MAP.put(entry.getKey(), operation);
                    for (List<Operation> operations : OPCODE_TEMP_MAP.values()) {
                        operations.remove(operation);
                    }
                }
            }
        }
        System.out.println(OPCODE_MAP);

        int[] state = new int[]{0, 0, 0, 0};
        List<String> commands = Files.readAllLines(Paths.get("src/main/resources/2018", "day16_2.txt"));
        for (String command : commands) {
            int[] comm = parseToCommand(command);
            state = OPCODE_MAP.get(comm[0])
                    .apply(state, comm);
        }
        System.out.println(Arrays.toString(state));
    }

    private static boolean tempMapNotClear() {
        return OPCODE_TEMP_MAP.values().stream().anyMatch(set -> set.size() != 0);
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
