package com.pluralsight.models.toppings;

public class Cheese extends PremiumTopping {

    public Cheese(String name, boolean isExtra, int pizzaSize) {
        super(name, isExtra, pizzaSize);
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        switch (pizzaSize) {
            case 8 -> {
                if (isExtra) totalPrice = 0.30;
                else totalPrice = 0.75;
            }

            case 12 -> {
                if (isExtra) totalPrice = 0.60;
                else totalPrice = 1.00;
            }

            case 16 -> {
                if (isExtra) totalPrice = 0.90;
                else totalPrice = 2.25;
            }

        }
        return totalPrice;
    }
}
