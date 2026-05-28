package com.pluralsight.utilities;

import java.time.format.DateTimeFormatter;

public class PremadeFormats {
    public static final String SINGLE_DASHES_COLORFUL = (Colors.HEADER + "-".repeat(80) + Colors.RESET);
    public static final String DOUBLE_DASHES_COLORFUL = (Colors.HEADER + "=".repeat(80) + Colors.RESET);
    public static final String SINGLE_DASHES = "-".repeat(80);
    public static final String DOUBLE_DASHES = "=".repeat(80);
    public static final int INDENTATION = 80;
    public static final String DATE_PATTERN = "MM/dd/yyyy";
    public static final String TIME_PATTERN = "hh:mm:ss a";
    public static final String DATETIME_PATTERN = "yyyyMMdd" + "-" + "hhmmss";
    public static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    public static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    public static final String STORE_HEADER = String.format(
            "%s%n%40s%n%40s%n%s", DOUBLE_DASHES,
            "World's Best Pizzeria",
            "Official Receipt", DOUBLE_DASHES
    );
}
