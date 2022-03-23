package ru.nsu.fit.oop.Task_2_1_1.util;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class StringArrayConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (source instanceof String) {
            String[] stringParams = ((String) source).split(",");
            int[] params = new int[stringParams.length];
            for (int i = 0; i < params.length; i++) {
                params[i] = Integer.parseInt(stringParams[i]);
            }

            return params;
        } else {
            throw new IllegalArgumentException("Conversion from " + source.getClass() + " to " +
                    targetType + " is not supported.");
        }
    }
}
