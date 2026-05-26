package com.pluralsight;

import com.pluralsight.models.Drink;
import com.pluralsight.models.Item;
import com.pluralsight.models.Order;

import java.util.Scanner;
import java.util.stream.IntStream;

public class UserInterface {

    private Scanner scan;
    private static final String BOTTOM_DASHES = (Colors.HEADER + "-".repeat(80) + Colors.RESET);
    Order currentOrder;

    public UserInterface() {
        scan = new Scanner(System.in);
        currentOrder = new Order();
    }


    public void Display() {

        // Main menu loop
        int choice = -1;
        while (choice != 2) {
            System.out.println(Colors.HEADER + "Welcome to the World's Best Pizzeria!!!" + Colors.RESET + "\n");
            System.out.println(BOTTOM_DASHES);

            System.out.println("1. Create a new order");
            System.out.println("2. Exit");
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
        int choice = -1;
        while (choice != 7) {
            System.out.println(Colors.HEADER + "Order Screen!" + Colors.RESET + "\n");
            System.out.println(BOTTOM_DASHES);

            System.out.println("1. Add Pizza");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Garlic Knot");
            System.out.println("4. Display order");
            System.out.println("5. Remove Item");
            System.out.println("6. Checkout");
            System.out.println("7. Cancel order");
            System.out.print("Your choice: ");

            if (!scan.hasNextInt()) {
                System.out.println(Colors.ERROR + "Please enter 1-7." + Colors.RESET + "\n");
                scan.nextLine();                 // discard bad input
                continue;
            }
            choice = scan.nextInt();
            scan.nextLine();                     // clear newline

            switch (choice) {
                case 1 -> processAddPizza();
                case 2 -> processAddDrink();
                case 3 -> processAddGarlicKnot();
                case 4 -> processDisplayOrder();
                case 5 -> processRemoveItem();
                case 6 -> processCheckout();
                case 7 -> processCancelOrder();
                default -> System.out.println(Colors.ERROR + "Invalid choice!" + Colors.RESET + "\n");
            }
        }
    }

    private void processAddPizza() {
    }

    /**
     * Handles getting drink size and flavor.
     */

    private void processAddDrink() {

        /**
         * Main loop repeats asking to add more drinks
         */

        while (true) {
            System.out.println(Colors.HEADER + "Drink Menu!" + Colors.RESET + "\n");
            System.out.println(BOTTOM_DASHES);
            String[] drinks = {"Coca cola", "Starry", "Coke zero", "Fanta", "Dr.Pepper", "Ice-Tea"};
            String choice = "";

            // Allows user to exit or continue with adding drink to order

            while (true) {
                System.out.println("Enter a Cup Size (S|M|L):");
                System.out.println("Exit (E):");
                System.out.print("Choice: ");
                choice = scan.nextLine();
                if (choice.equalsIgnoreCase("S") || choice.equalsIgnoreCase("M") || choice.equalsIgnoreCase("L")
                        || choice.equalsIgnoreCase("E")) {
                    break;
                } else {
                    System.out.println(Colors.ERROR + "Please enter a Cup Size (S|M|L) or (E) to exit." + Colors.RESET + "\n");
                }
            }
            if (choice.equalsIgnoreCase("e")) {
                break;
            }

            // Loops through drinks list while printing an index

            IntStream.range(0, drinks.length).forEach(i -> System.out.println(i + 1 + ". " + drinks[i]));
            int flavorChoice = -1;
            while (true) {
                System.out.print("Choice: ");
                try {
                    flavorChoice = parseInt(scan.nextLine());
                    if (!(flavorChoice > 0 && flavorChoice < 7)) {
                        System.out.println(Colors.ERROR + "Enter a number 1-6" + Colors.RESET + "\n");
                    } else {
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Creates a drink object

            Item drink = new Drink(drinks[flavorChoice - 1], choice.toUpperCase());
            currentOrder.addItem(drink);
            System.out.println(Colors.SUCCESS + "Drink added!" + Colors.RESET + "\n");
            System.out.print("Would you like to add another drink? (Y/N):");
            String answer = scan.nextLine();
            if (answer.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    private void processAddGarlicKnot() {
    }

    private void processDisplayOrder() {
        currentOrder.displayOrder();
    }

    private void processRemoveItem() {
    }

    private void processCheckout() {
    }

    private void processCancelOrder() {
    }

        /* --------------------------------------------------------------------------
       Helper Functions

     * Parse double and int strings
     * if s is empty throws Exception with Custom message (skipped by user)
     * Uses try/catch to check if user input valid.
     * if s is not a number or in a Double format throws Exception

       -------------------------------------------------------------------------- */

    private static Double parseDouble(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException(Colors.WARN + "Input can't be empty" + Colors.RESET);
        }
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Colors.WARN + "Invalid number: " + s + Colors.RESET);
        }
    }

    private static int parseInt(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException(Colors.WARN + "Input can't be empty" + Colors.RESET);
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Colors.WARN + "Invalid number: " + s + Colors.RESET);
        }
    }

}

