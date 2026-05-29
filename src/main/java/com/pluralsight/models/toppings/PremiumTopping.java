package com.pluralsight.models.toppings;

/**
 * Handles all Premium Toppings subclasses
 * getPrice gets Overridden for each child's unique prices.
 * toString gets reused in all children classes.
 */
public abstract class PremiumTopping extends Topping {
    protected boolean isExtra;
    protected int pizzaSize;

    public PremiumTopping(String name, boolean isExtra, int pizzaSize) {
        super(name);
        this.isExtra = isExtra;
        this.pizzaSize = pizzaSize;
    }

    /**
     * Simplifies printing to receipt
     *
     * @return isExtra checked.
     */
    @Override
    public String toString() {
        if (isExtra) {
            return String.format("%s (Extra) $%.2f", getName(), getPrice());
        }
        return String.format("%s $%.2f", getName(), getPrice());
    }
}
