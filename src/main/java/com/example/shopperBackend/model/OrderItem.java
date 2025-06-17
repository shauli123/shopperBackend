package com.example.shopperBackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItem {
    @JsonProperty(value = "order_id")
    private int orderId;
    @JsonProperty(value = "item_id")
    private int itemId;
    private int amount;

    public OrderItem() {
    }

    public OrderItem(int orderId, int itemId, int amount) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", itemId=" + itemId +
                ", amount=" + amount +
                '}';
    }
}
