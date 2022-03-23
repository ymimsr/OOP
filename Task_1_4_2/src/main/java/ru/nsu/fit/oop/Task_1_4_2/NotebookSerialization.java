package ru.nsu.fit.oop.Task_1_4_2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.TimeZone;

public class NotebookSerialization {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static String jsonPath = "Task_1_4_2/src/main/resources/notebook.json";


    static {
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

    public static void setJsonPath(String jsonPath_) {
        jsonPath = jsonPath_;
    }

    public static void serialize(Notebook notebook) {
        List<Notebook.Note> notes = notebook.getNotes();

        try {
            objectMapper.writeValue(new File(jsonPath), notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Notebook deserialize() throws IOException {
        Notebook notebook;

        try {
            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Notebook.Note.class);
            List<Notebook.Note> notes = objectMapper.readValue(
                    new File(jsonPath), javaType);
            notebook = new Notebook(notes);
        } catch (FileNotFoundException ignored) {
            notebook = new Notebook();
        }

        return notebook;
    }

}
