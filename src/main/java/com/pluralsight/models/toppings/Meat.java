package com.pluralsight.models.toppings;

public class Meat extends PremiumTopping {

    /**
     * Based off isExtra applies correct pricing.
     *
     * @param name
     * @param isExtra
     * @param pizzaSize
     */
    public Meat(String name, boolean isExtra, int pizzaSize) {
        super(name, isExtra, pizzaSize);
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        switch (pizzaSize) {
            case 8 -> {
                if (isExtra) totalPrice = 0.50;
                else totalPrice = 1.00;
            }

            case 12 -> {
                if (isExtra) totalPrice = 1.00;
                else totalPrice = 2.00;
            }

            case 16 -> {
                if (isExtra) totalPrice = 1.50;
                else totalPrice = 3.00;
            }
        }
        return totalPrice;
    }
}

