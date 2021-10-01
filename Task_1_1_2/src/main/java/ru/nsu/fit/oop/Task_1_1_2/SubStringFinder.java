package ru.nsu.fit.oop.Task_1_1_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SubStringFinder {

    private static int findSubStringInBuffer(String buffer, String subString, int offset) {
        for (int i = 0; i < offset + 1; i++) {
            if (subString.equals(buffer.substring(i, i + subString.length()))) {
                return i;
            }
        }

        return -1;
    }

    public static int findSubString(String filePath, String subString) {
        try (
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader, subString.length())
        ) {
            StringBuilder buffer = new StringBuilder();
            char[] charBuffer = new char[subString.length()];
            int result = bufferedReader.read(charBuffer, 0, subString.length());
            buffer.append(new String(charBuffer));

            if (result < subString.length()) return -1;
            if (buffer.toString().equals(subString)) return 0;

            int index = 0;
            while(true) {
                result = bufferedReader.read(charBuffer, 0, subString.length());

                if (result == -1) break;

                buffer.append(new String(charBuffer));

                int offset = findSubStringInBuffer(buffer.toString(), subString, result);
                if (offset != -1) return index * subString.length() + offset;

                buffer.delete(0, subString.length());
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

}

