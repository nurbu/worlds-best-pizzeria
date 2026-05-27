package com.pluralsight.models.toppings;

public class Sides extends Topping {

    public Sides(String name) {
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
