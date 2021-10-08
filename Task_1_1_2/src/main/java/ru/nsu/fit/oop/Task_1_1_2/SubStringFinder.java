package ru.nsu.fit.oop.Task_1_1_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubStringFinder {

    private static int findSubStringInBuffer(String buffer, String subString, int offset) {
        for (int i = 0; i < offset + 1; i++) {
            if (subString.equals(buffer.substring(i, i + subString.length()))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Function for finding substring in file
     * @param filePath filepath for file to read from
     * @param subString string to find in file
     * @return index of firs
     */
    public static ArrayList<Integer> findSubString(String filePath, String subString) {
        List<Integer> resultList = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader, subString.length())
        ) {
            StringBuilder buffer = new StringBuilder();
            char[] charBuffer = new char[subString.length()];
            int result = bufferedReader.read(charBuffer, 0, subString.length());
            buffer.append(new String(charBuffer));

            if (result < subString.length()) return resultList;
            if (buffer.toString().equals(subString)) {
                resultList.add(0);
            }

            int index = 0;
            while (true) {
                result = bufferedReader.read(charBuffer, 0, subString.length());

                if (result == -1) break;

                buffer.append(new String(charBuffer));

                int offset = findSubStringInBuffer(buffer.toString(), subString, result);
                if (offset != -1) {
                    resultList.add(index * subString.length() + offset);
                }

                buffer.delete(0, subString.length());
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

}

