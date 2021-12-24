package ru.nsu.fit.oop.Task_1_4_2;


import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.CmdLineException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class NotebookTest {

    private final Main main = new Main();

    @Test
    public void addOptionTest() {
        NotebookSerialization.setJsonPath("src/test/resources/addTest.json");

        main.execute(new String[]{"-add", "Test1", "Text1"});
        main.execute(new String[]{"-add", "Test2", "Text2"});
        main.execute(new String[]{"-add", "Test3", "Text3"});

        Notebook notebook = null;
        try {
            notebook = NotebookSerialization.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(notebook.getNotes().stream()
                .allMatch(note -> note.getTitle().contains("Test") && note.getText().contains("Text")));
    }

    @Test
    public void removeOptionTest() {
        NotebookSerialization.setJsonPath("src/test/resources/removeTest.json");

        main.execute(new String[]{"-rem", "Wassup?"});

        Notebook notebook = null;
        try {
            notebook = NotebookSerialization.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertFalse(notebook.getNotes().stream()
                .anyMatch(note -> note.getTitle().contains("Wassup?") && note.getText().contains("YO!")));
    }

    @Test
    public void showWithoutArgumentsOptionTest() {
        NotebookSerialization.setJsonPath("src/test/resources/showWithoutTest.json");

        MyPrintStream printStream = new MyPrintStream();
        System.setOut(printStream);

        main.execute(new String[]{"-show"});

        List<Notebook.Note> notes = printStream.notes;

        assertTrue(notes.stream()
                .allMatch(note -> note.getTitle().contains("Wassup")));
    }

    @Test
    public void showWithArgumentsOptionTest() {
        NotebookSerialization.setJsonPath("src/test/resources/showWithTest.json");

        MyPrintStream printStream = new MyPrintStream();
        System.setOut(printStream);

        main.execute(new String[]{"-show", "28.11.2021 10:24", "05.12.2021 23:21", "Wass", "up"});

        List<Notebook.Note> notes = printStream.notes;

        assertTrue(notes.stream()
                .allMatch(note -> note.getTitle().equals("Wassup?") && note.getText().equals("YO!")));
    }

    @Test
    public void invalidOptionsTest() {
        MyErrPrintStream myPrintStream = new MyErrPrintStream();
        System.setErr(myPrintStream);

        main.execute(new String[]{"-dasd -dasdawdq"});

        assertTrue(myPrintStream.errMessage.contains("java Notebook [options...] arguments..."));
    }

    private static class MyPrintStream extends PrintStream {

        public List<Notebook.Note> notes = new ArrayList<>();

        public MyPrintStream() {
            super(System.out, true);
        }

        @Override
        public void print(Object obj) {
            notes.add((Notebook.Note) obj);
        }

    }

    private static class MyErrPrintStream extends PrintStream {

        public String errMessage = "";

        public MyErrPrintStream() {
            super(System.err, true);
        }

        public void print(String s) { errMessage += s; }
    }
}
