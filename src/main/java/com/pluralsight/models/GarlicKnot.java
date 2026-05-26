package com.pluralsight.models;

public class GarlicKnot implements Item {


    @Override
    public double getPrice() {
        return 1.5;
    }

    @Override
    public String toString() {
        return "Garlic Knot $" + 1.50;
    }
}
