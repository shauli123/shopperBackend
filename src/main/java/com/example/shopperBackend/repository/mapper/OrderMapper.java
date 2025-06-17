package com.example.shopperBackend.repository.mapper;

import com.example.shopperBackend.model.Order;
import com.example.shopperBackend.model.OrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserUsername(rs.getString("user_username"));
        order.setOrderDate(rs.getString("order_date"));
        order.setShippingAddress(rs.getString("shipping_address"));
        order.setTotalPrice(rs.getInt("total_price"));
        order.setStatus(OrderStatus.valueOf(rs.getString("status")));
        return order;
    }
}
