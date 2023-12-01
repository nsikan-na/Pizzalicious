package org.example.util;

import java.util.ArrayList;

public class CartItem {
    static int counter = 1;
    private int id;
    private String item;
    private ArrayList<String> itemDetails;
    private double pricePerItem;
    private int quantity;

    public CartItem(String item, ArrayList<String> itemDetails, double pricePerItem, int quantity) {
        this.id = counter;
        counter++;
        this.item = item;
        this.itemDetails = itemDetails;
        this.pricePerItem = pricePerItem;
        this.quantity = quantity;
    }

    public ArrayList<String> getItemDetails() {
        return itemDetails;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public int getQuantity() {
        return quantity;
    }


    public void decreaseQuantity() {
        if (this.quantity < 0) {
            return;
        }
        this.quantity--;
    }

    public void increaseQuantity() {

        this.quantity++;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }
}
