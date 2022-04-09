package ru.nsu.fit.oop.Task_2_2_1;

import ru.nsu.fit.oop.Task_2_2_1.util.UUID;

public class Order {

    private final UUID id;

    public Order(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
