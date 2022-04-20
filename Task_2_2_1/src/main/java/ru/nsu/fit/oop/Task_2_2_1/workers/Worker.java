package ru.nsu.fit.oop.Task_2_2_1.workers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public abstract class Worker implements Runnable {

    private final long id;

    public Worker(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    static {
        try (FileInputStream fileInputStream = new FileInputStream(
                "Task_2_2_1/src/main/resources/configs/log/worker.config"
        )) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                work();
            } catch (InterruptedException ex) {
                System.out.println(this + " finished working.");
                break;
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " #" + id;
    }

    protected abstract void work() throws InterruptedException;

}
