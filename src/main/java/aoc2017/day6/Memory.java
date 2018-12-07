package aoc2017.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Memory {

    private List<Integer> blocks;

    public Memory(List<Integer> blocks) {
        this.blocks = blocks;
    }

    public Memory(Integer... blocks) {
        this.blocks = asList(blocks);
    }

    public Memory performRedistribution() {
        List<Integer> blocksCopy = new ArrayList<>(blocks);
        int maxValue = blocksCopy.stream()
                .mapToInt(a -> a)
                .max().getAsInt();
        return new Memory(redistributeBlock(blocksCopy, blocksCopy.indexOf(maxValue)));
    }

    private Integer[] redistributeBlock(List<Integer> blocks, int block) {
        Integer[] redistributedBlocks = toIntArray(blocks);
        redistributedBlocks[block] = 0;
        int nextBlock = block + 1;
        for (int i = 0; i < blocks.get(block); i++) {
            if (nextBlock == redistributedBlocks.length) {
                nextBlock = 0;
            }
            redistributedBlocks[nextBlock]++;
            nextBlock++;
        }
        return redistributedBlocks;
    }

    private Integer[] toIntArray(List<Integer> blocks) {
        Integer[] array = new Integer[blocks.size()];
        for(int i = 0; i < blocks.size(); i++) {
            array[i] = blocks.get(i);
        }
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Memory memory = (Memory) o;
        return Objects.equals(blocks, memory.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blocks);
    }
}
