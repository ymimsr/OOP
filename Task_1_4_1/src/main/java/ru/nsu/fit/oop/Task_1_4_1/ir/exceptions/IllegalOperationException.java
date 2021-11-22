package ru.nsu.fit.oop.Task_1_4_1.ir.exceptions;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException() {
        super("Invalid operation");
    }
}
