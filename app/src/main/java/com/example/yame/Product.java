package com.example.yame;

import java.util.List;

public class Product {
    private List<Integer> img;
    private String name;
    private long id;
    private int unitPrice;
    private int quantity;
    private boolean isBuy;
    private String productDetail;
    private String instruction;
    private List<Store> storeList;

    public Product(List<Integer> img, String name, long id, int unitPrice, int quantity, String productDetail, String instruction, List<Store> storeList) {
        this.img = img;
        this.name = name;
        this.id = id;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.isBuy = false;
        this.productDetail = productDetail;
        this.instruction = instruction;
        this.storeList = storeList;
    }

    public List<Integer> getImg() {
        return img;
    }

    public int getSizeOfListImg() {
        return img.size();
    }

    public void setImg(List<Integer> img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String toStringID() {
        return "REF. " + id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }
}

