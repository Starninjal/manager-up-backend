package com.example.employee_management_system.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class NumberFormatter {
    private static DecimalFormat DECIMAL_FORMAT;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DECIMAL_FORMAT = new DecimalFormat("#, ##0.###", symbols);
        DECIMAL_FORMAT.setGroupingUsed(true);
    }

    public static String format(Double value) {
        if (value == null) {
            return "0,00";
        }
        return DECIMAL_FORMAT.format(value);
    }
}
