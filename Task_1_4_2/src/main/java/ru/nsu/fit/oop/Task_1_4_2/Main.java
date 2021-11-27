package ru.nsu.fit.oop.Task_1_4_2;


import org.kohsuke.args4j.*;
import org.kohsuke.args4j.spi.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    @Option(name = "-add", usage = "adds a note to a notebook", handler = StringArrayOptionHandler.class,
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
            handler = StringArrayOptionHandler.class,
            forbids = {"-add", "-rem"})
    private String[] showArgs = null;


    @Argument
    List<String> argumentList = new ArrayList<>();

    public static void main(String[] args) {
        new Main().doMain(args);
    }

    public void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            Notebook notebook = NotebookSerialization.deserialize();

            if (addArgs != null) {
                if (addArgs.length != 2)

                notebook.add(addArgs[0], addArgs[1]);
            } else if (remTitle != null) {
                notebook.remove(remTitle);
            } else if (showArgs != null) {
                notebook.printByTimeAndKeywords(new Date(showArgs[0]),
                        new Date(showArgs[1]),
                        Arrays.copyOfRange(showArgs, 2, showArgs.length));
            } else {
                System.out.println(showArgs == null);
                notebook.printSortedByTime();
            } /*else {
                throw new CmdLineException(parser, "No options provided");
            }*/

            NotebookSerialization.serialize(notebook);

        } catch (CmdLineException exception) {
            System.err.println(exception.getMessage());
            System.err.println("java Notebook [options...] arguments...");
            parser.printUsage(System.err);
            System.err.println();
        }
    }

    /*private Date parseDate(String sDate) throws CmdLineException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        try {
            simpleDateFormat.parse(sDate);
        } catch (ParseException e) {
            th
        }
    }*/

    /*static class ShowOptionHandler extends OptionHandler<Object> {

        protected ShowOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super Object> setter) {
            super(parser, option, setter);
        }

        @Override
        public int parseArguments(Parameters params) throws CmdLineException {
            int counter = 0;

            if (params.size() < 3)
                throw new CmdLineException()

            for (; counter < params.size(); counter++) {
                String param
            }

            return counter;
        }

        private Date parseDate(String sDate) throws ParseException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            return simpleDateFormat.parse(sDate);
        }

        @Override
        public String getDefaultMetaVariable() {
            return null;
        }
    }*/
}
