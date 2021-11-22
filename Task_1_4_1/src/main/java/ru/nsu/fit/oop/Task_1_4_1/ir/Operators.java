package ru.nsu.fit.oop.Task_1_4_1.ir;

import ru.nsu.fit.oop.Task_1_4_1.ir.exceptions.IllegalOperationException;

public enum Operators {

    ADD,  // +
    SUB,  // -
    MUL,  // *
    DIV,  // /
    SIN,  // sin
    COS,  // cos
    LOG,  // log
    POW,  // exponentiation
    SQRT; // square root

    public static Operators getOperator(String sValue) {
        return switch (sValue) {
            case "+" -> ADD;
            case "-" -> SUB;
            case "*" -> MUL;
            case "/" -> DIV;
            case "sin" -> SIN;
            case "cos" -> COS;
            case "log" -> LOG;
            case "pow" -> POW;
            case "sqrt" -> SQRT;
            default -> throw new IllegalOperationException();
        };
    }
}
