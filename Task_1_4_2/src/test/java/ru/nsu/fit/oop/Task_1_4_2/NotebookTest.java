package ru.nsu.fit.oop.Task_1_4_2;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class NotebookTest {

    private final Main main = new Main();

    @Test
    public void addOptionTest() {
        NotebookSerialization.setJsonPath("src/test/resources/addTest.json");

        main.doMain(new String[]{"-add", "Test1", "Text1"});
        main.doMain(new String[]{"-add", "Test2", "Text2"});
        main.doMain(new String[]{"-add", "Test3", "Text3"});

        Notebook notebook = NotebookSerialization.deserialize();

        assertTrue(notebook.getNotes().stream()
                .allMatch(note -> note.getTitle().contains("Test") && note.getText().contains("Text")));
    }

    @Test
    public void removeOptionTest() {
        NotebookSerialization.setJsonPath("src/test/resources/removeTest.json");

        main.doMain(new String[]{"-rem", "Wassup?"});

        Notebook notebook = NotebookSerialization.deserialize();

        assertFalse(notebook.getNotes().stream()
                .anyMatch(note -> note.getTitle().contains("Wassup?") && note.getText().contains("YO!")));
    }

    @Test
    public void showWithoutArgumentsOptionTest() {
        NotebookSerialization.setJsonPath("src/test/resources/showWithoutTest.json");

        MyPrintStream printStream = new MyPrintStream();
        System.setOut(printStream);

        main.doMain(new String[]{"-show"});

        List<Notebook.Note> notes = printStream.notes;

        assertTrue(notes.stream()
                .allMatch(note -> note.getTitle().contains("Wassup")));
    }

    @Test
    public void showWithArgumentsOptionTest() {
        NotebookSerialization.setJsonPath("src/test/resources/showWithTest.json");

        MyPrintStream printStream = new MyPrintStream();
        System.setOut(printStream);

        main.doMain(new String[]{"-show", "28.11.2021 10:24", "05.12.2021 23:21", "Wass", "up"});

        List<Notebook.Note> notes = printStream.notes;

        assertTrue(notes.stream()
                .allMatch(note -> note.getTitle().equals("Wassup?") && note.getText().equals("YO!")));
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
}
