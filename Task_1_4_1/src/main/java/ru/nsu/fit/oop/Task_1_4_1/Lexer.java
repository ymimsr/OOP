package ru.nsu.fit.oop.Task_1_4_1;

import ru.nsu.fit.oop.Task_1_4_1.ir.Operators;
import ru.nsu.fit.oop.Task_1_4_1.ir.Token;

import java.util.Scanner;

public class Lexer {

    private String[] read() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        return line.split(" ");
    }

    public Token[] tokenize(String[] sTokens) {
        Token[] tokens = new Token[sTokens.length];

        for (int i = 0; i < sTokens.length; i++) {
            switch (sTokens[i]) {
                case "+"    :
                case "-"    :
                case "*"    :
                case "/"    :
                case "sin"  :
                case "cos"  :
                case "log"  :
                case "pow"  :
                case "sqrt" :
                    tokens[i] = (Token.make(Token.Kind.OPERATION, Operators.getOperator(sTokens[i])));
                    break;
                default:
                    // if sToken is a double
                    if (sTokens[i].matches("[+-]?\\d+(\\.\\d+)?")) {
                        tokens[i] = (Token.make(Token.Kind.OPERAND, Double.parseDouble(sTokens[i])));
                    } else {
                        // there should be an exception
                        // if sToken is not an operator and not a number
                        assert false;
                    }
            }
        }

        return tokens;
    }

    public Token[] tokenize() {
        return tokenize(read());
    }

}
