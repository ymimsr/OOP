package ru.nsu.fit.oop.Task_2_2_1.queue;

import java.util.Collection;
import java.util.List;

public interface IBlockingQueue<E> {

    void add(E e) throws InterruptedException;
    E poll() throws InterruptedException;
    List<E> pollAvailable(int maxSize) throws InterruptedException;
    boolean isEmpty();

}
