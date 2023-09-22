package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * A class that represents a user's shopping cart
 */
public class Cart {
    @JsonProperty("username")
    private String username;

    /**
     * All items in cart
     */
    @JsonProperty("items")
    private ArrayList<Shoe> items;

    public ArrayList<Shoe> getItems() {
        return items;
    }

    public void setItems(ArrayList<Shoe> items) {
        this.items = items;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
