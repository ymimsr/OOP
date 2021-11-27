package ru.nsu.fit.oop.Task_1_4_2;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class Notebook {

    private final List<Note> notes;

    public Notebook() {
        this(new ArrayList<>());
    }

    public Notebook(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void add(String title, String note) {
        notes.add(new Note(title, note));
    }

    public void remove(String title) {
        Optional<Note> optionalNote = notes.stream()
                .filter(note -> note.title.equals(title))
                .findFirst();

        optionalNote.ifPresent(notes::remove);
    }

    public void printSortedByTime() {
        notes.stream()
                .sorted(Comparator.comparing(note -> note.date))
                .forEach(System.out::println);
    }

    public void printByTimeAndKeywords(Date lowerBound, Date upperBound, String... keywords) {
        notes.stream()
                .filter(
                        note ->
                                note.date.compareTo(lowerBound) > 0
                                        && note.date.compareTo(upperBound) < 0)
                .filter(note -> {
                    for (String keyword : keywords) {
                        if (note.title.contains(keyword)) return true;
                    }
                    return false;
                })
                .sorted(Comparator.comparing(note -> note.date))
                .forEach(System.out::println);
    }

    public static class Note {

        private String title;
        private String text;
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd.MM.yyyy hh:mm")
        private Date date;

        // needed for jackson to instantiate Note class while unmarshalling List<Note>
        public Note() {
        }

        public Note(String title, String text) {
            this.title = title;
            this.text = text;
            this.date = new Date();
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }

        public Date getDate() {
            return date;
        }

        @Override
        public String toString() {
            return "Note{" +
                    "title='" + title + '\'' +
                    ", text='" + text + '\'' +
                    ", date=" + date +
                    '}';
        }

    }

}
