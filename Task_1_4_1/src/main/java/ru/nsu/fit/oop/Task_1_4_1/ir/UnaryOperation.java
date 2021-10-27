package ru.nsu.fit.oop.Task_1_4_1.ir;

import ru.nsu.fit.oop.Task_1_4_1.ir.Expression;
import ru.nsu.fit.oop.Task_1_4_1.ir.Token;

public class UnaryOperation extends Expression {

    public final Token.OperatorToken operatorToken;
    public final Expression expression;

    public UnaryOperation(Token.OperatorToken operatorToken, Expression expression) {
        this.operatorToken = operatorToken;
        this.expression = expression;
    }

    @Override
    public double calc() {
        switch (operatorToken.operator) {
            case SIN:
                return Math.sin(expression.calc());
            case COS:
                return Math.cos(expression.calc());
            case LOG:
                return Math.log(expression.calc());
            case SQRT:
                return Math.sqrt(expression.calc());
            default:
                assert false;
                return 0;
        }
    }
}
