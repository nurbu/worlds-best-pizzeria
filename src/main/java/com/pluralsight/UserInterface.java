package com.pluralsight;

import com.pluralsight.models.*;
import com.pluralsight.models.toppings.*;

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
        System.out.println("Creating Custom Pizza");
        int sizeNum;
        while (true) {
            System.out.println("Size?");
            System.out.println("1: 8 inch");
            System.out.println("2: 12 inch");
            System.out.println("3: 16 inch");
            try {
                sizeNum = parseInt(scan.nextLine());
                if (sizeNum > 0 && sizeNum < 4) break;
                else System.out.println(Colors.ERROR + "Invalid size!" + Colors.RESET + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        int size = 0;
        switch (sizeNum) {
            case 1 -> size = 8;
            case 2 -> size = 12;
            case 3 -> size = 16;
        }
        int crustNum = 0;
        while (true) {
            System.out.println("Type of Crust? (1-4)");
            System.out.println("1. thin");
            System.out.println("2. regular");
            System.out.println("3. thick");
            System.out.println("4. cauliflower");
            System.out.print("Choice: ");
            try {
                crustNum = parseInt(scan.nextLine());
                if (crustNum > 0 && crustNum < 5) break;
                else System.out.println(Colors.ERROR + "Invalid Input!" + Colors.RESET + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        String crustType = "";
        switch (crustNum) {
            case 1 -> crustType = "thin";
            case 2 -> crustType = "regular";
            case 3 -> crustType = "thick";
            case 4 -> crustType = "cauliflower";
        }
        boolean stuffedCrust = false;
        while (true) {
            System.out.println("Stuffed Crust? (1-2)");
            try {
                int checker = parseInt(scan.nextLine());
                if (checker == 1 || checker == 2) {
                    if (checker == 1) stuffedCrust = true;
                    break;
                } else System.out.println(Colors.ERROR + "Invalid Input!" + Colors.RESET + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        Pizza pizza = new Pizza(size, crustType, stuffedCrust);

        int toppingChoice = -1;
        while (toppingChoice != 6) {
            System.out.println("Toppings options");
            System.out.println("1. Meat");
            System.out.println("2. Cheese");
            System.out.println("3. Other toppings");
            System.out.println("4. Select sauces");
            System.out.println("5. Side");
            System.out.println("6. Done");
            while (true) {
                try {
                    toppingChoice = parseInt(scan.nextLine());
                    if (toppingChoice > 0 && toppingChoice < 7) break;
                    else System.out.println(Colors.ERROR + "Invalid Input Enter 1-6!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            switch (toppingChoice) {
                case 1 -> processAddMeat(pizza);
                case 2 -> processAddCheese(pizza);
                case 3 -> processAddRegular(pizza);
                case 4 -> processAddSauces(pizza);
                case 5 -> processAddSides(pizza);
                case 6 -> {
                }
                default -> System.out.println(Colors.ERROR + "Invalid Input!" + Colors.RESET + "\n");
            }
        }
        currentOrder.addItem(pizza);

    }

       /* --------------------------------------------------------------------------
       Topping's Helper Functions

     * Displays available toppings (numbered).
     * Uses parseInt helper within try/catch wrapped in a while
     * to help get valid input.
     * Premium Toppings only, checks if topping name already in
     pizza's topping list to make isExtra true for correct pricing and toString.
      * Auto loops until user.
      *
      @param pizza has a toppings list to append to.
       -------------------------------------------------------------------------- */

    private void processAddMeat(Pizza pizza) {
        String[] meats = {"Pepperoni", "Sausage", "Ham", "Bacon", "Chicken", "Meatball"};
        System.out.println("Meat Toppings");
        int choice = -1;
        while (choice != 7) {
            IntStream.range(0, meats.length).forEach(i -> System.out.println(i + 1 + ". " + meats[i]));
            System.out.println("7. Done");
            while (true) {
                try {
                    choice = parseInt(scan.nextLine());
                    if (choice > 0 && choice < 8) break;
                    else System.out.println(Colors.ERROR + "Invalid Input Enter 1-7!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (choice == 7) {
                break;
            }
            String meatType = meats[choice - 1];
            boolean isExtra = pizza.getAllToppings().stream().anyMatch(topping -> topping.getName().equals(meatType));
            PremiumTopping topping = new Meat(meatType, isExtra, pizza.getSize());
            pizza.addTopping(topping);
        }
    }


    private void processAddCheese(Pizza pizza) {
        String[] cheeses = {"Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"};
        System.out.println("Cheese Toppings");
        int choice = -1;
        while (choice != 6) {
            IntStream.range(0, cheeses.length).forEach(i -> System.out.println(i + 1 + ". " + cheeses[i]));
            System.out.println("6. Done");
            while (true) {
                try {
                    choice = parseInt(scan.nextLine());
                    if (choice > 0 && choice < 7) break;
                    else System.out.println(Colors.ERROR + "Invalid Input Enter 1-7!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (choice == 6) {
                break;
            }
            String cheeseType = cheeses[choice - 1];
            boolean isExtra = pizza.getAllToppings().stream().anyMatch(topping -> topping.getName().equals(cheeseType));
            PremiumTopping topping = new Cheese(cheeseType, isExtra, pizza.getSize());
            pizza.addTopping(topping);
        }
    }

    private void processAddRegular(Pizza pizza) {
        String[] regularToppings = {"Onions", "Mushrooms", "Bell Peppers", "Olives", "Tomatoes", "Spinach", "Basil", "Pineapple", "Anchovies"};
        System.out.println("Regular Toppings");
        int choice = -1;
        while (choice != 10) {
            IntStream.range(0, regularToppings.length).forEach(i -> System.out.println(i + 1 + ". " + regularToppings[i]));
            System.out.println("10. Done");
            while (true) {
                try {
                    choice = parseInt(scan.nextLine());
                    if (choice > 0 && choice < 11) break;
                    else System.out.println(Colors.ERROR + "Invalid Input Enter 1-10!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (choice == 10) {
                break;
            }
            String regularType = regularToppings[choice - 1];
            Topping topping = new RegularTopping(regularType);
            pizza.addTopping(topping);
        }
    }

    private void processAddSauces(Pizza pizza) {
        String[] sauces = {"Marinara", "Alfredo", "Pesto", "BBQ", "Buffalo", "Olive oil"};
        System.out.println("Sauces");
        int choice = -1;
        while (choice != 7) {
            IntStream.range(0, sauces.length).forEach(i -> System.out.println(i + 1 + ". " + sauces[i]));
            System.out.println("7. Done");
            while (true) {
                try {
                    choice = parseInt(scan.nextLine());
                    if (choice > 0 && choice < 8) break;
                    else System.out.println(Colors.ERROR + "Invalid Input Enter 1-7!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (choice == 7) {
                break;
            }
            String sauce = sauces[choice - 1];
            Topping topping = new Sauce(sauce);
            pizza.addTopping(topping);
        }
    }

    private void processAddSides(Pizza pizza) {
        String[] sides = {"Red Pepper", "Parmesan"};
        System.out.println("Meat Toppings");
        int choice = -1;
        while (choice != 3) {
            IntStream.range(0, sides.length).forEach(i -> System.out.println(i + 1 + ". " + sides[i]));
            System.out.println("3. Done");
            while (true) {
                try {
                    choice = parseInt(scan.nextLine());
                    if (choice > 0 && choice < 4) break;
                    else System.out.println(Colors.ERROR + "Invalid Input Enter 1-3!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (choice == 3) {
                break;
            }
            String side = sides[choice - 1];
            Topping topping = new Sides(side);
            pizza.addTopping(topping);
        }
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

    /**
     * Confirms user would like to add a Garlic Knot
     * Wrapped in while loop to repeat.
     */
    private void processAddGarlicKnot() {
        while (true) {
            System.out.println("Would you like to add garlic knot? (Y/N)");
            String choice = scan.nextLine();
            while (true) {
                if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")) {
                    break;
                }
                System.out.println(Colors.ERROR + "Please enter Y or N" + Colors.RESET + "\n");
            }
            if (choice.equalsIgnoreCase("Y")) {
                currentOrder.addItem(new GarlicKnot());
            } else {
                return;
            }
        }
    }

    /**
     * Calls on order's displayOrder method to print every item
     * All items toString already overridden (clean prints).
     */
    private void processDisplayOrder() {
        currentOrder.displayOrder();
    }

    private void processRemoveItem() {
        System.out.println("Removing an item from the order");
        System.out.println("Enter number associated with item (1,2,3,...)");
        currentOrder.displayOrder();
        int choice = -1;
        int orderSize = currentOrder.getItems().size() + 1;
        while (choice != orderSize) {
            System.out.println(orderSize + ". Done");
            while (true) {
                try {
                    choice = parseInt(scan.nextLine());
                    if (choice > 0 && choice < orderSize + 1) break;
                    else
                        System.out.println(Colors.ERROR + "Invalid Input Enter 1-" + orderSize + "!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (choice == orderSize) {
                break;
            }
            System.out.println(Colors.SUCCESS + "Item removed!" + Colors.RESET + "\n");
            System.out.println(currentOrder.getItem(choice));
            currentOrder.removeItem(choice);

        }
    }

    private void processCheckout() {
    }

    /**
     * Reinitializes order to completely restart
     */
    private void processCancelOrder() {
        currentOrder = new Order();
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

