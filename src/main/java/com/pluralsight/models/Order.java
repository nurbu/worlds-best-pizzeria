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

    public void displayOrder() {
        for (int i = 1; i < items.size(); i++) {
            System.out.println(i + ". " + items.get(i));
        }
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public int getOrderId() {
        return orderId;
    }

    private int setOrderId() {
        int min = 100000, max = 200000;
        return (int) (Math.random() * (max - min + 1)) + min;
    }


}
