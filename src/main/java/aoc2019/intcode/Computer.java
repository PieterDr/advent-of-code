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
    private Map<Long, Long> memory;
    private List<Long> outputs;

    public Computer(long[] intCode) {
        this.instructionPointer = 0;
        this.inputPointer = 0;
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
            long command = memory.get(instructionPointer);
            long opcode = command % 100;
            long parameterMode1 = command % 1000 / 100;
            long parameterMode2 = command % 10000 / 1000;
            long parameterMode3 = command / 10000;
            if (opcode == 1) {
                long a = getValue(parameterMode1);
                long b = getValue(parameterMode2);
                long writeAddress = getWriteAddress(parameterMode3);
                memory.put(writeAddress, a + b);
            } else if (opcode == 2) {
                long a = getValue(parameterMode1);
                long b = getValue(parameterMode2);
                long writeAddress = getWriteAddress(parameterMode3);
                memory.put(writeAddress, a * b);
            } else if (opcode == 3) {
                long writeAddress = getWriteAddress(parameterMode1);
                memory.put(writeAddress, inputSupplier.get());
            } else if (opcode == 4) {
                outputConsumer.accept(getValue(parameterMode1));
            } else if (opcode == 5) {
                long param1 = getValue(parameterMode1);
                long param2 = getValue(parameterMode2);
                if (param1 != 0) {
                    instructionPointer = param2;
                    continue;
                }
            } else if (opcode == 6) {
                long param1 = getValue(parameterMode1);
                long param2 = getValue(parameterMode2);
                if (param1 == 0) {
                    instructionPointer = param2;
                    continue;
                }
            } else if (opcode == 7) {
                long a = getValue(parameterMode1);
                long b = getValue(parameterMode2);
                long writeAddress = getWriteAddress(parameterMode3);
                memory.put(writeAddress, a < b ? 1L : 0);
            } else if (opcode == 8) {
                long a = getValue(parameterMode1);
                long b = getValue(parameterMode2);
                long writeAddress = getWriteAddress(parameterMode3);
                memory.put(writeAddress, a == b ? 1L : 0);
            } else if (opcode == 99) {
                return;
            } else {
                throw new RuntimeException("Invalid opcode encountered: " + opcode + " at address " + instructionPointer);
            }
            instructionPointer++;
        }
    }

    private long getValue(long parameterMode) {
        long memoryValue = memory.get(++instructionPointer);
        return parameterMode == 0 ? memory.get(memoryValue) : memoryValue;
    }

    private long getWriteAddress(long parameterMode) {
        return memory.get(++instructionPointer);
    }
}
