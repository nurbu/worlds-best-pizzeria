package com.pluralsight.models;

import com.pluralsight.models.toppings.Topping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Pizza implements Item {
    private String size;
    private String crustType;
    private boolean stuffedCrust;
    private List<Topping> toppings;

    public Pizza(String size, String crustType, boolean stuffedCrust) {
        this.size = size;
        this.crustType = crustType;
        this.stuffedCrust = stuffedCrust;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(int index) {
        this.toppings.remove(index);
    }

    public void getAllToppings() {
        IntStream.range(0, this.toppings.size()).forEach(i -> System.out.println(i + 1 + ". " + toppings.get(i)));
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        switch (size.toUpperCase()) {
            case "S" -> totalPrice = 8.5 + toppings.stream().mapToDouble(Topping::getPrice).sum();

            case "M" -> totalPrice = 12 + toppings.stream().mapToDouble(Topping::getPrice).sum();

            case "L" -> totalPrice = +toppings.stream().mapToDouble(Topping::getPrice).sum();

        }
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size + "Pizza\n");
        sb.append("Crust " + crustType + "\n");
        sb.append("StuffedCrust " + stuffedCrust + "\n");
        sb.append("Toppings\n");
        toppings.stream().forEach(topping -> sb.append(topping + "\n"));
        return sb.toString();
    }

}
