package com.pluralsight.menus;

import com.pluralsight.managers.ReceiptManager;
import com.pluralsight.models.*;
import com.pluralsight.models.toppings.*;
import com.pluralsight.utilities.Colors;
import com.pluralsight.utilities.PremadeFormats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import static com.pluralsight.models.SignaturePizza.loadSignaturePizzas;

public class UserInterface {

    private Scanner scan;
    Order currentOrder;

    public UserInterface() {
        scan = new Scanner(System.in);
        currentOrder = new Order();
    }


    public void Display() {

        // Main menu loop
        int choice = -1;
        while (choice != 2) {
            System.out.println(Colors.HEADER + "\nWelcome to the World's Best Pizzeria!!!" + Colors.RESET + "\n");
            System.out.println(PremadeFormats.SINGLE_DASHES);

            System.out.println("1. Create a new order");
            System.out.println("2. Exit");
            System.out.print("Choice: ");

            if (!scan.hasNextInt()) {
                System.out.println(Colors.ERROR + "\nPlease enter 1 or 2." + Colors.RESET + "\n");
                scan.nextLine();                 // discard bad input
                continue;
            }
            choice = scan.nextInt();
            scan.nextLine();                     // clear newline

            switch (choice) {
                case 1 -> processNewOrder();
                case 2 -> System.out.println(Colors.SUCCESS + "\nThank you for visiting our pizzeria!" + Colors.RESET);
                default -> System.out.println(Colors.ERROR + "\nInvalid choice!" + Colors.RESET + "\n");
            }
        }
    }

    private void processNewOrder() {
        int choice = -1;
        while (choice != 7) {
            System.out.println(Colors.HEADER + "\nOrder Screen!" + Colors.RESET + "\n");
            System.out.println(PremadeFormats.SINGLE_DASHES_COLORFUL);

            System.out.println("1. Add Pizza");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Garlic Knot");
            System.out.println("4. Display order");
            System.out.println("5. Remove Item");
            System.out.println("6. Checkout");
            System.out.println("7. Cancel order");
            System.out.print("Choice: ");

            if (!scan.hasNextInt()) {
                System.out.println(Colors.ERROR + "\nPlease enter 1-7." + Colors.RESET + "\n");
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
                default -> System.out.println(Colors.ERROR + "\nInvalid Choice!" + Colors.RESET + "\n");
            }
        }
    }

    private void processAddPizza() {
        System.out.println(PremadeFormats.PIZZA_HOME);
        int choice;
        while (true) {
            System.out.println("1. Signature Pizzas");
            System.out.println("2. Create Custom Pizza");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            try {
                choice = parseInt(scan.nextLine());
                if (choice > 0 && choice < 4) break;
                else System.out.println(Colors.ERROR + "\nInvalid Choice!" + Colors.RESET + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if (choice == 3) return;
        else if (choice == 1) {
            Map<String, Pizza> pizzas = loadSignaturePizzas();
            List<Pizza> pizzasIndexedValues = new ArrayList<>(pizzas.values());
            int signatureChoice = -1;
            int i;
            while (true) {
                i = 1;
                System.out.println("Signature Pizzas");
                for (Map.Entry<String, Pizza> entry : pizzas.entrySet()) {
                    System.out.println("\n" + i + ". " + entry.getKey());
                    System.out.println(entry.getValue());
                    i += 1;
                }
                System.out.println(i + ". Exit");
                System.out.print("Choice: ");
                try {
                    signatureChoice = parseInt(scan.nextLine());
                    if (signatureChoice > 0 && signatureChoice < i + 1) break;
                    else System.out.println(Colors.ERROR + "\nInvalid size!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (signatureChoice == pizzas.size() + 1) return;
            Pizza pizza = pizzasIndexedValues.get(signatureChoice - 1);
            System.out.println("Would you like to customize this pizza? (1-2)");

        } else if (choice == 2) {
            System.out.println("\n" + PremadeFormats.DOUBLE_DASHES_COLORFUL);
            System.out.println("\nCreating Custom Pizza");
            System.out.println(PremadeFormats.SINGLE_DASHES_COLORFUL);
            int sizeNum;
            while (true) {
                System.out.println("\nPizza Size? (1-4)");
                System.out.println("1. 8\"");
                System.out.println("2. 12\"");
                System.out.println("3. 16\"");
                System.out.println("4. Exit");
                System.out.print("Choice: ");
                try {
                    sizeNum = parseInt(scan.nextLine());
                    if (sizeNum > 0 && sizeNum < 4) break;
                    else System.out.println(Colors.ERROR + "\nInvalid size!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (sizeNum == 4) return;
            int size = 0;
            switch (sizeNum) {
                case 1 -> size = 8;
                case 2 -> size = 12;
                case 3 -> size = 16;
            }
            int crustNum = 0;
            while (true) {
                System.out.println("\nType of Crust? (1-4)");
                System.out.println("\n1. Thin");
                System.out.println("2. Regular");
                System.out.println("3. Thick");
                System.out.println("4. Cauliflower");
                System.out.print("Choice: ");
                try {
                    crustNum = parseInt(scan.nextLine());
                    if (crustNum > 0 && crustNum < 5) break;
                    else System.out.println(Colors.ERROR + "\nInvalid Input!" + Colors.RESET + "\n");
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
                System.out.println("\nStuffed Crust? (1-2)");
                System.out.println("1. Yes");
                System.out.println("2. No");
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
                System.out.println("\nToppings options");
                System.out.println("\n1. Meat");
                System.out.println("2. Cheese");
                System.out.println("3. Other toppings");
                System.out.println("4. Select sauces");
                System.out.println("5. Side");
                System.out.println("6. Exit");
                System.out.print("Choice: ");
                while (true) {
                    try {
                        toppingChoice = parseInt(scan.nextLine());
                        if (toppingChoice > 0 && toppingChoice < 7) break;
                        else System.out.println(Colors.ERROR + "\nInvalid Input Enter 1-6!" + Colors.RESET + "\n");
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
                    default -> System.out.println(Colors.ERROR + "\nInvalid Input!" + Colors.RESET + "\n");
                }
            }

            currentOrder.addItem(pizza);
        }

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
        int choice;
        while (true) {
            System.out.println("\nMeat Toppings\n");
            choice = getMenuChoice(meats) - 1;
            if (choice == meats.length) break;
            String meatType = meats[choice];
            boolean isExtra = pizza.getAllToppings().stream().anyMatch(topping -> topping.getName().equals(meatType));
            pizza.addTopping(new Meat(meatType, isExtra, pizza.getSize()));
            System.out.println("Add More!\n");
        }
    }


    private void processAddCheese(Pizza pizza) {

        String[] cheeses = {"Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"};
        int choice;
        while (true) {
            System.out.println("\nCheese Toppings\n");
            choice = getMenuChoice(cheeses) - 1;
            if (choice == cheeses.length) break;
            String cheeseType = cheeses[choice];
            boolean isExtra = pizza.getAllToppings().stream().anyMatch(topping -> topping.getName().equals(cheeseType));
            pizza.addTopping(new Cheese(cheeseType, isExtra, pizza.getSize()));
            System.out.println("Add More!\n");
        }
    }

    private void processAddRegular(Pizza pizza) {

        String[] regularToppings = {"Onions", "Mushrooms", "Bell Peppers", "Olives", "Tomatoes", "Spinach", "Basil", "Pineapple", "Anchovies"};

        int choice;
        while (true) {
            System.out.println("Regular Toppings");
            choice = getMenuChoice(regularToppings) - 1;
            if (choice == regularToppings.length) break;
            String regularType = regularToppings[choice];
            Topping topping = new RegularTopping(regularType);
            pizza.addTopping(topping);
            System.out.println("Add More!\n");
        }
    }

    private void processAddSauces(Pizza pizza) {
        String[] sauces = {"Marinara", "Alfredo", "Pesto", "BBQ", "Buffalo", "Olive oil"};

        int choice;
        while (true) {
            System.out.println("\nSauces\n");
            choice = getMenuChoice(sauces) - 1;
            if (choice == sauces.length) break;
            String sauceType = sauces[choice];
            Topping sauce = new Sauce(sauceType);
            pizza.addTopping(sauce);
            System.out.println("Add More!\n");
        }
    }

    private void processAddSides(Pizza pizza) {
        String[] sides = {"Red Pepper", "Parmesan"};
        int choice;
        while (true) {
            System.out.println("\nSides\n");
            choice = getMenuChoice(sides) - 1;
            if (choice == sides.length) break;
            String sideType = sides[choice];
            Topping side = new Sauce(sideType);
            pizza.addTopping(side);
            System.out.println("Add More!\n");
        }
    }

    /**
     * Handles getting drink size and flavor.
     */

    private void processAddDrink() {

        String[] drinks = {"Coca cola", "Starry", "Coke zero", "Fanta", "Dr.Pepper", "Ice-Tea"};
        String[] cupSize = {"Small", "Medium", "Large"};
        int cupChoice;
        /**
         * Main loop repeats asking to add more drinks
         */
        System.out.println(PremadeFormats.DOUBLE_DASHES_COLORFUL);
        System.out.println(Colors.HEADER + "\nDrink Menu!" + Colors.RESET + "\n");
        System.out.println(PremadeFormats.SINGLE_DASHES_COLORFUL);

        // Allows user to exit or continue with adding drink to order

        while (true) {
            System.out.println("\nEnter a Cup Size (1-3)");
            IntStream.range(0, cupSize.length).forEach(i -> System.out.println(i + 1 + ". " + cupSize[i]));
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            while (true) {
                try {
                    cupChoice = parseInt(scan.nextLine());
                    if (cupChoice > 0 && cupChoice < 5) break;
                    else System.out.println(Colors.ERROR + "\nInvalid Input Enter 1-4!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (cupChoice == 4) {
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
                        System.out.println(Colors.ERROR + "\nEnter a number 1-6\n" + Colors.RESET + "\n");
                    } else {
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Creates a drink object

            Item drink = new Drink(drinks[flavorChoice - 1], cupSize[cupChoice - 1]);
            currentOrder.addItem(drink);
            System.out.println(currentOrder.getItem(currentOrder.getItems().size()));
            System.out.println(Colors.SUCCESS + "\nDrink added!" + Colors.RESET + "\n");
        }


    }

    /**
     * Confirms user would like to add a Garlic Knot
     * Wrapped in while loop to repeat.
     */
    private void processAddGarlicKnot() {
        String[] garlicKnots = {"Classic", "Parmesan", "Cheesy", "Pizza-style", "Spicy", "Everything Bagel"};
        System.out.println("\nGarlic Knots");


        int choice = -1;
        while (choice != 7) {
            System.out.println("Pick a flavor (1-6)");
            IntStream.range(0, garlicKnots.length).forEach(i -> System.out.println(i + 1 + ". " + garlicKnots[i]));
            System.out.println("7. Exit");
            System.out.print("Choice: ");
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
            String flavor = garlicKnots[choice - 1];
            GarlicKnot garlicKnot = new GarlicKnot(flavor);

            currentOrder.addItem(garlicKnot);
            System.out.println(currentOrder.getItem(currentOrder.getItems().size()));
            System.out.println(Colors.SUCCESS + "Garlic Knot added!" + Colors.RESET + "\n");

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
        int choice = -1;
        int orderSize = currentOrder.getItems().size() + 1;
        while (choice != orderSize) {
            currentOrder.displayOrder();
            System.out.println(orderSize + ". Done");
            System.out.print("Choice: ");
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
            orderSize -= 1;
        }
    }

    /**
     * Displays Order
     * Send all items in order to be written onto the receipt.
     */
    private void processCheckout() {
        System.out.println("Checking out\n");
        currentOrder.displayOrder();
        ReceiptManager.createReceipt(currentOrder.getItems());
    }

    /**
     * Reinitializes order to completely restart
     */
    private void processCancelOrder() {
        currentOrder = new Order();
    }

    /* --------------------------------------------------------------------------
       Helper Functions

     * Parse int strings
     * if s is empty throws Exception with Custom message (skipped by user)
     * Uses try/catch to check if user input valid.
     * if s is not a number or in a int format throws Exception

       -------------------------------------------------------------------------- */

    private int getMenuChoice(String[] options) {
        IntStream.range(0, options.length).forEach(i -> System.out.println(i + 1 + ". " + options[i]));
        System.out.println(options.length + 1 + ". Exit");
        System.out.print("Choice:");
        while (true) {
            try {
                int choice = parseInt(scan.nextLine());
                if (choice > 0 && choice <= options.length + 1) return choice;
                System.out.println(Colors.ERROR + "Invalid Input!" + Colors.RESET + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int parseInt(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException(Colors.WARN + "Input can't be empty" + Colors.RESET);
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Colors.WARN + "Invalid not a number: " + s + Colors.RESET);
        }
    }

}

