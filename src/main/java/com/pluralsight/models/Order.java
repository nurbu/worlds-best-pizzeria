package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

    public void removeItem(int index) {
        items.remove(index - 1);
    }

    public Item getItem(int index) {
        return items.get(index - 1);
    }

    public List<Item> getItems() {
        return items;
    }

    public void clearOrder() {
        items.clear();
    }

    public void displayOrder() {
        if (items.isEmpty()) {
            System.out.println("order is empty");
            return;
        }
        IntStream.range(0, items.size()).forEach(i -> System.out.println(i + 1 + ". " + items.get(i)));
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
