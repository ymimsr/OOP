package ru.nsu.fit.oop.Task_2_1_1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeNumberCheckerTest {

    private static Stream<Arguments> provideTests() {
        return Stream.of(
                Arguments.of(
                        new int[] {2, 4},
                        true
                ),
                Arguments.of(
                        new int[] {6, 8, 7, 13, 9, 4},
                        true
                ),
                Arguments.of(
                        new int[] {
                                6997901,
                                6997927,
                                6997937,
                                6997967,
                                6998009,
                                6998029,
                                6998039,
                                6998051,
                                6998053
                        },
                        false
                )
        );
    }

    private final PrimeNumberChecker primeNumberChecker = new PrimeNumberChecker();

    @ParameterizedTest
    @MethodSource("provideTests")
    public void linearSolutionTest(int[] ps, boolean expSolution) {
        boolean solution = primeNumberChecker.linearSolution(ps);

        assertEquals(expSolution, solution);
    }

    @ParameterizedTest
    @MethodSource("provideTests")
    public void threadSolutionTest(int[] ps, boolean expSolution) {
        boolean solution = primeNumberChecker.threadSolution(ps);

        assertEquals(expSolution, solution);
    }

    @ParameterizedTest
    @MethodSource("provideTests")
    public void parallelSolutionTest(int[] ps, boolean expSolution) {
        boolean solution = primeNumberChecker.parallelSolution(ps);

        assertEquals(expSolution, solution);
    }
}
