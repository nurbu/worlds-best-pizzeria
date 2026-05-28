package com.pluralsight.managers;

import com.pluralsight.models.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptManager {
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final String TIME_PATTERN = "hhmmss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + "-" + TIME_PATTERN;

    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    public static void createReceipt(List<Item> items) {
        File folder = new File("Receipts");
        if (!folder.exists()) {
            folder.mkdir();
        }
        LocalDateTime now = LocalDateTime.now();
        String fileName = now.format(DATETIME_FMT);
        File file = new File(folder, fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Item item : items) {
                bw.write(item.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
