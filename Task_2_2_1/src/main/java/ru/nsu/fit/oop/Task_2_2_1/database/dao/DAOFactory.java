package ru.nsu.fit.oop.Task_2_2_1.database.dao;

public abstract class DAOFactory {

    public abstract DeliverymanDAO getDeliverymanDAO();
    public abstract BakerDAO getBakerDAO();

}
