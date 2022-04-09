package ru.nsu.fit.oop.Task_2_2_1.workers;

import ru.nsu.fit.oop.Task_2_2_1.Order;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;

public class Baker extends Worker {

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
    protected void work() {
        try {
            Order order = getOrder();
            makePizza();
            sendToStorage(order);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private Order getOrder() throws InterruptedException {
        Order order = orders.poll();
        System.out.println("Order #" + order.getId() + " is taken by baker #" + getId());

        return order;
    }

    private void makePizza() throws InterruptedException {
        Thread.sleep(workTime);
    }

    private void sendToStorage(Order order) throws InterruptedException {
        storage.add(order);
        System.out.println("Order #" + order.getId() + " is sent to warehouse by baker #" + getId());
    }

}
