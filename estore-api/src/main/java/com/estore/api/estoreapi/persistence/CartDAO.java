package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Shoe;

import java.io.IOException;


public interface CartDAO {

    void addToCart(String username, Shoe shoe) throws IOException;

    void removeFromCart(String username, int shoeId) throws IOException;

    Cart getCart(String username) throws IOException;

    void clearCart(String username) throws IOException;

}
