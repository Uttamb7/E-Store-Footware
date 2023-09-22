package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Order;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.utils.FlatFileOps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class OrderFileDAO implements OrderDAO {
    private static final Logger LOG = Logger.getLogger(OrderFileDAO.class.getName());
    private final ObjectMapper orderObjectMapper;
    private final String filename;
    Map<String, Order> orderMap = new HashMap<>();
    private final CartDAO cartDAO;

    public OrderFileDAO(@Value("${dao.orders}") String filename,
                        ObjectMapper orderObjectMapper, CartDAO cartDAO) throws IOException {
        this.cartDAO = cartDAO;
        this.filename = filename;
        this.orderObjectMapper = orderObjectMapper;
        load();
    }

    @Override
    public Order getOrder(String username) throws IOException {
        return orderMap.get(username);
    }

    @Override
    public List<Order> getAllOrders() throws IOException {
        load();
        return List.copyOf(orderMap.values());
    }

    @Override
    public Order checkout(String username) throws IOException {
        double totalCost = 0;
        Cart cart = cartDAO.getCart(username);
        for (Shoe shoe : cart.getItems()) {
            totalCost += shoe.getPrice();
        }
        Order order = new Order(username, new ArrayList<>(cart.getItems()), totalCost);
        orderMap.put(order.getUsername(), order);
        saveOrder(order);
        cartDAO.clearCart(username);
        return order;
    }

    private boolean load() throws IOException {
        // Deserializes the JSON objects from the file into an array of shoes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        FlatFileOps.ensureDataFileExists(filename);
        Order[] orders = orderObjectMapper.readValue(new File(filename), Order[].class);
        for (Order order : orders) {
            orderMap.put(order.getUsername(), order);
        }
        return true;
    }

    private void saveOrder(Order order) throws IOException {
        orderObjectMapper.writeValue(new File(filename), orderMap.values().toArray());
    }
}
