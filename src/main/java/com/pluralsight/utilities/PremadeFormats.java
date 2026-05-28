package com.pluralsight.utilities;

public class PremadeFormats {
    public static final String BOTTOM_DASHES = (Colors.HEADER + "-".repeat(80) + Colors.RESET);
    private static final String DATE_PATTERN = "MM/dd/yyyy";
    private static final String TIME_PATTERN = "hh:mm:ss";
    public static final String DATETIME_PATTERN = "yyyyMMdd" + "-" + "hhmmss";
}
