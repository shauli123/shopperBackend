package com.example.shopperBackend.controller;

import com.example.shopperBackend.model.Item;
import com.example.shopperBackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;
  @GetMapping
  public ResponseEntity<List<Item>> getItems() {
      try {
          return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/{query}")
  public ResponseEntity<List<Item>> searchItems(@PathVariable String query) {
      try {
          return new ResponseEntity<>(itemService.searchItems(query), HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
}
