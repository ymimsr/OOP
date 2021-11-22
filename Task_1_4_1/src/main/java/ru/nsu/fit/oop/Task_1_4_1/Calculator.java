package ru.nsu.fit.oop.Task_1_4_1;

import org.apache.commons.math3.complex.Complex;
import ru.nsu.fit.oop.Task_1_4_1.ir.*;
import ru.nsu.fit.oop.Task_1_4_1.ir.exceptions.IllegalTokenException;

public class Calculator {

    private int curIndex = 0;

    private Expression getExpression(Token[] tokens) {
        if (tokens.length <= curIndex) {
            assert false;
            return null;
        }

        Token token = tokens[curIndex];
        switch (token.getKind()) {
            case OPERAND:
                curIndex++;
                return new Operand((Token.OperandToken) token);
            case OPERATION:
                Token.OperatorToken operatorToken = (Token.OperatorToken) token;
                switch (operatorToken.operator) {
                    case ADD, SUB, MUL, DIV, POW -> {
                        curIndex++;
                        return new BinaryOperation(operatorToken, getExpression(tokens), getExpression(tokens));
                    }
                    case SIN, COS, LOG, SQRT -> {
                        curIndex++;
                        return new UnaryOperation(operatorToken, getExpression(tokens));
                    }
                }
            default:
                throw new IllegalTokenException();
        }
    }

    public Complex calculate(Token[] tokens) {
        curIndex = 0;

        Expression expression = getExpression(tokens);

        assert expression != null;
        return expression.calc();
    }
}
