package ru.nsu.fit.oop.Task_1_4_1.ir;

import org.apache.commons.math3.complex.Complex;
import ru.nsu.fit.oop.Task_1_4_1.ir.exceptions.IllegalOperationException;

public class BinaryOperation extends Expression {

    public final Token.OperatorToken operatorToken;
    public final Expression left;
    public final Expression right;

    public BinaryOperation(Token.OperatorToken operatorToken, Expression left, Expression right) {
        this.operatorToken = operatorToken;
        this.left = left;
        this.right = right;
    }

    @Override
    public Complex calc() {
        return switch (operatorToken.operator) {
            case ADD -> left.calc().add(right.calc());
            case SUB -> left.calc().subtract(right.calc());
            case MUL -> left.calc().multiply(right.calc());
            case DIV -> left.calc().divide(right.calc());
            case POW -> left.calc().pow(right.calc());
            default -> throw new IllegalOperationException();
        };
    }

}
