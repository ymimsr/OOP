package ru.nsu.fit.oop.Task_2_2_1;

import ru.nsu.fit.oop.Task_2_2_1.database.dao.DAOFactory;
import ru.nsu.fit.oop.Task_2_2_1.database.dao.GsonDAOFactory;
import ru.nsu.fit.oop.Task_2_2_1.queue.MyBlockingQueue;

public class Application {

    public static void main(String[] args) {
        DAOFactory daoFactory = new GsonDAOFactory("Task_2_2_1/src/main/resources/configs");
        Pizzeria pizzeria = new Pizzeria(daoFactory);

        pizzeria.openPizzeria(new MyBlockingQueue<>(), new MyBlockingQueue<>());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        pizzeria.closePizzeria();
    }

}
