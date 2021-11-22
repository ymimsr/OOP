package ru.nsu.fit.oop.Task_1_4_1;

import org.apache.commons.math3.complex.Complex;

public class CalculatorMain {

    private static final Calculator calculator = new Calculator();
    private static final Lexer lexer = new Lexer();

    public static Complex start(String input) {
        return calculator.calculate(lexer.tokenize(input.split(" ")));
    }

    public static Complex start() {
        return calculator.calculate(lexer.tokenize());
    }
}
