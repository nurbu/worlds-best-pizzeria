package com.pluralsight.models.toppings;

/**
 * Handles all Premium Toppings subclasses
 * getPrice gets Overridden for each child's unique prices.
 * toString gets reused in all children classes.
 */
public abstract class PremiumTopping extends Topping {
    private String name;
    private boolean isExtra;
    private String pizzaSize;

    public PremiumTopping(String name, boolean isExtra, String pizzaSize) {
        super(name);
        this.isExtra = isExtra;
        this.pizzaSize = pizzaSize;
    }

    /**
     * Simplifies printing to receipt
     *
     * @return isExtra checked.
     */
    public String toString() {
        if (isExtra) {
            return getName() + " (Extra) " + getPrice();
        }
        return getName() + " " + getPrice();
    }
}
