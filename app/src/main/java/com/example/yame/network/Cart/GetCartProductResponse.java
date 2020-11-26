package com.example.yame.network.Cart;

import com.example.yame.CartProductDB;
import com.example.yame.network.Response;

import java.util.List;

public class GetCartProductResponse extends Response {
    public List<CartProductDB> data;
}
