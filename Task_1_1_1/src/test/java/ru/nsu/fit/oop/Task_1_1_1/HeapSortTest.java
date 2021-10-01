package ru.nsu.fit.oop.Task_1_1_1;


import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HeapSortTest {

    private static List<int[]> provideTestArrays() {
        List<int[]> testArrays = new ArrayList<>();

        testArrays.add(new int[]{5, 4, 3, 2, 1});
        testArrays.add(new int[]{12, 43, 54, 2, 431});
        testArrays.add(new int[]{});
        testArrays.add(new int[]{-123, 3, -4315, 2});
        testArrays.add(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
        testArrays.add(new int[]{1, 2, 3, 4, 5});
        testArrays.add(new int[]{1, 1, 1});

        return testArrays;
    }

    @ParameterizedTest
    @MethodSource("provideTestArrays")
    public void testHeapSort(int[] array) {
        int[] result = array.clone();

        HeapSort.heapSort(array);
        Arrays.sort(result);

        assertArrayEquals(array, result);
    }
}