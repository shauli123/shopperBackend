package com.example.shopperBackend.service;

import com.example.shopperBackend.model.Item;
import com.example.shopperBackend.model.Order;
import com.example.shopperBackend.model.OrderData;
import com.example.shopperBackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
   @Autowired
   private OrderRepository orderRepository;

   public Order addItemsToOrder(Item item, String username) {
       System.out.println(item + " " + username);
       if(item == null) {
           return null;
       };
       if (username == null) {
           return null;
       }
       return orderRepository.addItemsToOrder(item, username);
   }

   public String deleteOrderItem(int itemId, String username) {
//       if(itemId == null) return "Send an item in order to delete it from the order";
       return orderRepository.deleteOrderItem(itemId, username);
   }

   public String closeOrder(String username) {
       return orderRepository.closeOrder(username);
   }

   public OrderData getOrderData(String username) {
       return orderRepository.getOrderData(username);
   }

   public List<OrderData> getOrders(String username) {
       return orderRepository.getOrders(username);
   }

}
