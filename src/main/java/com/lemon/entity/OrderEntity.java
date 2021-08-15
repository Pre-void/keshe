package com.lemon.entity;

import java.util.Date;

public class OrderEntity {

    private String orderId;
    private Date orderDate;
    private String orderAddress;
    private int goodsCount;

    public OrderEntity() {
    }

    public OrderEntity(String orderId, String goodsId, Date orderDate, String orderAddress, int goodsCount) {
        this.orderId = orderId;

        this.orderDate = orderDate;
        this.orderAddress = orderAddress;
        this.goodsCount = goodsCount;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", orderAddress='" + orderAddress + '\'' +
                ", goodsCount=" + goodsCount +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }
}
