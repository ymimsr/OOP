package ru.nsu.fit.oop.Task_2_2_1.util;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class UUID {

    private static final AtomicLong COUNTER = new AtomicLong(0);

    private final long id;

    private UUID(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UUID uuid = (UUID) o;
        return id == uuid.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public static UUID getNext() {
        return new UUID(COUNTER.getAndIncrement());
    }

}
