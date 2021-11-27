package ru.nsu.fit.oop.Task_1_4_2;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class NotebookSerialization {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void serialize(Notebook notebook) {
        List<Notebook.Note> notes = notebook.getNotes();

        try {
            objectMapper.writeValue(new File("Task_1_4_2/src/main/resources/notebook.json"), notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Notebook deserialize() {
        Notebook notebook;

        try {
            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Notebook.Note.class);
            List<Notebook.Note> notes = objectMapper.readValue(
                    new File("Task_1_4_2/src/main/resources/notebook.json"), javaType);
            notebook = new Notebook(notes);
        } catch (FileNotFoundException ignored) {
            notebook = new Notebook();
        } catch (IOException e) {
            notebook = new Notebook();
            e.printStackTrace();
        }

        return notebook;
    }

}
