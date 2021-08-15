package com.lemon.entity;

public class CartEntity {
    private String goods_id;
    private String user;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public CartEntity() {

    }

    public CartEntity(String goods_id, String user) {
        this.goods_id = goods_id;
        this.user = user;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "goods_id='" + goods_id + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
}
