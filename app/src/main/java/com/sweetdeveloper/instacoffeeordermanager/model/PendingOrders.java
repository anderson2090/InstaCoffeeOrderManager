package com.sweetdeveloper.instacoffeeordermanager.model;


public class PendingOrders {

    private String userName;
    private String userEmail;
    private String phone;
    private String address;
    private String time;

    public PendingOrders() {
    }

    public PendingOrders(String userName, String userEmail, String phone, String address, String time) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.phone = phone;
        this.address = address;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
