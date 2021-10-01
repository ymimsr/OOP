package ru.nsu.fit.oop.Task_1_1_1;

public class HeapSort {

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
                int temp = array[x];
                array[x] = array[largest];
                array[largest] = temp;
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
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }
}

