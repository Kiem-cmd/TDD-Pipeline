package org.example;

import java.util.List;

public class Order {
    private Long id;
    private User user;  // Thêm user vào order
    List<CartItem> items;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Order() {
    }

    public Order(User user, List<CartItem> items) {
        this.user = user;
        this.items = items;
    }

    // Getter và Setter cho id
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Getter và Setter cho user
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }




}