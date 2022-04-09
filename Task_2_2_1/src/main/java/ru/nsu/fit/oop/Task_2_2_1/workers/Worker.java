package ru.nsu.fit.oop.Task_2_2_1.workers;

import java.util.concurrent.Callable;

public abstract class Worker implements Callable<Void> {

    // TODO: write Logger for Worker class

    private final long id;

    public Worker(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public Void call() {
        while (!Thread.currentThread().isInterrupted()) {
            work();
        }
        return null;
    }

    protected abstract void work();

}
