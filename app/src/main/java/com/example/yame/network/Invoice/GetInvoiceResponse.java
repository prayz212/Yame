package com.example.yame.network.Invoice;

import com.example.yame.InvoiceDB;
import com.example.yame.network.Response;

import java.util.List;

public class GetInvoiceResponse extends Response {
    public List<InvoiceDB> data;
}
