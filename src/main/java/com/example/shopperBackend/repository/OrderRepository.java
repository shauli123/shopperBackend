package com.example.shopperBackend.repository;

import com.example.shopperBackend.model.*;
import com.example.shopperBackend.repository.mapper.ItemMapper;
import com.example.shopperBackend.repository.mapper.OrderItemMapper;
import com.example.shopperBackend.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;
    final String ORDERS_TABLE = "orders";
    final String ORDER_ITEMS_TABLE = "order_items";
    final String ITEMS_TABLE = "items";
//    public String createOrder(Order order, String username) {
//        try{
//            String sql = String.format("INSERT INTO %s (user_username, order_date, shipping_address, total_price, status) VALUES (?, ?, ?, ?, ?)", ORDERS_TABLE);
//            jdbcTemplate.update(sql, username, order.getOrderDate(), order.getShippingAddress(), order.getTotalPrice(), order.getStatus());
//            return "The order has been created successfully";
//        } catch (Exception e) {
//            return "Error creating order";
//        }
//    }

    public Order addItemsToOrder(Item item, String username) {
        try {
            if (item.getStock() <= 0) {
                return null;
            }
            String sqlCheck = String.format("SELECT * FROM %s WHERE id = ?", ITEMS_TABLE);
            Item matchedItem = jdbcTemplate.queryForObject(sqlCheck, new ItemMapper(), item.getId());
            if (matchedItem.getStock() <= 0) {
                return null;
            }
            String sql = String.format("SELECT * FROM %s WHERE user_username = ? AND status = 'TEMP'", ORDERS_TABLE);
            List<Order> tempOrders = jdbcTemplate.query(sql, new OrderMapper(), username);
            Order order;
            if (tempOrders.isEmpty()) {
                String insertSql = String.format("INSERT INTO %s (user_username, order_date, shipping_address, total_price, status) VALUES (?, ?, ?, 0, 'TEMP')", ORDERS_TABLE);
                CustomUser user = userRepository.findUserByUsername(username);
                jdbcTemplate.update(insertSql, username, new Date(), user.getAddress());
                order = jdbcTemplate.queryForObject(sql, new OrderMapper(), username);
            } else {
                order = tempOrders.get(0);
            }
            sql = String.format("SELECT * FROM %s WHERE order_id = ?", ORDER_ITEMS_TABLE);
            List<OrderItem> orderItems = jdbcTemplate.query(sql, new OrderItemMapper(), order.getId());
            boolean isItemFound = false;
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getItemId() == item.getId()) {
                    if (orderItem.getAmount() + 1 > item.getStock()) {
                        return order;
                    }
                    isItemFound = true;
                    break;
                }
            }
            if (isItemFound) {
                sql = String.format("UPDATE %s SET amount = amount + 1 WHERE order_id = ? AND item_id = ?", ORDER_ITEMS_TABLE);
                jdbcTemplate.update(sql, order.getId(), item.getId());
            } else {
                sql = String.format("INSERT INTO %s (order_id, item_id, amount) VALUES (?, ?, ?)", ORDER_ITEMS_TABLE);
                jdbcTemplate.update(sql, order.getId(), item.getId(), 1);
            }
            sql = String.format("UPDATE %s SET total_price = total_price + ? WHERE id = ?", ORDERS_TABLE);
            jdbcTemplate.update(sql, item.getPrice(), order.getId());
            sql = String.format("SELECT * FROM %s WHERE user_username = ? AND status = 'TEMP'", ORDERS_TABLE);
            order = jdbcTemplate.queryForObject(sql, new OrderMapper(), username);
            return order;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public OrderData getOrderData(String username) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE user_username = ? AND status = 'TEMP'", ORDERS_TABLE);
            Order order = jdbcTemplate.queryForObject(sql, new OrderMapper(), username);
            sql = String.format("SELECT * FROM %s WHERE order_id = ?", ORDER_ITEMS_TABLE);
            List<OrderItem> orderItems = jdbcTemplate.query(sql, new OrderItemMapper(), order.getId());
            OrderData orderData = new OrderData(order, orderItems);
            return orderData;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

//    public List<Order> getOrders(String username) {
//        try {
//            String sql = String.format("SELECT * FROM %s WHERE user_username = ?", ORDERS_TABLE);
//            List<Order> orders = jdbcTemplate.query(sql, new OrderMapper(), username);
//            return orders;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
    public List<OrderData> getOrders(String username) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE user_username = ?", ORDERS_TABLE);
            List<Order> orders = jdbcTemplate.query(sql, new OrderMapper(), username);

            List<OrderData> orderDataList = new ArrayList<>();
            String itemSql = String.format("SELECT * FROM %s WHERE order_id = ?", ORDER_ITEMS_TABLE);

            for (Order order : orders) {
                List<OrderItem> orderItems = jdbcTemplate.query(itemSql, new OrderItemMapper(), order.getId());
                orderDataList.add(new OrderData(order, orderItems));
            }

            return orderDataList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String deleteOrderItem(int itemId, String username) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE user_username = ? AND status = 'TEMP'", ORDERS_TABLE);
            Order order = jdbcTemplate.queryForObject(sql, new OrderMapper(), username);

            sql = String.format("SELECT * FROM %s WHERE order_id = ? AND item_id = ?", ORDER_ITEMS_TABLE);
            OrderItem orderItem = jdbcTemplate.queryForObject(sql, new OrderItemMapper(), order.getId(), itemId);

            if (orderItem.getAmount() <= 1) {
                sql = String.format("DELETE FROM %s WHERE order_id = ? AND item_id = ?", ORDER_ITEMS_TABLE);
                jdbcTemplate.update(sql, order.getId(), itemId);
            } else {
                sql = String.format("UPDATE %s SET amount = amount - 1 WHERE order_id = ? AND item_id = ?", ORDER_ITEMS_TABLE);
                jdbcTemplate.update(sql, order.getId(), itemId);
            }
            sql = String.format("SELECT * FROM %s WHERE order_id = ?", ORDER_ITEMS_TABLE);
            List<OrderItem> orderItems = jdbcTemplate.query(sql, new OrderItemMapper(), order.getId());
            if (orderItems.isEmpty()) {
                sql = String.format("DELETE FROM %s WHERE id = ?", ORDERS_TABLE);
                jdbcTemplate.update(sql, order.getId());
                return "Item reduced by 1 and Order deleted successfully";
            }

//            sql = String.format("UPDATE %s SET total_price = total_price - ? WHERE id = ?", ORDERS_TABLE);
//            jdbcTemplate.update(sql, item.getPrice(), order.getId());

            return "Order item reduced by 1 successfully";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error deleting order item";
        }
    }

    public String closeOrder(String username) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE user_username = ? AND status = 'TEMP'", ORDERS_TABLE);
            Order order = jdbcTemplate.queryForObject(sql, new OrderMapper(), username);

            sql = String.format("SELECT * FROM %s WHERE order_id = ?", ORDER_ITEMS_TABLE);
            List<OrderItem> orderItems = jdbcTemplate.query(sql, new OrderItemMapper(), order.getId());

            List<Item> allItems = itemRepository.getAllItems();

            for (OrderItem orderItem : orderItems) {
                Item matchedItem = allItems.stream()
                        .filter(i -> i.getId() == orderItem.getItemId())
                        .findFirst()
                        .orElse(null);

                if (matchedItem == null || matchedItem.getStock() < orderItem.getAmount()) {
                    return "There is not enough stock for some of the items in the order";
                }
            }

            for (OrderItem orderItem : orderItems) {
                itemRepository.reduceStock(orderItem.getItemId(), orderItem.getAmount());
            }

            sql = String.format("UPDATE %s SET status = 'CLOSED' WHERE id = ?", ORDERS_TABLE);
            jdbcTemplate.update(sql, order.getId());

            return "Order has been closed successfully";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error creating order";
        }
    }

    public String deleteAllOrdersAndOrderItems(String username) {
        try {
            String sql = String.format("DELETE FROM %s WHERE order_id IN (SELECT id FROM %s WHERE user_username = ?)", ORDER_ITEMS_TABLE, ORDERS_TABLE);
            jdbcTemplate.update(sql, username);
            sql = String.format("DELETE FROM %s WHERE user_username = ?", ORDERS_TABLE);
            jdbcTemplate.update(sql, username);
            return "All orders and order items deleted successfully";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error deleting orders and order items";
        }
    }
}
