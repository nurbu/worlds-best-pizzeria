package com.pluralsight.models;

public class Drink implements Item {
    private String flavor;
    private String size;
    private double price;

    public Drink(String flavor, String size) {
        this.flavor = flavor;
        this.size = size;
        setPrice();
    }

    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }

    private void setPrice() {
        switch (this.size) {
            case "S" -> price = 2.00;
            case "M" -> price = 2.50;
            case "L" -> price = 3.00;
        }
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return size + " " + flavor + " $" + price;
    }
}
