package ControllerTier;

import com.estore.api.estoreapi.controller.OrderController;
import com.estore.api.estoreapi.model.Order;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.OrderDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.estore.api.estoreapi.enums.Sizing.MENS;
import static org.junit.Assert.assertEquals;

@Tag("Controller-Tier")
public class OrderControllerTest {
    private OrderController orderController;
    private OrderDAO mockOrderDAO;

    public OrderControllerTest(){}

    @BeforeEach
    public void setUpOrderController() {
        this.mockOrderDAO = Mockito.mock(OrderDAO.class);
        this.orderController = new OrderController(this.mockOrderDAO);
    }

    @Test
    public void testCheckoutSuccess() throws IOException {
        String username = "dom";
        Shoe shoe = new Shoe(1, "Air Max 90", MENS, 9, 129.99, "Nike", "Leather", "Green");
        List<Shoe> items = new ArrayList<>();
        items.add(shoe);
        double totalCost = 0;
        for (Shoe shoeInCart : items) {
            totalCost += shoeInCart.getPrice();
        }
        Order order = new Order(username, items, totalCost);
        Mockito.when(mockOrderDAO.checkout("dom")).thenReturn(order);
        ResponseEntity<Order> response = orderController.checkout("dom");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(order, response.getBody());
    }
    @Test
    public void testCheckoutFailure() throws IOException {
        Mockito.when(mockOrderDAO.checkout("dom")).thenThrow(new IOException());
        ResponseEntity<Order> response = orderController.checkout("dom");
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetOrderSuccess() throws IOException {
        String username = "dom";
        Shoe shoe = new Shoe(1, "Air Max 90", MENS, 9, 129.99, "Nike", "Leather", "Green");
        List<Shoe> items = new ArrayList<>();
        items.add(shoe);
        double totalCost = 0;
        for (Shoe shoeInCart : items) {
            totalCost += shoeInCart.getPrice();
        }
        Order order = new Order(username, items, totalCost);
        Mockito.when(mockOrderDAO.getOrder("dom")).thenReturn(order);
        ResponseEntity<Order> response = orderController.getOrder("dom");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(order, response.getBody());

    }

    @Test
    public void testGetOrderIOException() throws IOException {
        String username = "testUser";
        Mockito.when(mockOrderDAO.getOrder(username)).thenThrow(new IOException("Mock IO exception"));

        ResponseEntity<Order> response = orderController.getOrder(username);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }
    @Test
    public void testGetAllOrdersSuccess() throws IOException {
        List<Order> orders = new ArrayList<>();

        Shoe shoe = new Shoe(1, "Air Max 90", MENS, 9, 129.99, "Nike", "Leather", "Green");
        List<Shoe> order1items = new ArrayList<>();
        order1items.add(shoe);
        double order1totalCost = 0;
        for (Shoe shoeInCart : order1items) {
            order1totalCost += shoeInCart.getPrice();
        }
        Order order1 = new Order("dom", order1items, order1totalCost);

        Shoe shoe2 = new Shoe(1, "Air Max 90", MENS, 9, 129.99, "Nike", "Leather", "Green");
        List<Shoe> order2items = new ArrayList<>();
        order2items.add(shoe);
        double order2totalCost = 0;
        for (Shoe shoeInCart : order1items) {
            order2totalCost += shoeInCart.getPrice();
        }
        Order order2 = new Order("amanda", order2items, order2totalCost);

        orders.add(order1);
        orders.add(order2);
        Mockito.when(mockOrderDAO.getAllOrders()).thenReturn(orders);
        ResponseEntity<List<Order>> response = orderController.getAllOrders();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(orders, response.getBody());
    }

    @Test
    public void testGetAllOrdersIOException() throws IOException {
        Mockito.when(mockOrderDAO.getAllOrders()).thenThrow(new IOException());

        ResponseEntity<List<Order>> response = orderController.getAllOrders();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}

