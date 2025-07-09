package com.example.shopperBackend.controller;

import com.example.shopperBackend.model.Item;
import com.example.shopperBackend.model.Order;
import com.example.shopperBackend.model.OrderData;
import com.example.shopperBackend.service.OrderService;
import com.example.shopperBackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtUtil jwtUtil;

    @PreAuthorize("authenticated")
    @PostMapping
    public ResponseEntity<Order> addItemsToOrderAndCreateOrder(@RequestHeader(value = "Authorization") String token, @RequestBody Item item) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            System.out.println(item + " " + username);
            Order order = orderService.addItemsToOrder(item, username);
            if (order == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("authenticated")
    @DeleteMapping
    public ResponseEntity<String> deleteOrderItem(@RequestHeader(value = "Authorization") String token, @RequestBody Item item) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            String result = orderService.deleteOrderItem(item, username);
            if (result.contains("successfully")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("authenticated")
    @PutMapping("/close-order")
    public ResponseEntity<String> closeOrder(@RequestHeader(value = "Authorization") String token) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            String result = orderService.closeOrder(username);
            if (result.contains("successfully")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("authenticated")
    @GetMapping("/order-data")
    public ResponseEntity<OrderData> getOrderDataOfTempOrder(@RequestHeader(value = "Authorization") String token) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            OrderData orderData = orderService.getOrderData(username);
            return new ResponseEntity<>(orderData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("authenticated")
    @GetMapping
    public ResponseEntity<List<OrderData>> getOrders(@RequestHeader(value = "Authorization") String token) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            List<OrderData> orders = orderService.getOrders(username);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}