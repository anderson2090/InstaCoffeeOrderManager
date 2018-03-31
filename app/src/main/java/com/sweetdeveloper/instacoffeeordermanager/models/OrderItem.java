package com.sweetdeveloper.instacoffeeordermanager.models;


import android.os.Parcel;
import android.os.Parcelable;

public class OrderItem implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemName);
        dest.writeString(this.price);
        dest.writeString(this.quantity);
    }

    protected OrderItem(Parcel in) {
        this.itemName = in.readString();
        this.price = in.readString();
        this.quantity = in.readString();
    }

    public static final Parcelable.Creator<OrderItem> CREATOR = new Parcelable.Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel source) {
            return new OrderItem(source);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };
}
