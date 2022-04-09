package ru.nsu.fit.oop.Task_2_2_1.database.dto;

import java.util.Objects;

public final class DeliverymanDTO implements DTO {
    public long id;
    public int workTime;
    public int carCapacity;

    public DeliverymanDTO(long id, int workTime, int carCapacity) {
        this.id = id;
        this.workTime = workTime;
        this.carCapacity = carCapacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DeliverymanDTO) obj;
        return this.id == that.id &&
                this.workTime == that.workTime &&
                this.carCapacity == that.carCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workTime, carCapacity);
    }

    @Override
    public String toString() {
        return "DeliverymanDTO[" +
                "id=" + id + ", " +
                "workTime=" + workTime + ", " +
                "carCapacity=" + carCapacity + ']';
    }

}