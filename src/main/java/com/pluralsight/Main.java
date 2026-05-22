package com.pluralsight;

import com.pluralsight.models.Order;

public class Main {

    public static void main(String[] args) {
        Order order = new Order();
        System.out.println("Order ID: " + order.getOrderId());
        UserInterface userInterface = new UserInterface();
        userInterface.Display();

    }

}
