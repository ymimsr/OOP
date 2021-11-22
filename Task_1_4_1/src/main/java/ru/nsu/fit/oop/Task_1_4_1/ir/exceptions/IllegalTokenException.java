package ru.nsu.fit.oop.Task_1_4_1.ir.exceptions;

public class IllegalTokenException extends RuntimeException {
    public IllegalTokenException() {
        super("Invalid token");
    }
}
