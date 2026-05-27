package com.pluralsight.models.toppings;

public class Cheese extends PremiumTopping {

    private String name;
    private boolean isExtra;
    private String pizzaSize;

    public Cheese(String name, boolean isExtra, String pizzaSize) {
        super(name, isExtra, pizzaSize);

    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        switch (pizzaSize.trim().toUpperCase()) {
            case "S" -> {
                if (isExtra) totalPrice = 0.30;
                else totalPrice = 0.75;
            }

            case "M" -> {
                if (isExtra) totalPrice = 0.60;
                else totalPrice = 1.00;
            }

            case "L" -> {
                if (isExtra) totalPrice = 0.90;
                else totalPrice = 2.25;
            }

        }
        return totalPrice;
    }
}
