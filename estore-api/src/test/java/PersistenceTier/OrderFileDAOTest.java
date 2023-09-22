package PersistenceTier;

import com.estore.api.estoreapi.enums.Sizing;
import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Order;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.CartDAO;
import com.estore.api.estoreapi.persistence.CartFileDAO;
import com.estore.api.estoreapi.persistence.OrderFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("Persistence-tier")
public class OrderFileDAOTest {
    private OrderFileDAO mockOrderDAO;
    private Order mockOrder;
    Shoe mockShoe1 = new Shoe(1, "Yeezy", Sizing.MENS, 12, 229.99, "Adidas", "Mesh", "Grey");
    Shoe mockShoe2 = new Shoe(2, "AJ1 Low", Sizing.MENS, 11, 199.99, "Jordan", "Leather", "Olive");
    ObjectMapper mockObjectMapper;

    public OrderFileDAOTest() {
    }

    @BeforeEach
    public void setup() throws IOException {
        ArrayList<Shoe> mockShoes = new ArrayList<>();
        mockShoes.add(mockShoe1);
        mockShoes.add(mockShoe2);

        Cart mockCart = new Cart();
        mockCart.setUsername("john");
        mockCart.setItems(mockShoes);

        Cart[] mockCarts = new Cart[]{mockCart};
        ObjectMapper mockCartObjectMapper = mock(ObjectMapper.class);
        when(mockCartObjectMapper.readValue(any(File.class), eq(Cart[].class))).thenReturn(mockCarts);
        CartDAO mockCartDAO = new CartFileDAO("data/testing_cart.txt", mockCartObjectMapper);

        this.mockOrder = new Order("john", mockShoes, 429.98);

        Order[] mockOrderList = new Order[]{mockOrder};
        this.mockObjectMapper = mock(ObjectMapper.class);
        when(mockObjectMapper.readValue(any(File.class), eq(Order[].class))).thenReturn(mockOrderList);
        this.mockOrderDAO = new OrderFileDAO("data/testing.txt", mockObjectMapper, mockCartDAO);
    }

    @Test
    public void testGetOrderSuccess() throws IOException {
        setup();
        Order order = mockOrderDAO.getOrder("john");
        Assertions.assertEquals("john", order.getUsername());
    }

    @Test
    public void testGetOrderFailure() throws IOException {
        setup();
        Order order = mockOrderDAO.getOrder("jane");
        assertNull(order);
    }

    @Test
    public void testGetAllOrders() throws IOException {
        setup();
        List<Order> orders = mockOrderDAO.getAllOrders();
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals("john", orders.get(0).getUsername());
    }

    @Test
    public void testCheckoutSuccess() throws IOException {
        setup();
        Order order = mockOrderDAO.checkout("john");
        assertNotNull(order);
        assertEquals("john", order.getUsername());
        assertEquals(2, order.getItems().size());
        assertEquals(429.98, order.getTotalCost());
    }
}

