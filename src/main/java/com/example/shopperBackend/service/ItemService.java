package com.example.shopperBackend.service;

import com.example.shopperBackend.model.FavItem;
import com.example.shopperBackend.model.Item;
import com.example.shopperBackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }

    public List<FavItem> getFavItems(String username) {
        return itemRepository.getFavItems(username);
    }

    public String addFavItem(String username, int id) {
        List<FavItem> favItems = itemRepository.getFavItems(username);
        if (favItems == null) return itemRepository.addFavItem(username, id);
        if (!favItems.stream().anyMatch(item -> item.getItemId() == id)) {
            return itemRepository.addFavItem(username, id);
        }
        return "Item already in favorites";
    }

    public String deleteFavItem(String username, int id) {
        List<FavItem> favItems = itemRepository.getFavItems(username);
        if (favItems == null) return "Item not in favorites";
        if (favItems.stream().anyMatch(item -> item.getItemId() == id)) {
            return itemRepository.deleteFavItem(username, id);
        }
        else {
            return "Item not in favorites";
        }
    }

    public List<Item> searchItems(String query) {
        return itemRepository.searchItems(query);
    }


}
