package ru.nsu.fit.oop.Task_2_2_1.database.dto;

import java.util.Objects;

public final class BakerDTO implements DTO {
    public long id;
    public int workTime;

    public BakerDTO(long id, int workTime) {
        this.id = id;
        this.workTime = workTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BakerDTO) obj;
        return this.id == that.id &&
                this.workTime == that.workTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workTime);
    }

    @Override
    public String toString() {
        return "BakerDTO[" +
                "id=" + id + ", " +
                "workTime=" + workTime + ']';
    }

}
