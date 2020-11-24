package com.example.yame;

import java.util.List;

public class ProductDB {
    private long id;
    private int price;
    private String url;
    private String name;

    public ProductDB(long id, int price, String name, String imgs) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.url = imgs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgs() {
        return url;
    }

    public void setImgs(String imgs) {
        this.url = imgs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ProductDB{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", imgs=" + url +
                '}';
    }
}
