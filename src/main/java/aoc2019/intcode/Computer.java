package aoc2019.intcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Computer extends Thread {

    private int instructionPointer;
    private int inputPointer;
    private int[] intCode;
    private List<Integer> outputs;
    private BlockingQueue<Integer> inputQueue;
    private BlockingQueue<Integer> outputQueue;

    public Computer(int[] intCode) {
        this.instructionPointer = 0;
        this.inputPointer = 0;
        this.intCode = new int[intCode.length];
        System.arraycopy(intCode, 0, this.intCode, 0, intCode.length);
        this.outputs = new ArrayList<>();
    }

    public Computer(int[] intCode, BlockingQueue<Integer> inputQueue, BlockingQueue<Integer> outputQueue) {
        this.instructionPointer = 0;
        this.inputPointer = 0;
        this.intCode = new int[intCode.length];
        System.arraycopy(intCode, 0, this.intCode, 0, intCode.length);
        this.outputs = new ArrayList<>();
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    public static List<Integer> run(int[] intCode, int... input) {
        return new Computer(intCode).run(input);
    }

    private List<Integer> run(int... input) {
        run(() -> input[inputPointer++], outputs::add);
        return outputs;
    }

    @Override
    public void run() {
        run(this::takeInput, this::putOutput);
    }

    private void putOutput(Integer output) {
        try {
            outputQueue.put(output);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int takeInput() {
        try {
            return inputQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void run(Supplier<Integer> inputSupplier, Consumer<Integer> outputConsumer) {
        while (true) {
            int command = intCode[instructionPointer];
            int opcode = command % 100;
            int parameterMode2 = command % 10000 / 1000;
            int parameterMode1 = command % 1000 / 100;
            if (opcode == 1) {
                int a = getValue(parameterMode1);
                int b = getValue(parameterMode2);
                int outputAddress = intCode[++instructionPointer];
                intCode[outputAddress] = a + b;
            } else if (opcode == 2) {
                int a = getValue(parameterMode1);
                int b = getValue(parameterMode2);
                int outputAddress = intCode[++instructionPointer];
                intCode[outputAddress] = a * b;
            } else if (opcode == 3) {
                int writeAddress = intCode[++instructionPointer];
                intCode[writeAddress] = inputSupplier.get();
            } else if (opcode == 4) {
                outputConsumer.accept(getValue(parameterMode1));
            } else if (opcode == 5) {
                int param1 = getValue(parameterMode1);
                int param2 = getValue(parameterMode2);
                if (param1 != 0) {
                    instructionPointer = param2;
                    continue;
                }
            } else if (opcode == 6) {
                int param1 = getValue(parameterMode1);
                int param2 = getValue(parameterMode2);
                if (param1 == 0) {
                    instructionPointer = param2;
                    continue;
                }
            } else if (opcode == 7) {
                int a = getValue(parameterMode1);
                int b = getValue(parameterMode2);
                int outputAddress = intCode[++instructionPointer];
                intCode[outputAddress] = a < b ? 1 : 0;
            } else if (opcode == 8) {
                int a = getValue(parameterMode1);
                int b = getValue(parameterMode2);
                int outputAddress = intCode[++instructionPointer];
                intCode[outputAddress] = a == b ? 1 : 0;
            } else if (opcode == 99) {
                return;
            } else {
                throw new RuntimeException("Invalid opcode encountered: " + opcode + " at address " + instructionPointer);
            }
            instructionPointer++;
        }
    }

    private int getValue(int parameterMode1) {
        return parameterMode1 == 0 ? intCode[intCode[++instructionPointer]] : intCode[++instructionPointer];
    }
}
