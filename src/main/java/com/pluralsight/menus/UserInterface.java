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

    private final Scanner scan;
    Order currentOrder;

    public UserInterface() {
        scan = new Scanner(System.in);
        currentOrder = new Order();
    }


    public void Display() {

        // Main menu loop
        int choice = -1;
        while (choice != 2) {
            System.out.println(PremadeFormats.headingFormat("Welcome to the World's Best Pizzeria!!!"));
            System.out.println(Colors.TEXT + "1. Create a new order");
            System.out.println("2. Exit");
            System.out.print("Choice: " + Colors.RESET);

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

    /**
     * Main Ordering System
     */
    private void processNewOrder() {
        int choice = -1;
        while (choice != 7) {
            System.out.println(PremadeFormats.headingFormat("Order Screen!"));
            System.out.println(Colors.TEXT + "1. Add Pizza");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Garlic Knot");
            System.out.println("4. Display order");
            System.out.println("5. Remove Item");
            System.out.println("6. Checkout");
            System.out.println("7. Cancel order");
            System.out.print("Choice: " + Colors.RESET);

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

    /**
     * Allows user to choose between Signature and custom
     */
    private void processAddPizza() {
        // Handles Choosing between which Pizza user likes
        System.out.println(PremadeFormats.headingFormat("Add Pizza"));
        int choice;
        while (true) {
            System.out.println(Colors.TEXT + "1. Signature Pizzas");
            System.out.println("2. Create Custom Pizza");
            System.out.println("3. Exit");
            System.out.print("Choice: " + Colors.RESET);
            try {
                choice = parseInt(scan.nextLine());
                if (choice > 0 && choice < 4) break;
                else System.out.println(Colors.ERROR + "\nInvalid Choice!" + Colors.RESET + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // User choices to add a Signature Pizza

        if (choice == 1) {
            // Map has name and Pizza instance
            Map<String, Pizza> pizzas = loadSignaturePizzas();
            // Help give pizza's values indexes
            List<Pizza> pizzasIndexedValues = new ArrayList<>(pizzas.values());
            int signatureChoice;
            int i;
            while (true) {
                i = 1;
                System.out.println(PremadeFormats.headingFormat("Signature Pizzas"));
                // Prints both the name of signature and the toString
                for (Map.Entry<String, Pizza> entry : pizzas.entrySet()) {
                    System.out.println(Colors.TEXT + "\n" + i + ". " + entry.getKey());
                    System.out.println(entry.getValue());
                    i += 1;
                }
                System.out.println(i + ". Exit");
                System.out.print("Choice: " + Colors.RESET);
                // Gets the user choice on signature pizza
                try {
                    signatureChoice = parseInt(scan.nextLine());
                    if (signatureChoice > 0 && signatureChoice < i + 1) break;
                    else System.out.println(Colors.ERROR + "\nInvalid size!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            // If user choices exit
            if (signatureChoice == pizzas.size() + 1) return;

            // Uses user choice to match the index with array to create an instance here
            Pizza pizza = pizzasIndexedValues.get(signatureChoice - 1);
            int customizeChoice;
            // Customizing pizza
            while (true) {
                System.out.println(PremadeFormats.headingFormat("Customize Pizza"));
                System.out.println(Colors.TEXT + "1. Yes");
                System.out.println("2. No");
                System.out.print("Choice: " + Colors.RESET);
                try {
                    customizeChoice = parseInt(scan.nextLine());
                    if (customizeChoice == 1 || customizeChoice == 2) break;
                    System.out.println(Colors.ERROR + "\nInvalid Choice!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            // If customizing use helper function to handle all removing and adding
            if (customizeChoice == 1) customizePremadePizza(pizza);
            // After pizza's topping list is update add list
            currentOrder.addItem(pizza);
            System.out.println(pizza);
            System.out.println(Colors.SUCCESS + "\nPizza Added Successfully!" + Colors.RESET);

        }
        // user choices have a custom pizza
        else if (choice == 2) {
            System.out.println(PremadeFormats.headingFormat("Creating Custom Pizza"));
            int sizeNum;
            while (true) {
                System.out.println(PremadeFormats.headingFormat("Pizza Size? (1-4)"));
                System.out.println(Colors.TEXT + "1. 8\"");
                System.out.println("2. 12\"");
                System.out.println("3. 16\"");
                System.out.println("4. Exit");
                System.out.print("Choice: " + Colors.RESET);
                try {
                    sizeNum = parseInt(scan.nextLine());
                    if (sizeNum > 0 && sizeNum < 5) break;
                    else System.out.println(Colors.ERROR + "\nInvalid size!" + Colors.RESET + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            // User decides to cancel pizza order
            if (sizeNum == 4) return;
            int size = 0;
            switch (sizeNum) {
                case 1 -> size = 8;
                case 2 -> size = 12;
                case 3 -> size = 16;
            }
            /**
             * Gets crustType and boolean stuffedCrust
             */
            int crustNum;
            while (true) {
                System.out.println(PremadeFormats.headingFormat("Type of Crust? (1-4)"));
                System.out.println(Colors.TEXT + "1. Thin");
                System.out.println("2. Regular");
                System.out.println("3. Thick");
                System.out.println("4. Cauliflower");
                System.out.print("Choice: " + Colors.RESET);
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
                System.out.println(PremadeFormats.headingFormat("Stuffed Crust? (1-2)"));
                System.out.println(Colors.TEXT + "1. Yes");
                System.out.println("2. No");
                System.out.print("Choice: " + Colors.RESET);
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
            /**
             * Loops list to print all toppings (numbered 1,2,...)
             * Makes sure toppingChoice is valid using getMenuChoice
             * Uses valid choice with switch case
             */
            Pizza pizza = new Pizza(size, crustType, stuffedCrust);

            String[] toppings = {"Meat", "Cheese", "Regular toppings", "Sauces", "Side"};
            int toppingChoice;
            while (true) {
                System.out.println(PremadeFormats.headingFormat("Toppings options"));
                toppingChoice = getMenuChoice(toppings);
                if (toppingChoice == toppings.length + 1) break;
                switch (toppingChoice) {
                    case 1 -> processAddMeat(pizza);
                    case 2 -> processAddCheese(pizza);
                    case 3 -> processAddRegular(pizza);
                    case 4 -> processAddSauces(pizza);
                    case 5 -> processAddSides(pizza);
                    default -> System.out.println(Colors.ERROR + "\nInvalid Input!" + Colors.RESET + "\n");
                }
            }
            // After pizza has all the toppings customized auto add to order
            currentOrder.addItem(pizza);
        }
    }

    /**
     * Handles if adding or removing toppings from premade pizzas
     *
     * @param pizza Updates the topping's list
     */
    private void customizePremadePizza(Pizza pizza) {
        System.out.println(PremadeFormats.headingFormat("Customizing Pizza"));
        String[] options = {"Add Toppings", "Remove Toppings"};
        int choice;
        while (true) {
            choice = getMenuChoice(options) - 1;
            if (choice == options.length) break;
                // For removing toppings
            else if (choice == 1) {
                while (true) {
                    // Saves a copy of pizza's list
                    List<Topping> currentTopping = pizza.getAllToppings();
                    System.out.println(PremadeFormats.headingFormat("Current Toppings"));
                    IntStream.range(0, currentTopping.size()).forEach(i -> System.out.println(i + 1 + ". " + Colors.TEXT + currentTopping.get(i)));
                    System.out.println(currentTopping.size() + 1 + ". Exit");
                    System.out.print("Choice: " + Colors.RESET);
                    int removingChoice;
                    while (true) {
                        try {
                            removingChoice = parseInt(scan.nextLine());
                            if (removingChoice > 0 && removingChoice <= currentTopping.size() + 1) break;
                            System.out.println(Colors.ERROR + "Invalid Input!" + Colors.RESET + "\n");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());

                        }
                    }
                    if (removingChoice == currentTopping.size() + 1) break;
                    // User choice -1 to match index of topping's displayed
                    System.out.println(Colors.SUCCESS + currentTopping.get(removingChoice - 1) + " Removed Successfully!" + Colors.RESET);
                    currentTopping.remove(removingChoice - 1);
                    pizza.setToppings(currentTopping);
                }
            }
            // Adds Topping
            else if (choice == 0) {
                // Reuses previous logic for going to different methods to add to pizza's topping lsit
                String[] toppings = {"Meat", "Cheese", "Regular toppings", "Sauces", "Side"};
                int toppingChoice;
                while (true) {
                    System.out.println(PremadeFormats.headingFormat("Toppings options"));
                    toppingChoice = getMenuChoice(toppings);
                    if (toppingChoice == toppings.length + 1) break;
                    switch (toppingChoice) {
                        case 1 -> processAddMeat(pizza);
                        case 2 -> processAddCheese(pizza);
                        case 3 -> processAddRegular(pizza);
                        case 4 -> processAddSauces(pizza);
                        case 5 -> processAddSides(pizza);
                        default -> System.out.println(Colors.ERROR + "\nInvalid Input!" + Colors.RESET + "\n");
                    }
                }
            }
        }


    }


       /* --------------------------------------------------------------------------
       Topping's Helper Functions

     * Displays available toppings (numbered) and check inputs
     * using getMenuChoice
     * Premium Toppings only, checks if topping name already in
     pizza's topping list to make isExtra true for correct pricing and toString.
     *
      @param pizza has a toppings list to append to.
       -------------------------------------------------------------------------- */

    private void processAddMeat(Pizza pizza) {

        String[] meats = {"Pepperoni", "Sausage", "Ham", "Bacon", "Chicken", "Meatball"};
        int choice;
        while (true) {
            System.out.println(PremadeFormats.headingFormat("Meat Toppings"));
            choice = getMenuChoice(meats) - 1;
            if (choice == meats.length) break;
            String meatType = meats[choice];
            boolean isExtra = pizza.getAllToppings().stream().anyMatch(topping -> topping.getName().equals(meatType));
            pizza.addTopping(new Meat(meatType, isExtra, pizza.getSize()));
            System.out.println("Add More!");
        }
    }


    private void processAddCheese(Pizza pizza) {

        String[] cheeses = {"Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"};
        int choice;
        while (true) {
            System.out.println(PremadeFormats.headingFormat("Cheese Toppings"));
            choice = getMenuChoice(cheeses) - 1;
            if (choice == cheeses.length) break;
            String cheeseType = cheeses[choice];
            boolean isExtra = pizza.getAllToppings().stream().anyMatch(topping -> topping.getName().equals(cheeseType));
            pizza.addTopping(new Cheese(cheeseType, isExtra, pizza.getSize()));
            System.out.println("Add More!");
        }
    }

    private void processAddRegular(Pizza pizza) {

        String[] regularToppings = {"Onions", "Mushrooms", "Bell Peppers", "Olives", "Tomatoes", "Spinach", "Basil", "Pineapple", "Anchovies"};

        int choice;
        while (true) {
            System.out.println(PremadeFormats.headingFormat("Regular Toppings"));
            choice = getMenuChoice(regularToppings) - 1;
            if (choice == regularToppings.length) break;
            String regularType = regularToppings[choice];
            Topping topping = new RegularTopping(regularType);
            pizza.addTopping(topping);
            System.out.println("Add More!");
        }
    }

    private void processAddSauces(Pizza pizza) {
        String[] sauces = {"Marinara", "Alfredo", "Pesto", "BBQ", "Buffalo", "Olive oil"};

        int choice;
        while (true) {
            System.out.println(PremadeFormats.headingFormat("Sauces"));
            choice = getMenuChoice(sauces) - 1;
            if (choice == sauces.length) break;
            String sauceType = sauces[choice];
            Topping sauce = new Sauce(sauceType);
            pizza.addTopping(sauce);
            System.out.println("Add More!");
        }
    }

    private void processAddSides(Pizza pizza) {
        String[] sides = {"Red Pepper", "Parmesan"};
        int choice;
        while (true) {
            System.out.println(PremadeFormats.headingFormat("Sides"));
            choice = getMenuChoice(sides) - 1;
            if (choice == sides.length) break;
            String sideType = sides[choice];
            Topping side = new Sauce(sideType);
            pizza.addTopping(side);
            System.out.println("Add More!");
        }
    }

    /**
     * Handles getting drink size and flavor.
     */

    private void processAddDrink() {

        String[] drinks = {"Coca cola", "Starry", "Coke zero", "Fanta", "Dr.Pepper", "Ice-Tea"};
        String[] cupSize = {"Small", "Medium", "Large"};
        // Main loop repeats asking to add more drinks

        System.out.println(PremadeFormats.headingFormat("Drink Menu"));

        // Allows user to exit or continue with adding drink to order


        while (true) {
            int cupSizeChoice;
            System.out.println(PremadeFormats.headingFormat("Enter a Cup Size (1-3)"));
            cupSizeChoice = getMenuChoice(cupSize) - 1;
            if (cupSizeChoice == cupSize.length) break;

            // Loops through drinks list while printing an index

            IntStream.range(0, drinks.length).forEach(i -> System.out.println(i + 1 + ". " + drinks[i]));
            int flavorChoice;
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

            Item drink = new Drink(drinks[flavorChoice - 1], cupSize[cupSizeChoice]);
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


        int choice;
        while (true) {
            System.out.println(PremadeFormats.headingFormat("Garlic Knots"));
            System.out.println(Colors.TEXT + "Pick a flavor (1-6)" + Colors.RESET);
            choice = getMenuChoice(garlicKnots) - 1;
            if (choice == garlicKnots.length) break;
            String garlicKnotType = garlicKnots[choice];
            Item flavoredKnot = new GarlicKnot(garlicKnotType);
            currentOrder.addItem(flavoredKnot);
            System.out.println(currentOrder.getItem(currentOrder.getItems().size()));
            System.out.println(Colors.SUCCESS + "Garlic Knot added!" + Colors.RESET + "\n");
            System.out.println("Add More!");
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
        System.out.println(PremadeFormats.headingFormat("Remove Item"));
        System.out.println(Colors.TEXT + "Enter number associated with item (1,2,3,...)");
        int choice = -1;
        int orderSize = currentOrder.getItems().size() + 1;
        while (choice != orderSize) {
            currentOrder.displayOrder();
            System.out.println(orderSize + ". Done");
            System.out.print("Choice: " + Colors.RESET);
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
        System.out.println(PremadeFormats.headingFormat("Checkout"));
        currentOrder.displayOrder();
        ReceiptManager.createReceipt(currentOrder.getItems());
        currentOrder = new Order();
    }

    /**
     * Reinitialize order to completely restart
     */
    private void processCancelOrder() {
        currentOrder = new Order();
    }

    /* --------------------------------------------------------------------------
       Helper Functions
       -------------------------------------------------------------------------- */

    /**
     * Loops through options while numbering, extra print for exit
     * Uses helper parseInt() within a try/catch within a while loop
     * to repeat until user input valid.
     * Used by all topping sub-menus
     *
     * @param options All options for within menu
     * @return int associated with the prints
     */

    private int getMenuChoice(String[] options) {
        IntStream.range(0, options.length).forEach(i -> System.out.println(i + 1 + Colors.TEXT + ". " + options[i]));
        System.out.println(options.length + 1 + ". Exit");
        System.out.print("Choice: " + Colors.RESET);
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

    /**
     * Parse string input
     * if s is empty throws Exception with Custom message.
     * Uses try/catch to check if user input valid.
     * If s is not a number throws Exception
     *
     * @return validated int
     */
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

