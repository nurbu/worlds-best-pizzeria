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
        while (choice != 6) {
            System.out.println(Colors.HEADER + "Order Screen!" + Colors.RESET + "\n");
            System.out.println(BOTTOM_DASHES);

            System.out.println("1. Add Pizza");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Garlic Knot");
            System.out.println("4. Display order");
            System.out.println("5. Checkout");
            System.out.println("6. Cancel order");
            System.out.print("Your choice: ");

            if (!scan.hasNextInt()) {
                System.out.println(Colors.ERROR + "Please enter 1-6." + Colors.RESET + "\n");
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
                case 5 -> processCheckout();
                case 6 -> processCancelOrder();
                default -> System.out.println(Colors.ERROR + "Invalid choice!" + Colors.RESET + "\n");
            }
        }
    }

    private void processAddPizza() {
    }

    private void processAddDrink() {
    }

    private void processAddGarlicKnot() {
    }

    private void processDisplayOrder() {
    }

    private void processCheckout() {
    }

    private void processCancelOrder() {
    }


}

