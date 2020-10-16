package com.example.yame.CartFragment;

import com.example.yame.Product;
import com.example.yame.Store;

import java.util.List;

public class BuyProduct extends Product {

    private int totalPrice;

    public BuyProduct(List<Integer> img, String name, long id, int unitPrice, int quantity, String productDetail, String instruction, List<Store> storeList) {
        super(img, name, id, unitPrice, quantity, productDetail, instruction, storeList);
        this.totalPrice = unitPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
