package ru.nsu.fit.oop.Task_1_4_1.ir;

import org.apache.commons.math3.complex.Complex;
import ru.nsu.fit.oop.Task_1_4_1.ir.Expression;
import ru.nsu.fit.oop.Task_1_4_1.ir.Token;
import ru.nsu.fit.oop.Task_1_4_1.ir.exceptions.IllegalOperationException;

public class UnaryOperation extends Expression {

    public final Token.OperatorToken operatorToken;
    public final Expression expression;

    public UnaryOperation(Token.OperatorToken operatorToken, Expression expression) {
        this.operatorToken = operatorToken;
        this.expression = expression;
    }

    @Override
    public Complex calc() {
        return switch (operatorToken.operator) {
            case SIN -> expression.calc().sin();
            case COS -> expression.calc().cos();
            case LOG -> expression.calc().log();
            case SQRT -> expression.calc().sqrt();
            default -> throw new IllegalOperationException();
        };
    }
}
