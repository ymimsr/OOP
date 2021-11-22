package ru.nsu.fit.oop.Task_1_4_1.ir;

import org.apache.commons.math3.complex.Complex;
import ru.nsu.fit.oop.Task_1_4_1.ir.exceptions.IllegalTokenException;

public abstract class Token {

    public static Token make(Kind kind, Object value) {
        return switch (kind) {
            case OPERAND -> new OperandToken((Complex) value);
            case OPERATION -> new OperatorToken((Operators) value);
        };
    }

    public enum Kind {
        OPERAND,
        OPERATION
    }

    public abstract Kind getKind();

    public static class OperatorToken extends Token {

        public final Operators operator;

        public OperatorToken(Operators operator) {
            this.operator = operator;
        }

        @Override
        public Kind getKind() {
            return Kind.OPERATION;
        }

    }

    public static class OperandToken extends Token {

        public final Complex value;

        public OperandToken(Complex value) {
            this.value = value;
        }

        @Override
        public Kind getKind() {
            return Kind.OPERAND;
        }

    }

}
