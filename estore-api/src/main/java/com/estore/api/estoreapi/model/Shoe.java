package com.estore.api.estoreapi.model;

import com.estore.api.estoreapi.enums.Sizing;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A shoe that represents a product in the estore
 */
public class Shoe {

    public static final String STRING_FORMAT = "Shoe [id=%d, style='%s', sizing=%s, size=%f, price=%.2f, brand='%s', material='%s', color='%s']";
    /**
     * Identifies this shoe within the system, must be unique
     */
    @JsonProperty("id")
    private int id;

    /**
     * The style of the shoe
     */
    @JsonProperty("style")
    private String style;

    /**
     * Chooses the sizing system this shoe size will abide by
     */
    @JsonProperty("sizing")
    private Sizing sizing;

    /**
     * The shoe size, in shoe size standard.
     * Can be whole numbers and half's of whole numbers, but nothing more
     */
    @JsonProperty("size")
    private double size;

    /**
     * The price of the show in dollars
     */
    @JsonProperty("price")
    private double price;

    /**
     * The brand of the shoe
     */
    @JsonProperty("brand")
    private String brand;

    /**
     * The material of the shoe
     */
    @JsonProperty("material")
    private String material;

    /**
     * The color of the shoe
     */
    @JsonProperty("color")
    private String color;

    public Shoe() {

    }

    public Shoe(int id, String style, Sizing sizing, int size, double price, String brand, String material, String color) {
        this.id = id;
        this.style = style;
        this.sizing = sizing;
        this.size = size;
        this.price = price;
        this.brand = brand;
        this.material = material;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, style, sizing, size, price, brand, material, color);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Sizing getSizing() {
        return sizing;
    }

    public void setSizing(Sizing sizing) {
        this.sizing = sizing;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
