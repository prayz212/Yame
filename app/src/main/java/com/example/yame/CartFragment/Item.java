package com.example.yame.CartFragment;

import com.example.yame.R;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private int img;
    private String type;
    private String ID;
    private float price;

    public Item(int img, String type, String ID, float price) {
        this.img = img;
        this.type = type;
        this.ID = ID;
        this.price = price;
    }

    public static List<Item> generate(int num) {
        List<Item> items = new ArrayList<>();
        if(num < 1) {
            return items;
        }
        for (int i = 1; i <= num; i++) {
            items.add(new Item(R.drawable.demo_img_home_fragment, "T-shirt " + i, "ID T-shirt " + i, 100000));
        }
        return items;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
