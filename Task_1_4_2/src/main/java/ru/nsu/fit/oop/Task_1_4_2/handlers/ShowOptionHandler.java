package ru.nsu.fit.oop.Task_1_4_2.handlers;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.Messages;
import org.kohsuke.args4j.spi.OptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowOptionHandler extends OptionHandler<Object> {

    public ShowOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super Object> setter) {
        super(parser, option, setter);
    }

    @Override
    public int parseArguments(Parameters params) throws CmdLineException {
        int counter = 0;

        // -show without arguments
        if (params.size() == 0) {
            setter.addValue(true);
            return 0;
        }

        // show with arguments
        setter.addValue(parseDate(params.getParameter(counter++), params.getParameter(-1)));
        setter.addValue(parseDate(params.getParameter(counter++), params.getParameter(-1)));

        for (; counter < params.size(); counter++) {
            String param = params.getParameter(counter);

            if (param.startsWith("-"))
                break;

            setter.addValue(param);
        }

        return counter;
    }

    private Date parseDate(String sDate, String option) throws CmdLineException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Date date;
        try {
            date = simpleDateFormat.parse(sDate);
        } catch (ParseException e) {
            throw new CmdLineException(owner, Messages.ILLEGAL_OPERAND, option, sDate);
        }

        return date;
    }

    @Override
    public String getDefaultMetaVariable() {
        return "DATE1 DATE2 STRING...";
    }

}
