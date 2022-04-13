package ru.nsu.fit.oop.Task_2_2_1;

import ru.nsu.fit.oop.Task_2_2_1.database.dao.BakerDAO;
import ru.nsu.fit.oop.Task_2_2_1.database.dao.DAOFactory;
import ru.nsu.fit.oop.Task_2_2_1.database.dao.DeliverymanDAO;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;
import ru.nsu.fit.oop.Task_2_2_1.workers.Baker;
import ru.nsu.fit.oop.Task_2_2_1.workers.Deliveryman;
import ru.nsu.fit.oop.Task_2_2_1.workers.OrderSubmitter;
import ru.nsu.fit.oop.Task_2_2_1.workers.WorkerExecutor;

import java.util.ArrayList;
import java.util.List;

public class Pizzeria {

    private List<Baker> bakers;
    private List<Deliveryman> deliverymen;
    private List<OrderSubmitter> orderSubmitters;

    private final BakerDAO bakerDAO;
    private final DeliverymanDAO deliverymanDAO;

    public Pizzeria(DAOFactory daoFactory) {
        bakerDAO = daoFactory.getBakerDAO();
        deliverymanDAO = daoFactory.getDeliverymanDAO();
    }

    private void init(
            IBlockingQueue<Order> orders,
            IBlockingQueue<Order> storage
    ) {
        bakers = bakerDAO.getAll(orders, storage);
        deliverymen = deliverymanDAO.getAll(storage);
        orderSubmitters = new ArrayList<>();
        orderSubmitters.add(new OrderSubmitter(1, orders));
    }


    private WorkerExecutor<Baker> bakerExecutor;
    private WorkerExecutor<Deliveryman> deliverymanExecutor;
    private WorkerExecutor<OrderSubmitter> orderSubmitterExecutor;

    public void openPizzeria(
            IBlockingQueue<Order> orders,
            IBlockingQueue<Order> storage
    ) {
        init(orders, storage);

        int[] threadPoolSizes = calcThreadPoolSizes();
        bakerExecutor = new WorkerExecutor<>(bakers, threadPoolSizes[0]);
        deliverymanExecutor = new WorkerExecutor<>(deliverymen, threadPoolSizes[1]);
        orderSubmitterExecutor = new WorkerExecutor<>(orderSubmitters, 1);

        bakerExecutor.start();
        deliverymanExecutor.start();
        orderSubmitterExecutor.start();
    }

    public void closePizzeria() {
        orderSubmitterExecutor.stop();
        bakerExecutor.stop();
        deliverymanExecutor.stop();
    }

    private int[] calcThreadPoolSizes() {
        int[] threadPoolSizes = new int[2];

        int availableProcessors = Runtime.getRuntime().availableProcessors() + 1;
        threadPoolSizes[0] =
                bakers.size() * availableProcessors / (bakers.size() + deliverymen.size());
        threadPoolSizes[1] =
                deliverymen.size() * availableProcessors / (bakers.size() + deliverymen.size());

        return threadPoolSizes;
    }

}
