package ru.nsu.fit.oop.Task_1_2_1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackIntegerTest {

    private static Stream<Arguments> provideTestsForPush() {
        return Stream.of(
                Arguments.of(
                        new MyStack<Integer>(),
                        new MyStack<>(List.of(1, 4, 5, 21, 453)),
                        new int[]{1, 4, 5, 21, 453}
                ),
                Arguments.of(
                        new MyStack<>(List.of(1, 2, 3)),
                        new MyStack<>(List.of(1, 2, 3, 4, 5)),
                        new int[]{4, 5}
                )
        );
    }

    private static Stream<Arguments> provideTestsForPop() {
        return Stream.of(
                Arguments.of(
                        new MyStack<>(List.of(1, 2, 3, 4, 5)),
                        2,
                        new MyStack<>(List.of(1, 2, 3)),
                        new int[]{5, 4}
                ),
                Arguments.of(
                        new MyStack<>(List.of(1, 2 ,3)),
                        3,
                        new MyStack<Integer>(),
                        new int[]{3, 2, 1}
                )
        );
    }

    private static Stream<Arguments> provideTestsForPushStack() {
        return Stream.of(
                Arguments.of(
                        new MyStack<Integer>(),
                        new MyStack<Integer>(),
                        new MyStack<Integer>()
                ),
                Arguments.of(
                        new MyStack<>(List.of(1, 2, 3)),
                        new MyStack<Integer>(),
                        new MyStack<>(List.of(1, 2, 3))
                ),
                Arguments.of(
                        new MyStack<Integer>(),
                        new MyStack<>(List.of(1, 2, 3)),
                        new MyStack<>(List.of(3, 2, 1))
                ),
                Arguments.of(
                        new MyStack<>(List.of(1, 2, 3)),
                        new MyStack<>(List.of(4, 5, 6)),
                        new MyStack<>(List.of(1, 2, 3, 6, 5, 4))
                )
        );
    }

    private static Stream<Arguments> provideTestsForPopStack() {
        return Stream.of(
                Arguments.of(
                        new MyStack<>(List.of(1, 2)),
                        2,
                        new MyStack<Integer>(),
                        new MyStack<>(List.of(2, 1))
                ),
                Arguments.of(
                        new MyStack<>(List.of(1, 2, 3, 4)),
                        2,
                        new MyStack<>(List.of(1, 2)),
                        new MyStack<>(List.of(4, 3))
                )
        );
    }

    private static Stream<Arguments> provideTestsForCount() {
        return Stream.of(
                Arguments.of(
                        new MyStack<Integer>(),
                        0
                ),
                Arguments.of(
                        new MyStack<>(List.of(1, 2, 4)),
                        3
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestsForPush")
    public void pushTest(MyStack<Integer> stack, MyStack<Integer> expStack, int[] elems) {
        for (int elem : elems) {
            stack.push(elem);
        }
        assertEquals(expStack, stack);
    }

    @ParameterizedTest
    @MethodSource("provideTestsForPop")
    public void popTest(MyStack<Integer> stack, int popCount, MyStack<Integer> expStack, int[] expPopElems) {
        int[] popElems = new int[popCount];
        for (int i = 0; i < popCount; i++) {
            popElems[i] = stack.pop();
        }

        assertEquals(expStack, stack);
        assertArrayEquals(expPopElems, popElems);
    }

    @ParameterizedTest
    @MethodSource("provideTestsForPushStack")
    public void pushStackTest(MyStack<Integer> toStack, MyStack<Integer> fromStack, MyStack<Integer> expStack) {
        toStack.pushStack(fromStack);

        assertEquals(expStack, toStack);
    }

    @ParameterizedTest
    @MethodSource("provideTestsForPopStack")
    public void popStackTest(MyStack<Integer> stack, int popCount, MyStack<Integer> expStack, MyStack<Integer> expPopElems) {
        MyStack<Integer> popElems = stack.popStack(popCount);

        assertEquals(expStack, stack);
        assertEquals(expPopElems, popElems);
    }

    @ParameterizedTest
    @MethodSource("provideTestsForCount")
    public void countTest(MyStack<Integer> stack, int expCount) {
        assertEquals(expCount, stack.count());
    }
}
