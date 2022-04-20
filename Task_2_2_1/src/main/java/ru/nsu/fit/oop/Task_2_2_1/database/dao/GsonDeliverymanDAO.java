package ru.nsu.fit.oop.Task_2_2_1.database.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.nsu.fit.oop.Task_2_2_1.Order;
import ru.nsu.fit.oop.Task_2_2_1.database.dto.BakerDTO;
import ru.nsu.fit.oop.Task_2_2_1.database.dto.DeliverymanDTO;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;
import ru.nsu.fit.oop.Task_2_2_1.workers.Baker;
import ru.nsu.fit.oop.Task_2_2_1.workers.Deliveryman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GsonDeliverymanDAO implements DeliverymanDAO {

    private final Gson gson;
    private final String target;

    public GsonDeliverymanDAO(Gson gson, String target) {
        this.gson = gson;
        this.target = target;
    }

    private List<DeliverymanDTO> deliverymenDTO = new ArrayList<>();

    @Override
    public Deliveryman get(long id, IBlockingQueue<Order> storage) {
        if (!isInit)
            fetchData(target);

        Optional<DeliverymanDTO> optionalDeliverymanDTO = deliverymenDTO.stream()
                .filter(deliverymanDTO -> deliverymanDTO.id == id)
                .findFirst();

        if (optionalDeliverymanDTO.isEmpty())
            return null;

        DeliverymanDTO deliverymanDTO = optionalDeliverymanDTO.get();

        return new Deliveryman(deliverymanDTO.id, deliverymanDTO.workTime, deliverymanDTO.carCapacity, storage);
    }

    @Override
    public List<Deliveryman> getAll(IBlockingQueue<Order> storage) {
        if (!isInit)
            fetchData(target);

        List<Deliveryman> deliverymen = new ArrayList<>();
        for (DeliverymanDTO deliverymanDTO : deliverymenDTO) {
            deliverymen.add(new Deliveryman(
                    deliverymanDTO.id,
                    deliverymanDTO.workTime,
                    deliverymanDTO.carCapacity,
                    storage
            ));
        }

        return deliverymen;
    }

    private boolean isInit = false;

    private void fetchData(String target) {
        Type listOfDeliverymanDTO = new TypeToken<ArrayList<DeliverymanDTO>>() {}.getType();
        try {
            deliverymenDTO = gson.fromJson(new FileReader(target), listOfDeliverymanDTO);
            isInit = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
