package com.example.yame;

public class ProductDetailDB extends ProductDB {

    private String detail;
    private String instruction;

    public ProductDetailDB(long id, int price, String name, String url,  String detail, String instruction) {
        super(id, price, name, url);
        this.detail = detail;
        this.instruction = instruction;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "ProductDetailDB{" +
                "id=" + super.getId() +
                ", price=" + super.getPrice() +
                ", name='" + super.getName() + '\'' +
                ", imgs=" + super.getUrl() +
                "detail='" + detail + '\'' +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
