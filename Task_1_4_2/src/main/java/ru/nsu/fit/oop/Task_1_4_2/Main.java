package ru.nsu.fit.oop.Task_1_4_2;


import org.kohsuke.args4j.*;
import org.kohsuke.args4j.spi.*;
import ru.nsu.fit.oop.Task_1_4_2.handlers.AddOptionHandler;
import ru.nsu.fit.oop.Task_1_4_2.handlers.ShowOptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    @Option(name = "-add", usage = "adds a note to a notebook", handler = AddOptionHandler.class,
            forbids = {"-rem", "-show"})
    private String[] addArgs = null;

    @Option(name = "-rem", usage = "removes a note from a notebook", forbids = {"-add", "-show"})
    private String remTitle = null;

    @Option(name = "-show", usage = """
            with arguments:
            prints all notes that contain given keywords in the title
            and published in given interval sorted by its publication date
            without arguments:
            prints all notes sorted by its publication date""",
            handler = ShowOptionHandler.class,
            forbids = {"-add", "-rem"})
    private Object[] showArgs = null;

    public static void main(String[] args) {
        new Main().doMain(args);
    }

    public void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            Notebook notebook = NotebookSerialization.deserialize();

            if (addArgs != null) {
                notebook.add(addArgs[0], addArgs[1]);
            } else if (remTitle != null) {
                notebook.remove(remTitle);
            } else if (showArgs.length >= 2) {
                notebook.printByTimeAndKeywords(
                        (Date) showArgs[0],
                        (Date) showArgs[1],
                        Arrays.copyOfRange(showArgs, 2, showArgs.length, String[].class)
                );
            } else if (showArgs.length == 1){
                notebook.printSortedByTime();
            }

            NotebookSerialization.serialize(notebook);

        } catch (CmdLineException exception) {
            System.err.println(exception.getMessage());
            System.err.println("java Notebook [options...] arguments...");
            parser.printUsage(System.err);
            System.err.println();
        }
    }

}
