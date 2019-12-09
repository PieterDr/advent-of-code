package aoc2019.intcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.lang.Math.toIntExact;

public class Computer {

    private long instructionPointer;
    private long inputPointer;
    private long relativeBase;
    private Map<Long, Long> memory;
    private List<Long> outputs;

    public Computer(long[] intCode) {
        this.instructionPointer = 0;
        this.inputPointer = 0;
        this.relativeBase = 0;
        this.outputs = new ArrayList<>();
        this.memory = new HashMap<>();
        IntStream.range(0, intCode.length).forEach(i -> memory.put((long) i, intCode[i]));
    }

    public static List<Long> run(long[] intCode, long... input) {
        return new Computer(intCode).run(input);
    }

    private List<Long> run(long... input) {
        run(() -> input[toIntExact(inputPointer++)], outputs::add);
        return outputs;
    }

    protected void run(Supplier<Long> inputSupplier, Consumer<Long> outputConsumer) {
        while (true) {
            long command = memory.getOrDefault(instructionPointer, 0L);
            long opcode = command % 100;
            long parameterMode1 = command % 1000 / 100;
            long parameterMode2 = command % 10000 / 1000;
            long parameterMode3 = command / 10000;
            if (opcode == 1) {
                long a = getParameter(parameterMode1);
                long b = getParameter(parameterMode2);
                long writeAddress = getWriteAddress(parameterMode3);
                memory.put(writeAddress, a + b);
            } else if (opcode == 2) {
                long a = getParameter(parameterMode1);
                long b = getParameter(parameterMode2);
                long writeAddress = getWriteAddress(parameterMode3);
                memory.put(writeAddress, a * b);
            } else if (opcode == 3) {
                long writeAddress = getWriteAddress(parameterMode1);
                memory.put(writeAddress, inputSupplier.get());
            } else if (opcode == 4) {
                outputConsumer.accept(getParameter(parameterMode1));
            } else if (opcode == 5) {
                long param1 = getParameter(parameterMode1);
                long param2 = getParameter(parameterMode2);
                if (param1 != 0) {
                    instructionPointer = param2;
                    continue;
                }
            } else if (opcode == 6) {
                long param1 = getParameter(parameterMode1);
                long param2 = getParameter(parameterMode2);
                if (param1 == 0) {
                    instructionPointer = param2;
                    continue;
                }
            } else if (opcode == 7) {
                long a = getParameter(parameterMode1);
                long b = getParameter(parameterMode2);
                long writeAddress = getWriteAddress(parameterMode3);
                memory.put(writeAddress, a < b ? 1L : 0);
            } else if (opcode == 8) {
                long a = getParameter(parameterMode1);
                long b = getParameter(parameterMode2);
                long writeAddress = getWriteAddress(parameterMode3);
                memory.put(writeAddress, a == b ? 1L : 0);
            } else if (opcode == 9) {
                relativeBase += getParameter(parameterMode1);
            } else if (opcode == 99) {
                return;
            } else {
                throw new RuntimeException("Invalid opcode encountered: " + opcode + " at address " + instructionPointer);
            }
            instructionPointer++;
        }
    }

    private long getParameter(long parameterMode) {
        long memoryValue = memory.getOrDefault(++instructionPointer, 0L);
        return switch (toIntExact(parameterMode)) {
            case 0 -> memory.getOrDefault(memoryValue, 0L);
            case 1 -> memoryValue;
            case 2 -> memory.getOrDefault(memoryValue + relativeBase, 0L);
            default -> throw new RuntimeException("Invalid parameterMode: " + parameterMode);
        };
    }

    private long getWriteAddress(long parameterMode) {
        long memoryValue = memory.getOrDefault(++instructionPointer, 0L);
        return switch (toIntExact(parameterMode)) {
            case 0 -> memoryValue;
            case 2 -> memoryValue + relativeBase;
            default -> throw new RuntimeException("Invalid parameterMode: " + parameterMode);
        };
    }
}
