package PersistenceTier;

import com.estore.api.estoreapi.enums.Sizing;
import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.CartFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Persistence-tier")
public class CartFileDAOTest {
    ObjectMapper mockObjectMapper;

    Cart mockCart;

    Shoe mockShoe = new Shoe(1, "Yeezy", Sizing.MENS, 12, 229.99, "Adidas", "Mesh", "Grey");
    Shoe mockShoe2 = new Shoe(2, "Yeezy", Sizing.MENS, 12, 229.99, "Adidas", "Mesh", "Grey");

    CartFileDAO mockCartDAO;

    public CartFileDAOTest() {
    }

    @BeforeEach
    public void setupCartFileDAO() throws IOException {
        this.mockCart = new Cart();

        Cart[] mockCartList = new Cart[]{};

        this.mockObjectMapper = mock(ObjectMapper.class);
        when(mockObjectMapper.readValue(any(File.class), eq(Cart[].class))).thenReturn(mockCartList);
        this.mockCartDAO = new CartFileDAO("data/cart-testing.txt", mockObjectMapper);
    }

    @Test
    public void testAddToCart() throws IOException {
        setupCartFileDAO();

        mockCartDAO.addToCart("dom", mockShoe);
        Cart cart = mockCartDAO.getCart("dom");
        ArrayList<Shoe> expectedItems = new ArrayList<>();

        expectedItems.add(mockShoe);

        Assertions.assertEquals(expectedItems, cart.getItems());
    }


    @Test
    public void testSetCart() throws Exception {
        setupCartFileDAO();

        String username = "user1";
        ArrayList<Shoe> items = new ArrayList<>();
        items.add(mockShoe);

        Cart result = new Cart();
        result.setUsername(username);
        result.setItems(items);

        Assertions.assertEquals(result.getUsername(), username);
        Assertions.assertEquals(result.getItems().size(), 1);
        Assertions.assertEquals(result.getItems().get(0), mockShoe);
    }

    @Test
    public void testRemoveFromCart() throws IOException {
        setupCartFileDAO();
        ArrayList<Shoe> shoeList = new ArrayList<>();
        shoeList.add(mockShoe);
        shoeList.add(mockShoe2);
        mockCart.setItems(shoeList);
        mockCartDAO.removeFromCart("dom", 1);
        mockCartDAO.removeFromCart("dom", 2);
        Cart cart = mockCartDAO.getCart("dom");
        Assertions.assertEquals(Collections.emptyList(), cart.getItems());
    }

    @Test
    public void testClearCart() throws IOException {
        setupCartFileDAO();
        ArrayList<Shoe> shoeList = new ArrayList<>();
        shoeList.add(mockShoe);
        shoeList.add(mockShoe2);
        mockCart.setItems(shoeList);
        mockCartDAO.clearCart("dom");
        Cart cart = mockCartDAO.getCart("dom");
        Assertions.assertEquals(Collections.emptyList(), cart.getItems());
    }


}

