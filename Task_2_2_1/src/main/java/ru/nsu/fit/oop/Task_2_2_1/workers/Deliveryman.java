package ru.nsu.fit.oop.Task_2_2_1.workers;

import ru.nsu.fit.oop.Task_2_2_1.Order;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

public class Deliveryman extends Worker {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    }

    private final IBlockingQueue<Order> storage;

    private final int workTime;
    private final int carCapacity;

    public Deliveryman(long id, int workTime, int carCapacity, IBlockingQueue<Order> storage) {
        super(id);
        this.workTime = workTime;
        this.carCapacity = carCapacity;
        this.storage = storage;
    }

    @Override
    protected void work() throws InterruptedException {
        List<Order> orders = getOrders();
        deliverOrder(orders);
    }

    private List<Order> getOrders() throws InterruptedException {
        List<Order> orders = storage.pollAvailable(carCapacity);
        for (Order order : orders) {
            LOGGER.info(("Order #" + order.getId() + " is taken by deliveryman #" + getId()));
        }

        return orders;
    }

    private void deliverOrder(List<Order> orders) throws InterruptedException {
        Thread.sleep(workTime);
        for (Order order : orders) {
            LOGGER.info("Order #" + order.getId() + " has been delivered by deliveryman #" + getId());
        }
    }
}
