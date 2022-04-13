package ru.nsu.fit.oop.Task_2_2_1.workers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerExecutor<T extends Worker> {

    private final List<T> workers;
    private final ExecutorService executor;

    public WorkerExecutor(List<T> workers, int poolSize) {
        this.workers = workers;
        executor = Executors.newFixedThreadPool(poolSize);
    }

    public void start() {
        for (Worker worker : workers) {
            executor.execute(worker);
        }
    }

    public void stop() {
        executor.shutdownNow();
    }

}
