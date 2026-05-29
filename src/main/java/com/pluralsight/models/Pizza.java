package com.pluralsight.models;

import com.pluralsight.models.toppings.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pizza implements Item {
    private int size;
    private String crustType;
    private boolean stuffedCrust;
    private List<Topping> toppings;

    public Pizza(int size, String crustType, boolean stuffedCrust) {
        this.size = size;
        this.crustType = crustType;
        this.stuffedCrust = stuffedCrust;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(int index) {
        this.toppings.remove(index);
    }

    public List<Topping> getAllToppings() {
        return this.toppings;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        switch (size) {
            case 8 -> totalPrice = 8.5 + toppings.stream().mapToDouble(Topping::getPrice).sum();

            case 12 -> totalPrice = 12 + toppings.stream().mapToDouble(Topping::getPrice).sum();

            case 16 -> totalPrice = 16.5 + toppings.stream().mapToDouble(Topping::getPrice).sum();

        }
        return totalPrice;
    }

    @Override
    public String toString() {
        List<RegularTopping> regularToppings = new ArrayList<>();
        List<Meat> meats = new ArrayList<>();
        List<Cheese> cheeses = new ArrayList<>();
        List<Sauce> sauce = new ArrayList<>();
        List<Side> sides = new ArrayList<>();
        for (Topping topping : toppings) {
            if (topping instanceof RegularTopping) regularToppings.add((RegularTopping) topping);
            else if (topping instanceof Meat) meats.add((Meat) topping);
            else if (topping instanceof Cheese) cheeses.add((Cheese) topping);
            else if (topping instanceof Sauce) sauce.add((Sauce) topping);
            else if (topping instanceof Side) sides.add((Side) topping);
        }
        regularToppings.sort(Comparator.comparing(RegularTopping::getName));
        meats.sort(Comparator.comparing(Meat::getName));
        cheeses.sort(Comparator.comparing(Cheese::getName));
        sauce.sort(Comparator.comparing(Sauce::getName));
        sides.sort(Comparator.comparing(Side::getName));

        StringBuilder sb = new StringBuilder();
        sb.append(size + "\" Pizza ");
        if (stuffedCrust) {
            sb.append("(StuffedCrust) \n");
        }
        double basePrice = 0;
        if (size == 8) basePrice = 8.50;
        else if (size == 12) basePrice = 12.00;
        else basePrice = 16.50;
        sb.append(String.format("Base Price: $%.2f%n", basePrice));
        sb.append("Crust: " + crustType + "\n");
        if (toppings.size() > 0) {
            sb.append("Toppings\n");
            if (regularToppings.size() > 0) {
                sb.append("Regular Toppings: ");
                for (int i = 0; i < regularToppings.size(); i++) {
                    sb.append(regularToppings.get(i) + (i == regularToppings.size() - 1 ? "\n" : ", "));
                }
            }
            if (meats.size() > 0) {
                sb.append("Meat Toppings: ");
                for (int i = 0; i < meats.size(); i++) {
                    sb.append(meats.get(i) + (i == meats.size() - 1 ? "\n" : ", "));
                }
            }
            if (cheeses.size() > 0) {
                sb.append("Cheese Toppings: ");
                for (int i = 0; i < cheeses.size(); i++) {
                    sb.append(cheeses.get(i) + (i == cheeses.size() - 1 ? "\n" : ", "));
                }
            }
            if (sauce.size() > 0) {
                sb.append("Sauce: ");
                for (int i = 0; i < sauce.size(); i++) {
                    sb.append(sauce.get(i) + (i == sauce.size() - 1 ? "\n" : ", "));
                }
            }
            if (sides.size() > 0) {
                sb.append("Side Toppings: ");
                for (int i = 0; i < sides.size(); i++) {
                    sb.append(sides.get(i) + (i == sides.size() - 1 ? "\n" : ", "));
                }
            }
        }
        sb.append(String.format("Total Pizza Price: $%.2f%n", getPrice()));
        return sb.toString();
    }

}
