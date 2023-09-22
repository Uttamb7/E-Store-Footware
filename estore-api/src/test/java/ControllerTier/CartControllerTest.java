package ControllerTier;

import com.estore.api.estoreapi.controller.CartController;
import com.estore.api.estoreapi.enums.Sizing;
import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.CartDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import static org.mockito.Mockito.*;

@Tag("Controller-Tier")
public class CartControllerTest {

    private CartController cartController;
    private CartDAO mockCartDAO;

    public CartControllerTest() {
    }

    @BeforeEach
    public void setupCartController() {
        this.mockCartDAO = Mockito.mock(CartDAO.class);
        this.cartController = new CartController(this.mockCartDAO);
    }

    @Test
    public void testGetAllItems() throws IOException {
        String username = "user123";
        Cart cart = new Cart();
        Mockito.when(mockCartDAO.getCart(username)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.getAllItems(username);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(cart, response.getBody());
    }
    @Test
    public void testGetAllItemsIOException() throws IOException {
        CartDAO mockCartDAO = mock(CartDAO.class);
        when(mockCartDAO.getCart(anyString())).thenThrow(IOException.class);
        CartController cartController = new CartController(mockCartDAO);
        ResponseEntity<Cart> responseEntity = cartController.getAllItems("username");
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddItem() throws Exception {
        String username = "user123";
        Shoe shoe = new Shoe(1, "style1", Sizing.MENS, 10, 99.99, "brand1", "material1", "color1");
        Cart cart = new Cart();
        Mockito.when(mockCartDAO.getCart(username)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.addItem(username, shoe);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(cart, response.getBody());
        Mockito.verify(mockCartDAO).addToCart(username, shoe);
    }

    @Test
    public void testAddItemIOException() throws IOException {
        CartDAO mockCartDAO = mock(CartDAO.class);
        doThrow(IOException.class).when(mockCartDAO).addToCart(anyString(), any(Shoe.class));
        CartController cartController = new CartController(mockCartDAO);
        ResponseEntity<Cart> responseEntity = cartController.addItem("username", new Shoe());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testRemoveItem() throws IOException {
        String username = "user123";
        int itemId = 1;
        Cart cart = new Cart();
        when(mockCartDAO.getCart(username)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.removeItem(username, itemId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(cart, response.getBody());
        Mockito.verify(mockCartDAO).removeFromCart(username, itemId);
    }
    @Test
    public void testRemoveItemIOException() throws  IOException{
        CartDAO mockCartDAO = mock(CartDAO.class);
        doThrow(IOException.class).when(mockCartDAO).removeFromCart(anyString(), anyInt());
        CartController cartController = new CartController(mockCartDAO);
        ResponseEntity<Cart> responseEntity = cartController.removeItem("username", 1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
