package com.example.shopperBackend.repository.mapper;

import com.example.shopperBackend.model.FavItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavItemMapper implements RowMapper<FavItem> {

    @Override
    public FavItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        FavItem favItem = new FavItem();
        favItem.setItemId(rs.getInt("item_id"));
        favItem.setUserUsername(rs.getString("user_username"));
        return favItem;
    }
}
