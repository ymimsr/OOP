package ru.nsu.fit.oop.Task_2_2_1.database.dao;

import ru.nsu.fit.oop.Task_2_2_1.Order;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;
import ru.nsu.fit.oop.Task_2_2_1.workers.Baker;
import ru.nsu.fit.oop.Task_2_2_1.workers.Deliveryman;

import java.util.List;

public interface BakerDAO {

    Baker get(long id, IBlockingQueue<Order> orders, IBlockingQueue<Order> storage);
    List<Baker> getAll(IBlockingQueue<Order> orders, IBlockingQueue<Order> queue);

}
