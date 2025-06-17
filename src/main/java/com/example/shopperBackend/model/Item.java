package com.example.shopperBackend.model;

public class Item {
    private int id;
    private String title;
    private String img;
    private int price;
    private int stock;

    public Item() {
    }

    public Item(int id, String title, String img, int price, int stock) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
