package ru.nsu.fit.oop.Task_2_2_1.workers;

import ru.nsu.fit.oop.Task_2_2_1.Order;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;
import ru.nsu.fit.oop.Task_2_2_1.util.UUID;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

public class OrderSubmitter extends Worker {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    }

    private final IBlockingQueue<Order> orders;

    public OrderSubmitter(long id, IBlockingQueue<Order> orders) {
        super(id);
        this.orders = orders;
    }

    @Override
    protected void work() throws InterruptedException {
        Order order = generateOrder();
        sendOrder(order);
        Thread.sleep((int) Math.round(Math.random() * 300));
    }

    private Order generateOrder() {
        return new Order(UUID.getNext());
    }

    private void sendOrder(Order order) throws InterruptedException {
        LOGGER.info("Order #" + order.getId() + " has been received.");
        orders.add(order);
    }

}
