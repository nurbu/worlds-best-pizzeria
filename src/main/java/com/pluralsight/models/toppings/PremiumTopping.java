package com.pluralsight.models.toppings;

public abstract class PremiumTopping extends Topping {

    private boolean isExtra;
    private String pizzaSize;

    public PremiumTopping(String name, boolean isExtra, String pizzaSize) {
        super(name);
        this.isExtra = isExtra;
        this.pizzaSize = pizzaSize;
    }
}
