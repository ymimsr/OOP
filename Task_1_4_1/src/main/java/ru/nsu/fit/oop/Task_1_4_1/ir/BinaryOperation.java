package ru.nsu.fit.oop.Task_1_4_1.ir;

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
    public double calc() {
        switch (operatorToken.operator) {
            case ADD:
                return left.calc() + right.calc();
            case SUB:
                return left.calc() - right.calc();
            case MUL:
                return left.calc() * right.calc();
            case DIV:
                return left.calc() / right.calc();
            case POW:
                return Math.pow(left.calc(), right.calc());
            default:
                assert false;
                return 0; // non-binary operation
        }
    }

}
