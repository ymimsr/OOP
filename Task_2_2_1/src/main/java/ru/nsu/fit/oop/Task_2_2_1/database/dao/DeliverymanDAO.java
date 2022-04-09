package ru.nsu.fit.oop.Task_2_2_1.database.dao;

import ru.nsu.fit.oop.Task_2_2_1.Order;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;
import ru.nsu.fit.oop.Task_2_2_1.workers.Deliveryman;

import java.util.List;

public interface DeliverymanDAO {

    Deliveryman get(long id, IBlockingQueue<Order> storage);
    List<Deliveryman> getAll(IBlockingQueue<Order> storage);

}
