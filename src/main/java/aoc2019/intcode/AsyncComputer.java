package aoc2019.intcode;

import java.util.concurrent.BlockingQueue;

public class AsyncComputer extends Computer implements Runnable {

    private BlockingQueue<Integer> inputQueue;
    private BlockingQueue<Integer> outputQueue;
    private boolean finished = false;

    public AsyncComputer(int[] intCode, BlockingQueue<Integer> inputQueue, BlockingQueue<Integer> outputQueue) {
        super(intCode);
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        run(this::takeInput, this::putOutput);
        finished = true;
    }

    public void join() throws InterruptedException {
        while (!finished) {
            Thread.sleep(5);
        }
    }

    private void putOutput(int output) {
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
}
