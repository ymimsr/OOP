package ru.nsu.fit.oop.Task_1_4_2.handlers;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;

public class AddOptionHandler extends OptionHandler<String> {

    public AddOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super String> setter) {
        super(parser, option, setter);
    }

    @Override
    public int parseArguments(Parameters params) throws CmdLineException {
        String title = params.getParameter(0);
        String text = params.getParameter(1);

        setter.addValue(title);
        setter.addValue(text);

        return 2;
    }

    @Override
    public String getDefaultMetaVariable() {
        return "TITLE NOTE";
    }
}
