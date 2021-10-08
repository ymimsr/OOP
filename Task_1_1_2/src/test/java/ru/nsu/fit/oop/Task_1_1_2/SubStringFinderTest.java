package ru.nsu.fit.oop.Task_1_1_2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SubStringFinderTest {

    private static Stream<Arguments> provideTestsForFindSubStringTest() {
        return Stream.of(
                Arguments.of(
                        "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\1.in",
                        "violent",
                        31
                ),
                Arguments.of(
                        "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\2.in",
                        "I give him a hard time.  Keeps him in check.",
                        550
                ),
                Arguments.of(
                        "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\2.in",
                        "Top of the morning to you, Mrs. O'Neil.",
                        27587
                ),
                Arguments.of(
                        "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\3.in",
                        "blablabla",
                        0
                ),
                Arguments.of(
                        "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\3.in",
                        "blablablabla",
                        -1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestsForFindSubStringTest")
    public void findSubStringTest(String filePath, String subString, int expResult) {
        ArrayList<Integer> resultList = SubStringFinder.findSubString(filePath, subString);
        int[] result = new int[resultList.size()];
        resultList.toArray(result);
        assertArrayEquals(result, expResult);
    }
}