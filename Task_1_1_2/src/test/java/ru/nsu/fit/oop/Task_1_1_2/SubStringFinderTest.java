package ru.nsu.fit.oop.Task_1_1_2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubStringFinderTest {

    @Test
    public void findSubStringTest1() {
        String filePath = "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\1.in";
        String subString = "пирог";
        int expResult = 7;
        int result = SubStringFinder.findSubString(filePath, subString);
        assertEquals(expResult, result);
    }

    @Test
    public void findSubStringTest2() {
        String filePath = "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\2.in";
        String subString = "пирог";
        int expResult = -1;
        int result = SubStringFinder.findSubString(filePath, subString);
        assertEquals(expResult, result);
    }

    @Test
    public void findSubStringTest3() {
        String filePath = "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\3.in";
        String subString = "цыган";
        int expResult = 17;
        int result = SubStringFinder.findSubString(filePath, subString);
        assertEquals(expResult, result);
    }

    @Test
    public void findSubStringTest4() {
        String filePath = "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\4.in";
        String subString = "I give him a hard time.  Keeps him in check.";
        int expResult = 550;
        int result = SubStringFinder.findSubString(filePath, subString);
        assertEquals(expResult, result);
    }

    @Test
    public void findSubStringTest5() {
        String filePath = "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\4.in";
        String subString = "Top of the morning to you, Mrs. O'Neil.";
        int expResult = 27587;
        int result = SubStringFinder.findSubString(filePath, subString);
        assertEquals(expResult, result);
    }

    @Test
    public void findSubStringTest6() {
        String filePath = "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\5.in";
        String subString = "blablabla";
        int expResult = 0;
        int result = SubStringFinder.findSubString(filePath, subString);
        assertEquals(expResult, result);
    }

    @Test
    public void findSubStringTest7() {
        String filePath = "E:\\projects\\OOP\\Task_1_1_2\\src\\test\\resources\\5.in";
        String subString = "blablablabla";
        int expResult = -1;
        int result = SubStringFinder.findSubString(filePath, subString);
        assertEquals(expResult, result);
    }
}