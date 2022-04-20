package ru.nsu.fit.oop.Task_2_2_1.database.dao;

import com.google.gson.Gson;

public class GsonDAOFactory extends DAOFactory {

    private final Gson gson = new Gson();
    private String target;

    public GsonDAOFactory(String target) {
        this.target = target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public DeliverymanDAO getDeliverymanDAO() {
        return new GsonDeliverymanDAO(gson, target + "/deliverymen.json");
    }

    @Override
    public BakerDAO getBakerDAO() {
        return new GsonBakerDAO(gson, target + "/bakers.json");
    }


}
