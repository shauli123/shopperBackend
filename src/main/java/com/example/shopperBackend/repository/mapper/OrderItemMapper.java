package com.example.shopperBackend.repository.mapper;

import com.example.shopperBackend.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setItemId(rs.getInt("item_id"));
        orderItem.setAmount(rs.getInt("amount"));
        return orderItem;
    }
}
