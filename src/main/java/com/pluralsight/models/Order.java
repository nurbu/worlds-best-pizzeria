package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * All orders have a list of items and unique a orderId
 */
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

    /**
     * Use index given to find item
     * index - 1 done as index is user input and
     * listing of items starts at 1.
     *
     * @param index
     */
    public void removeItem(int index) {
        items.remove(index - 1);
    }

    public Item getItem(int index) {
        return items.get(index - 1);
    }

    public List<Item> getItems() {
        return items;
    }

    public void displayOrder() {
        if (items.isEmpty()) {
            System.out.println("order is empty");
            return;
        }
        IntStream.range(0, items.size()).forEach(i -> System.out.println(i + 1 + ". " + items.get(i)));
    }

    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets the orderId between 100k - 200k
     *
     * @return
     */
    private int setOrderId() {
        int min = 100000, max = 200000;
        return (int) (Math.random() * (max - min + 1)) + min;
    }


}
