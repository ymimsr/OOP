package ru.nsu.fit.oop.Task_1_1_2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubStringFinderTest {

    private static Stream<Arguments> provideTestsForFindSubStringTest() {
        return Stream.of(
                Arguments.of(
                        "./src/test/resources/1.in",
                        "violent",
                        List.of(31)
                ),
                Arguments.of(
                        "./src/test/resources/2.in",
                        "I give him a hard time.  Keeps him in check.",
                        List.of(550)
                ),
                Arguments.of(
                        "./src/test/resources/2.in",
                        "Top of the morning to you, Mrs. O'Neil.",
                        List.of(27587)
                ),
                Arguments.of(
                        "./src/test/resources/3.in",
                        "blablabla",
                        List.of(0)
                ),
                Arguments.of(
                        "./src/test/resources/3.in",
                        "blablablabla",
                        new ArrayList<Integer>()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestsForFindSubStringTest")
    public void findSubStringTest(String filePath, String subString, List<Integer> expResult) {
        List<Integer> resultList = SubStringFinder.findSubString(filePath, subString);
        assertEquals(expResult, resultList);
    }
}