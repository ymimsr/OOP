package ru.nsu.fit.oop.Task_1_1_1;

import java.util.ArrayList;
import java.util.Collections;

public class HeapSort {

    /**
     * Function for swapping to elements in array
     * @param array source array
     * @param i index of the first element
     * @param j index of the second element
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Function for converting array to binary heap
     * @param array array to sort
     * @param n length of heap
     * @param i index of root node
     */
    private static void heapify(int[] array, int n, int i) {
        int x = i;
        int largest = i;
        while (true) {
            int l = 2 * x + 1;
            int r = 2 * x + 2;

            if (l < n && array[l] > array[largest])
                largest = l;

            if (r < n && array[r] > array[largest])
                largest = r;

            if (largest != x) {
                swap(array, x, largest);
                x = largest;
            } else {
                break;
            }
        }
    }

    /**
     * Sorting function
     * @param array array to sort
     */
    public static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }
}

