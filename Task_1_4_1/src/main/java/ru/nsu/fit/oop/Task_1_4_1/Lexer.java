package ru.nsu.fit.oop.Task_1_4_1;

import org.apache.commons.math3.complex.Complex;
import ru.nsu.fit.oop.Task_1_4_1.ir.Operators;
import ru.nsu.fit.oop.Task_1_4_1.ir.Token;
import ru.nsu.fit.oop.Task_1_4_1.ir.exceptions.IllegalTokenException;

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
                    // if sToken is a complex
                    if (sTokens[i].matches("([-]?\\d+(\\.\\d+)?)([+-]\\d+(\\.\\d+)?i)?")) {
                        Complex number;
                        // a+bi
                        if (sTokens[i].matches("([-]?\\d+(\\.\\d+)?)(\\+\\d+(\\.\\d+)?i)?")) {
                            String[] sComplex = sTokens[i].split("\\+");
                            // a
                            if (sComplex.length == 1) {
                                number = new Complex(Double.parseDouble(sTokens[i]));
                            }
                            // a+bi
                            else {
                                // removing symbol 'i'
                                sComplex[1] = sComplex[1].substring(0, sComplex[1].length() - 1);
                                number = new Complex(Double.parseDouble(sComplex[0]), Double.parseDouble(sComplex[1]));
                            }
                        }
                        // a-bi
                        else {
                            String[] sComplex = sTokens[i].split("-");
                            // -a-bi
                            if (sTokens[i].charAt(0) == '-') {
                                // -a
                                if (sComplex.length == 2) {
                                    number = new Complex(-1.0 * Double.parseDouble(sComplex[1]));
                                }
                                // -a-bi
                                else {
                                    sComplex[2] = sComplex[2].substring(0, sComplex[2].length() - 1);
                                    number = new Complex(-1.0 * Double.parseDouble(sComplex[1]),
                                            -1.0 * Double.parseDouble(sComplex[2]));
                                }
                            }
                            // a-bi
                            else {
                                // a
                                if (sComplex.length == 1) {
                                    number = new Complex(Double.parseDouble(sComplex[0]));
                                }
                                // a-bi
                                else {
                                    sComplex[1] = sComplex[1].substring(0, sComplex[1].length() - 1);
                                    number = new Complex(-1.0 * Double.parseDouble(sComplex[0]),
                                            -1.0 * Double.parseDouble(sComplex[1]));
                                }

                            }
                        }
                        tokens[i] = (Token.make(Token.Kind.OPERAND, number));
                    } else {
                        // if sToken is not an operator and not a number
                        throw new IllegalTokenException();
                    }
            }
        }

        return tokens;
    }

    public Token[] tokenize() {
        return tokenize(read());
    }

}
