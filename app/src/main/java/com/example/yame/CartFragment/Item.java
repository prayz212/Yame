package com.example.yame.CartFragment;

import com.example.yame.R;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private int img;
    private String type;
    private String ID;
    private int unitPrice;
    private int totalPrice;
    private int quantity;

    public Item(int img, String type, String ID, int price, int quantity) {
        this.img = img;
        this.type = type;
        this.ID = ID;
        this.unitPrice = price;
        this.quantity = quantity;
        this.totalPrice = unitPrice;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return unitPrice;
    }

    public void setPrice(int price) {
        this.unitPrice = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
