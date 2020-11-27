package com.example.yame.network.Order;

import com.example.yame.OrderDB;
import com.example.yame.network.Response;

import java.util.List;

public class GetOrderResponse extends Response {
    public List<OrderDB> data;
}
