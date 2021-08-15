package com.lemon.entity;


public class MygoodsEntity {

    private int id;
    private String img;
    private String price;
    private String detail;
    private String kind;
    private double count;

    public MygoodsEntity(int id, String img, String price, String detail, String kind, double count) {
        this.id = id;
        this.img = img;
        this.price = price;
        this.detail = detail;
        this.kind = kind;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public MygoodsEntity() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "MygoodsEntity{" +
                "img='" + img + '\'' +
                ", price='" + price + '\'' +
                ", detail='" + detail + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
