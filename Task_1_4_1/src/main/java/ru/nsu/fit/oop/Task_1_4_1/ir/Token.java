package ru.nsu.fit.oop.Task_1_4_1.ir;

public abstract class Token {

    public static Token make(Kind kind, Object value) {
        switch (kind) {
            case OPERAND:
                return new OperandToken((Double) value);
            case OPERATION:
                return new OperatorToken((Operators) value);
            default:
                assert false;
                return null;
        }
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

        public final double value;

        public OperandToken(double value) {
            this.value = value;
        }

        @Override
        public Kind getKind() {
            return Kind.OPERAND;
        }

    }

}
