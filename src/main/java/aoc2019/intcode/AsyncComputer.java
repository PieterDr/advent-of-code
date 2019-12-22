package aoc2019.intcode;

import java.util.concurrent.BlockingQueue;

public class AsyncComputer extends Computer implements Runnable {

    public BlockingQueue<Long> inputQueue;
    public BlockingQueue<Long> outputQueue;
    private boolean finished = false;

    public AsyncComputer(long[] intCode, BlockingQueue<Long> inputQueue, BlockingQueue<Long> outputQueue) {
        super(intCode);
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        run(this::takeInput, this::putOutput);
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }

    public void join() throws InterruptedException {
        while (!finished) {
            Thread.sleep(5);
        }
    }

    private void putOutput(long output) {
        try {
            outputQueue.put(output);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private long takeInput() {
        try {
            return inputQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
