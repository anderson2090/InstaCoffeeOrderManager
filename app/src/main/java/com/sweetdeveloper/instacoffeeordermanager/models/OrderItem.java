package com.sweetdeveloper.instacoffeeordermanager.models;



public class OrderItem {

    private String itemName;
    private String price;
    private String quantity;

    public OrderItem() {
    }

    public OrderItem(String itemName, String price, String quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
