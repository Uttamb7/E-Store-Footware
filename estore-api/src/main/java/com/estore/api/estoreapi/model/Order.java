package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Order {

    @JsonProperty("username")
    private String username;

    @JsonProperty("items")
    private List<Shoe> items;

    @JsonProperty("totalCost")
    private double totalCost;

    public Order(String username, List<Shoe> items, double totalCost) {
        this.username = username;
        this.items = items;
        this.totalCost = totalCost;
    }

    public Order(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public List<Shoe> getItems() {
        return this.items;
    }

    public double getTotalCost() {
        return this.totalCost;
    }
}
