package com.sweetdeveloper.instacoffeeordermanager.models;


import java.util.ArrayList;

public class PendingOrder {
    private String address;
    private String email;
    private String name;
    private ArrayList<OrderItem> orderItems;
    private String phone;
    private String total;

    public PendingOrder() {
    }

    public PendingOrder(String address, String email, String name, ArrayList<OrderItem> orderItems, String phone, String total) {
        this.address = address;
        this.email = email;
        this.name = name;
        this.orderItems = orderItems;
        this.phone = phone;
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}



