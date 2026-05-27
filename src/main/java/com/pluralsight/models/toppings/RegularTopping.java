package com.pluralsight.models.toppings;

public class RegularTopping extends Topping {

    public RegularTopping(String name) {
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
