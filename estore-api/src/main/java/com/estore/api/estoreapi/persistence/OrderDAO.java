package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderDAO {
    Order getOrder(String username) throws IOException;

    List<Order> getAllOrders() throws IOException;

    Order checkout(String username) throws IOException;

}