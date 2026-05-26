package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Item> items;
    private final int orderId;

    public Order() {
        this.items = new ArrayList<>();
        this.orderId = setOrderId();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void clearOrder() {
        items.clear();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    private int setOrderId() {
        int min = 100000, max = 200000;
        return (int) (Math.random() * (max - min + 1)) + min;
    }


}
