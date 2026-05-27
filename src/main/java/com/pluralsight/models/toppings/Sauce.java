package com.pluralsight.models.toppings;

public class Sauce extends Topping {

    public Sauce(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
