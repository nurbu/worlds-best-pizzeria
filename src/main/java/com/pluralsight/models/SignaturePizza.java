package com.pluralsight.models;

import com.pluralsight.models.toppings.Cheese;
import com.pluralsight.models.toppings.RegularTopping;
import com.pluralsight.models.toppings.Sauce;

import java.util.HashMap;
import java.util.Map;

public class SignaturePizza {

    public static Map<String, Pizza> loadSignaturePizzas() {
        Map<String, Pizza> signaturePizzas = new HashMap<String, Pizza>();
        Pizza Veggie = new Pizza(8, "Regular", false);
        Pizza Margherita = new Pizza(12, "Regular", true);

        // Add the toppings
        Margherita.addTopping(new Cheese("Mozzarella", false, 12));
        Margherita.addTopping(new RegularTopping("Tomatoes"));
        Margherita.addTopping(new RegularTopping("Basil"));
        Margherita.addTopping(new Sauce("Marinara"));
        Margherita.addTopping(new Sauce("Olive Oil"));

        Veggie.addTopping(new RegularTopping("Bell Pepper"));
        Veggie.addTopping(new RegularTopping("Spinach"));
        Veggie.addTopping(new RegularTopping("Olives"));
        Veggie.addTopping(new RegularTopping("Onions"));
        Veggie.addTopping(new Sauce("Marinara"));
        Veggie.addTopping(new Cheese("Mozzarella", false, 8));

        signaturePizzas.put("Margherita", Margherita);
        signaturePizzas.put("Veggie", Veggie);

        return signaturePizzas;
    }
}

