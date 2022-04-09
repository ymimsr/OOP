package ru.nsu.fit.oop.Task_2_2_1.workers;

import ru.nsu.fit.oop.Task_2_2_1.Order;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;

import java.util.List;

public class Deliveryman extends Worker {

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
    protected void work() {
        try {
            List<Order> orders = getOrders();
            deliverOrder(orders);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private List<Order> getOrders() throws InterruptedException {
        List<Order> orders = storage.pollAvailable(carCapacity);
        for (Order order : orders) {
            System.out.println("Order #" + order.getId() + " is taken by deliveryman #" + getId());
        }

        return orders;
    }

    private void deliverOrder(List<Order> orders) throws InterruptedException {
        Thread.sleep(workTime);
        for (Order order : orders) {
            System.out.println("Order # " + order.getId() + " has been delivered by deliveryman #" + getId());
        }
    }
}
