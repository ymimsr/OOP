package ru.nsu.fit.oop.Task_2_2_1.workers;

import ru.nsu.fit.oop.Task_2_2_1.Order;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

public class Baker extends Worker {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    }

    private final IBlockingQueue<Order> orders;
    private final IBlockingQueue<Order> storage;

    private final long workTime;

    public Baker(long id, long workTime, IBlockingQueue<Order> orders, IBlockingQueue<Order> storage) {
        super(id);
        this.orders = orders;
        this.storage = storage;
        this.workTime = workTime;
    }

    @Override
    protected void work() throws InterruptedException {
        Order order = getOrder();
        makePizza();
        sendToStorage(order);
    }

    private Order getOrder() throws InterruptedException {
        Order order = orders.poll();
        LOGGER.info("Order #" + order.getId() + " is taken by baker #" + getId());

        return order;
    }

    private void makePizza() throws InterruptedException {
        Thread.sleep(workTime);
    }

    private void sendToStorage(Order order) throws InterruptedException {
        LOGGER.info("Order #" + order.getId() + " is sent to warehouse by baker #" + getId());
        storage.add(order);
    }

}
