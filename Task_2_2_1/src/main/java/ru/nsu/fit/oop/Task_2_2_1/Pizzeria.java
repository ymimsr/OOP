package ru.nsu.fit.oop.Task_2_2_1;

import ru.nsu.fit.oop.Task_2_2_1.database.dao.BakerDAO;
import ru.nsu.fit.oop.Task_2_2_1.database.dao.DAOFactory;
import ru.nsu.fit.oop.Task_2_2_1.database.dao.DeliverymanDAO;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;
import ru.nsu.fit.oop.Task_2_2_1.util.UUID;
import ru.nsu.fit.oop.Task_2_2_1.workers.Baker;
import ru.nsu.fit.oop.Task_2_2_1.workers.Deliveryman;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// TODO: write stop method for executors
public class Pizzeria {

    private List<Baker> bakers;
    private List<Deliveryman> deliverymen;

    private final BakerDAO bakerDAO;
    private final DeliverymanDAO deliverymanDAO;

    private IBlockingQueue<Order> orders;
    private IBlockingQueue<Order> storage;

    public Pizzeria(DAOFactory daoFactory, IBlockingQueue<Order> orders, IBlockingQueue<Order> storage) {
        this.orders = orders;
        this.storage = storage;

        bakerDAO = daoFactory.getBakerDAO();
        deliverymanDAO = daoFactory.getDeliverymanDAO();

        bakers = bakerDAO.getAll(orders, storage);
        deliverymen = deliverymanDAO.getAll(storage);
    }

    // TODO: Decompose this method
    public void openPizzeria() {
        ExecutorService bakersExecutor = Executors.newFixedThreadPool(bakers.size());
        ExecutorService deliverymenExecutor = Executors.newFixedThreadPool(deliverymen.size());
        ExecutorService orderSubmitter = Executors.newSingleThreadExecutor();
        orderSubmitter.execute(() -> {
            try {
                while (true) {
                    // TODO: write OrderSubmitter class
                    Order newOrder = new Order(UUID.getNext());
                    System.out.println("Order #" + newOrder.getId() + " has been received");
                    orders.add(newOrder);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        new Thread(() -> {
            try {
                bakersExecutor.invokeAll(bakers);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                deliverymenExecutor.invokeAll(deliverymen);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
