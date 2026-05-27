package com.pluralsight.models.toppings;

public class Meat extends PremiumTopping {
    private String name;
    private boolean isExtra;
    private String pizzaSize;

    public Meat(String name, boolean isExtra, String pizzaSize) {
        super(name, isExtra, pizzaSize);

    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        switch (pizzaSize.trim().toUpperCase()) {
            case "S" -> {
                if (isExtra) totalPrice = 0.50;
                else totalPrice = 1.00;
            }

            case "M" -> {
                if (isExtra) totalPrice = 1.00;
                else totalPrice = 2.00;
            }

            case "L" -> {
                if (isExtra) totalPrice = 1.50;
                else totalPrice = 3.00;
            }

        }
        return totalPrice;
    }
}
