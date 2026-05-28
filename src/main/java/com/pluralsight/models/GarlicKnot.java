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
        return "Garlic Knot " + flavor + " $" + 1.50;
    }
}
