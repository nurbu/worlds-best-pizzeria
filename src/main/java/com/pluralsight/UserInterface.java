package com.pluralsight;

import java.util.Scanner;

public class UserInterface {

    private Scanner scan;
    private static final String BOTTOM_DASHES = (Colors.HEADER + "-".repeat(80) + Colors.RESET);

    public UserInterface() {
        scan = new Scanner(System.in);
    }


    public void Display() {

        // Main menu loop
        int choice = -1;
        while (choice != 2) {
            System.out.println(Colors.HEADER + "Welcome to the World's Best Pizzeria!!!" + Colors.RESET + "\n");
            System.out.println(BOTTOM_DASHES);

            System.out.println("1. Filter by price");
            System.out.println("12. Exit");
            System.out.print("Your choice: ");

            if (!scan.hasNextInt()) {
                System.out.println(Colors.ERROR + "Please enter 1 or 2." + Colors.RESET + "\n");
                scan.nextLine();                 // discard bad input
                continue;
            }
            choice = scan.nextInt();
            scan.nextLine();                     // clear newline

            switch (choice) {
                case 1 -> processNewOrder();
                case 2 -> System.out.println(Colors.SUCCESS + "\nThank you for visiting our pizzeria!" + Colors.RESET);
                default -> System.out.println(Colors.ERROR + "Invalid choice!" + Colors.RESET + "\n");
            }
        }
    }

    private void processNewOrder() {
    }


}

