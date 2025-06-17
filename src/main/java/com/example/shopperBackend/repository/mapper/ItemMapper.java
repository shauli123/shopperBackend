package com.example.shopperBackend.repository.mapper;

import com.example.shopperBackend.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setTitle(rs.getString("title"));
        item.setImg(rs.getString("img"));
        item.setPrice(rs.getInt("price"));
        item.setStock(rs.getInt("stock"));
        return item;
    }
}
