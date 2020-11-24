package com.example.yame;

import com.google.gson.annotations.SerializedName;

public class CartProductDB extends ProductDB {

    private int quanlity;
    private int totalPrice;

    public CartProductDB(long id, int price, String name, String imgs, int quan) {
        super(id, price, name, imgs);
        this.quanlity = quan;
        this.totalPrice = 10;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartProductDB{" +
                "quanlity=" + quanlity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
