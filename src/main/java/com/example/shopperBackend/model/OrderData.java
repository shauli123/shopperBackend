package com.example.shopperBackend.model;

import java.util.List;

public class OrderData {
    private Order order;
    private List<OrderItem> items;

    public OrderData() {
    }

    public OrderData(Order order, List<OrderItem> items) {
        this.order = order;
        this.items = items;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "order=" + order +
                ", items=" + items +
                '}';
    }
}
