package ru.nsu.fit.oop.Task_1_4_1.ir;

public enum Operators {

    ADD,  // +
    SUB,  // -
    MUL,  // *
    DIV,  // /
    SIN,  // sin
    COS,  // cos
    LOG,  // log
    POW,  // exponentiation
    SQRT; // square root

    public static Operators getOperator(String sValue) {
        switch (sValue) {
            case "+" :
                return ADD;
            case "-" :
                return SUB;
            case "*" :
                return MUL;
            case "/" :
                return DIV;
            case "sin" :
                return SIN;
            case "cos" :
                return COS;
            case "log" :
                return LOG;
            case "pow" :
                return POW;
            case "sqrt" :
                return SQRT;
            default:
                assert false; // invalid operation
        }

        return null;
    }
}
