package ru.nsu.fit.oop.Task_1_4_1;

import java.util.Scanner;

public class CalculatorMain {

    private final Lexer lexer;
    private final Calculator calculator;

    public CalculatorMain() {
        this.lexer = new Lexer();
        this.calculator = new Calculator();
    }

    public void startCalculator() {
        System.out.print("Write an expression in prefix notation: ");
        double answer = calculator.calculate(lexer.tokenize());
        System.out.println("Answer is " + answer);
    }

    public static void main(String[] args) {
        CalculatorMain calculatorMain = new CalculatorMain();
        calculatorMain.startCalculator();
    }

}
