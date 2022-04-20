package ru.nsu.fit.oop.Task_2_2_1.database.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.nsu.fit.oop.Task_2_2_1.Order;
import ru.nsu.fit.oop.Task_2_2_1.database.dto.BakerDTO;
import ru.nsu.fit.oop.Task_2_2_1.queue.IBlockingQueue;
import ru.nsu.fit.oop.Task_2_2_1.workers.Baker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GsonBakerDAO implements BakerDAO {

    private final Gson gson;
    private final String target;

    public GsonBakerDAO(Gson gson, String target) {
        this.gson = gson;
        this.target = target;
    }

    private List<BakerDTO> bakerDTOS = new ArrayList<>();

    @Override
    public Baker get(long id, IBlockingQueue<Order> orders, IBlockingQueue<Order> storage) {
        if (!isInit)
            fetchData(target);

        Optional<BakerDTO> optionalBakerDTO = bakerDTOS.stream()
                .filter(bakerDTO -> bakerDTO.id == id)
                .findFirst();

        if (optionalBakerDTO.isEmpty())
            return null;

        BakerDTO bakerDTO = optionalBakerDTO.get();

        return new Baker(bakerDTO.id, bakerDTO.workTime, orders, storage);
    }

    @Override
    public List<Baker> getAll(IBlockingQueue<Order> orders, IBlockingQueue<Order> queue) {
        if (!isInit)
            fetchData(target);

        List<Baker> bakers = new ArrayList<>();
        for (BakerDTO bakerDTO : bakerDTOS) {
            bakers.add(new Baker(bakerDTO.id, bakerDTO.workTime, orders, queue));
        }

        return bakers;
    }

    private boolean isInit = false;

    private void fetchData(String target) {
        Type listOfBakerDTO = new TypeToken<ArrayList<BakerDTO>>() {}.getType();
        try {
            bakerDTOS = gson.fromJson(new FileReader(target), listOfBakerDTO);
            isInit = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
