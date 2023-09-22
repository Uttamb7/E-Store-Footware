package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.utils.FlatFileOps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class CartFileDAO implements CartDAO {
    private static final Logger LOG = Logger.getLogger(CartFileDAO.class.getName());
    private final ObjectMapper objectMapper;
    private final String filename;
    Map<String, Cart> cartMap = new HashMap<>();

    public CartFileDAO(@Value("${dao.carts}") String filename,
                       ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    @Override
    public void addToCart(String username, Shoe shoe) throws IOException {
        Cart cart = standardCart(username);
        cart.getItems().add(shoe);
        save();
    }

    @Override
    public void removeFromCart(String username, int shoeId) throws IOException {
        Cart cart = standardCart(username);
        cart.getItems().removeIf(shoe -> shoe.getId() == shoeId);
        save();
    }

    @Override
    public Cart getCart(String username) throws IOException {
        return standardCart(username);
    }

    @Override
    public void clearCart(String username) throws IOException {
        Cart cart = getCart(username);
        cart.getItems().clear();
        save();
    }

    private Cart standardCart(String username) throws IOException {
        Cart cart = cartMap.get(username);

        if (cart == null) {
            cart = new Cart();
            cart.setUsername(username);
            cart.setItems(new ArrayList<>());
            cartMap.put(username, cart);
        }

        save();
        return cart;
    }

    private boolean load() throws IOException {
        // Deserializes the JSON objects from the file into an array of shoes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file

        FlatFileOps.ensureDataFileExists(filename);
        Cart[] carts = objectMapper.readValue(new File(filename), Cart[].class);

        for (Cart cart : carts) {
            cartMap.put(cart.getUsername(), cart);
        }

        return true;
    }

    private boolean save() throws IOException {
        objectMapper.writeValue(new File(filename), cartMap.values().toArray());
        return true;
    }

}