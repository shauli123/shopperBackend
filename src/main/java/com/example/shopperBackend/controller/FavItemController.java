package com.example.shopperBackend.controller;

import com.example.shopperBackend.model.FavItem;
import com.example.shopperBackend.model.Item;
import com.example.shopperBackend.service.ItemService;
import com.example.shopperBackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/fav-items")
public class FavItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private JwtUtil jwtUtil;
    @PreAuthorize("authenticated")
    @GetMapping()
    public ResponseEntity<List<FavItem>> getFavItems(@RequestHeader(value = "Authorization") String token) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            List<FavItem> favItems = itemService.getFavItems(username);
            return new ResponseEntity<>(favItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("authenticated")
    @PostMapping()
    public ResponseEntity<String> addFavItem(@RequestHeader(value = "Authorization") String token, @RequestParam int id) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            String result = itemService.addFavItem(username, id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("authenticated")
    @DeleteMapping()
    public ResponseEntity<String> deleteFavItem(@RequestHeader(value = "Authorization") String token, @RequestParam int id) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            String result = itemService.deleteFavItem(username, id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
