package com.example.shopperBackend.repository;

import com.example.shopperBackend.model.FavItem;
import com.example.shopperBackend.model.Item;
import com.example.shopperBackend.repository.mapper.FavItemMapper;
import com.example.shopperBackend.repository.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String ITEMS_TABLE = "items";
    private static final String FAV_ITEMS_TABLE = "fav_items";

    public List<Item> getAllItems() {
        try {
            String sql = String.format("SELECT * FROM %s", ITEMS_TABLE);
            List<Item> items = jdbcTemplate.query(sql, new ItemMapper());
            return items;
        } catch (Exception e) {
            return null;
        }
    }

    public List<FavItem> getFavItems(String username) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE user_username = ?", FAV_ITEMS_TABLE);
            List<FavItem> items = jdbcTemplate.query(sql, new FavItemMapper(), username);
            return items;
        } catch (Exception e) {
            return null;
        }
    }

    public String reduceStock(int id) {
        try {
            String sql = String.format("UPDATE %s SET stock = stock - 1 WHERE id = ?", ITEMS_TABLE);
            jdbcTemplate.update(sql, id);
            return "Stock updated successfully";
        } catch (Exception e) {
            return "Error updating stock";
        }
    }

    public String addFavItem(String username, int id) {
        try {
            String sql = String.format("INSERT INTO %s (user_username, item_id) VALUES (?, ?)", FAV_ITEMS_TABLE);
            jdbcTemplate.update(sql, username, id);
            return "Item added to favorites successfully";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error adding item to favorites";
        }
    }

    public List<Item> searchItems(String query) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE LOWER(title) LIKE LOWER(?)", ITEMS_TABLE);
            List<Item> items = jdbcTemplate.query(sql, new ItemMapper(), "%" + query + "%");
            return items;
        } catch (Exception e) {
            return null;
        }
    }

    public String deleteFavItem(String username, int itemId) {
        try {
            String sql = String.format("DELETE FROM %s WHERE user_username = ? AND item_id = ?", FAV_ITEMS_TABLE);
            jdbcTemplate.update(sql, username, itemId);
            return "Item removed from favorites successfully";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error removing item from favorites";
        }
    }

}
