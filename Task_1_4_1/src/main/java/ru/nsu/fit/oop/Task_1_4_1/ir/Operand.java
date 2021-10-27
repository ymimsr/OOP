package ru.nsu.fit.oop.Task_1_4_1.ir;

import ru.nsu.fit.oop.Task_1_4_1.ir.Expression;
import ru.nsu.fit.oop.Task_1_4_1.ir.Token;

public class Operand extends Expression {

    public final Token.OperandToken value;

    public Operand(Token.OperandToken value) {
        this.value = value;
    }

    @Override
    public double calc() {
        return value.value;
    }

}
