package ru.nsu.fit.oop.Task_2_2_1.queue;

import java.util.*;

public class MyBlockingQueue<E> implements IBlockingQueue<E> {

    private final Queue<E> queue = new LinkedList<>();
    private final int maxCapacity;

    public MyBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    public MyBlockingQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public synchronized void add(E e) throws InterruptedException {
        while (maxCapacity == queue.size()) {
            this.wait();
        }
        queue.add(e);
        this.notifyAll();
    }

    @Override
    public E poll() throws InterruptedException {
        List<E> pollElemCol = pollAvailable(1);

        return pollElemCol.get(0);
    }

    @Override
    public synchronized List<E> pollAvailable(int maxSize) throws InterruptedException {
        while (queue.size() == 0)
            this.wait();

        int availableQuantity = Math.min(maxSize, queue.size());
        List<E> available = new ArrayList<>();

        for (int i = 0; i < availableQuantity; i++) {
            available.add(queue.poll());
        }
        this.notifyAll();

        return available;
    }

    @Override
    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
