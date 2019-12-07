package aoc2019.intcode;

import java.util.ArrayList;
import java.util.List;

public class Computer {

    private int instructionPointer;
    private int inputPointer;
    private int[] intCode;
    private List<Integer> outputs;

    public Computer(int[] intCode) {
        this.instructionPointer = 0;
        this.inputPointer = 0;
        this.intCode = intCode;
        this.outputs = new ArrayList<>();
    }

    public static List<Integer> run(int[] intCode, int... input) {
        int[] intCodeCopy = new int[intCode.length];
        System.arraycopy(intCode, 0, intCodeCopy, 0, intCode.length);
        return new Computer(intCodeCopy).run(input);
    }

    @SuppressWarnings("DuplicatedCode")
    private List<Integer> run(int... input) {
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
                intCode[writeAddress] = input[inputPointer++];
            } else if (opcode == 4) {
                outputs.add(getValue(parameterMode1));
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
                return outputs;
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
