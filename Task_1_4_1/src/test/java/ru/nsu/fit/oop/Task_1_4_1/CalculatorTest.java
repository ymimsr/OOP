package ru.nsu.fit.oop.Task_1_4_1;

import org.apache.commons.math3.complex.Complex;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculatorTest {

    private static Stream<Arguments> provideTestsForCalc() {
        return Stream.of(
                // real numbers test
                Arguments.of(
                        "+ 23 5",
                        new Complex(28d)
                ),
                Arguments.of(
                        "pow 23 2",
                        new Complex(529d)
                ),
                Arguments.of(
                        "sin -1",
                        new Complex(Math.sin(-1))
                ),
                Arguments.of(
                        "sqrt / 23 2",
                        new Complex(Math.sqrt(11.5))
                ),
                Arguments.of(
                        "sin pow * 2 4 3",
                        new Complex(Math.sin(Math.pow(2d * 4d, 3d)))
                ),
                // imaginary number tests
                Arguments.of(
                        "sqrt -1",
                        new Complex(0, 1.0)
                ),
                Arguments.of(
                        "+ 23+2i -5-3i",
                        new Complex(18d, -1d)
                ),
                Arguments.of(
                        "pow * 2 -3-2i 2",
                        (new Complex(2d))
                                .multiply(new Complex(-3d, -2d))
                                .pow(new Complex(2d))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestsForCalc")
    public void calculateTest(String input, Complex expResult) {
        Complex result = CalculatorMain.start(input);
        assertEquals(expResult.getReal(), result.getReal(), 1e-5);
        assertEquals(expResult.getImaginary(), result.getImaginary(), 1e-5);
    }
}
