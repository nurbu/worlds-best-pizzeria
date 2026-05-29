package com.pluralsight.utilities;

import java.time.format.DateTimeFormatter;

public class PremadeFormats {
    // Colored Dashes and uncolored dashes
    public static final String SINGLE_DASHES_COLORFUL = (Colors.HEADER + "-".repeat(40) + Colors.RESET);
    public static final String DOUBLE_DASHES_COLORFUL = (Colors.HEADER + "=".repeat(40) + Colors.RESET);
    public static final String SINGLE_DASHES = "-".repeat(40);
    public static final String DOUBLE_DASHES = "=".repeat(40);
    // Custom formatted dates for receipt
    public static final String DATE_PATTERN = "MM/dd/yyyy";
    public static final String TIME_PATTERN = "hh:mm:ss a";
    public static final String DATETIME_PATTERN = "yyyyMMdd" + "-" + "hhmmss";
    public static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    public static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    /**
     * Fully formatted header for displaying receipt
     */
    public static final String STORE_HEADER = String.format(
            "%s%n%20s%n%20s%n%s", DOUBLE_DASHES,
            "World's Best Pizzeria",
            "Official Receipt", DOUBLE_DASHES
    );

    /**
     * Helps format header
     *
     * @param header unformatted String headers
     * @return Fully formatted String header
     */
    public static String headingFormat(String header) {
        return String.format("%s%n%30s%n%s", DOUBLE_DASHES_COLORFUL,
                Colors.HEADER + header + Colors.RESET,
                DOUBLE_DASHES_COLORFUL);
    }
}
