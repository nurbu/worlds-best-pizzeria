package com.pluralsight.managers;

import com.pluralsight.models.Item;
import com.pluralsight.utilities.PremadeFormats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptManager {


    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(PremadeFormats.DATETIME_PATTERN);

    /**
     * Checks if Receipts folder exists (creates if needed).
     * Gets lastest date and time (formats using DATETIME_FMT) stores as fileName.
     * Creates new file use folder (Receipts) as parent.
     * Loop through items and uses their preformatted toString
     * to write each line all wrapped in a try/catch.
     *
     * @param items All of currentOrders items
     */
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
