package com.pluralsight.managers;

import com.pluralsight.models.Item;
import com.pluralsight.utilities.PremadeFormats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ReceiptManager {


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
        String date = now.format(PremadeFormats.DATE_FMT);
        String time = now.format(PremadeFormats.TIME_FMT);
        String fileName = now.format(PremadeFormats.DATETIME_FMT);
        File file = new File(folder, fileName.concat(".txt"));
        double total = 0;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(PremadeFormats.STORE_HEADER);
            bw.newLine();
            bw.newLine();
            bw.write("Date: " + date + " " + time);
            bw.newLine();
            for (int i = 0; i < items.size(); i++) {
                bw.newLine();
                bw.write(i + 1 + ". " + items.get(i).toString());
                total += items.get(i).getPrice();
            }
            bw.newLine();
            bw.write(PremadeFormats.DOUBLE_DASHES + "\n");
            bw.write(String.format("Total Price: %.2f%n", total));
            bw.write(PremadeFormats.SINGLE_DASHES);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
