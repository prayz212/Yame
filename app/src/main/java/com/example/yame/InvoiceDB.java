package com.example.yame;

public class InvoiceDB {
    private long id, id_cart;
    private int total, status;
    private String name, phone, address, note, date;

    public InvoiceDB(long id, long id_cart, int total, int status, String name, String phone, String address, String note, String date) {
        this.id = id;
        this.id_cart = id_cart;
        this.total = total;
        this.status = status;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_cart() {
        return id_cart;
    }

    public void setId_cart(long id_cart) {
        this.id_cart = id_cart;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "InvoiceDB{" +
                "id=" + id +
                ", id_cart=" + id_cart +
                ", total=" + total +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
