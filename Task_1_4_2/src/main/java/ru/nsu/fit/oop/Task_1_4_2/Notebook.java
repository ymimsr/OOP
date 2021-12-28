package ru.nsu.fit.oop.Task_1_4_2;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        notes.removeIf(note -> note.title.equals(title));
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
                                note.date.after(lowerBound)
                                        && note.date.before(upperBound))
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            return title + " [" + simpleDateFormat.format(date) + "] " + text + "\n";
        }

    }

}
