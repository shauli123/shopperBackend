package com.example.shopperBackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

    private int id;
    @JsonProperty(value = "user_username")
    private String userUsername;
    @JsonProperty(value = "order_date")
    private String orderDate;
    @JsonProperty(value = "shipping_address")
    private String shippingAddress;
    @JsonProperty(value = "total_price")
    private int totalPrice;
    private OrderStatus status;

    public Order() {
    }

    public Order(int id, String userUsername, String orderDate, String shippingAddress, int totalPrice, OrderStatus status) {
        this.id = id;
        this.userUsername = userUsername;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userUsername='" + userUsername + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
