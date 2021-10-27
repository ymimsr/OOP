package ru.nsu.fit.oop.Task_1_4_1;

import ru.nsu.fit.oop.Task_1_4_1.ir.*;

public class Calculator {
    // sin + - 1 2 1
    // sin ((1 - 2) + 1) = sin 0 = 0
    // Algorithm for prefix expressions
    // 1. If it is an operator the

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
                assert false;
                return null;
        }
    }

    public double calculate(Token[] tokens) {
        curIndex = 0;

        Expression expression = getExpression(tokens);

        assert expression != null;
        return expression.calc();
    }
}
