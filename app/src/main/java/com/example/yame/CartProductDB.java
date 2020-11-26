package com.example.yame;

import com.google.gson.annotations.SerializedName;

public class CartProductDB extends ProductDB {

    private long id_cart;
    private int quanlity;
    private int totalPrice;

    public CartProductDB(long id, int price, String name, String imgs, int quan, long cart) {
        super(id, price, name, imgs);
        this.id_cart = cart;
        this.quanlity = quan;
        this.totalPrice = 0;
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

    public long getId_cart() {
        return id_cart;
    }

    public void setId_cart(long id_cart) {
        this.id_cart = id_cart;
    }

    @Override
    public String toString() {
        return "CartProductDB{" +
                "id_cart=" + id_cart +
                ", quanlity=" + quanlity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
