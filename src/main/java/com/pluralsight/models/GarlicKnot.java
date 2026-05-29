package com.pluralsight.models;

public class GarlicKnot implements Item {

    private String flavor;

    public GarlicKnot(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return 1.5;
    }

    @Override
    public String toString() {
        return String.format("Garlic Knot %s $%.2f%n", flavor, 1.50);
    }
}
