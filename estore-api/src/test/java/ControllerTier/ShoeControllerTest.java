package ControllerTier;

import com.estore.api.estoreapi.controller.ShoeController;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.ShoeDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import static com.estore.api.estoreapi.enums.Sizing.MENS;

@Tag("Controller-tier")
public class ShoeControllerTest {
    private ShoeController shoeController;
    private ShoeDAO mockShoeDAO;

    public ShoeControllerTest() {
    }

    @BeforeEach
    public void setupShoeController() {
        this.mockShoeDAO = Mockito.mock(ShoeDAO.class);
        this.shoeController = new ShoeController(this.mockShoeDAO);
    }

    @Test
    public void testGetShoeById() throws IOException {
        Shoe shoe = new Shoe();
        Mockito.when(this.mockShoeDAO.getShoeById(shoe.getId())).thenReturn(shoe);
        ResponseEntity<Shoe> response = this.shoeController.getShoeById(shoe.getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testGetShoeNotFound() throws Exception {
        int shoeId = 45;
        Mockito.when(this.mockShoeDAO.getShoeById(shoeId)).thenReturn(null);
        ResponseEntity<Shoe> response = this.shoeController.getShoeById(shoeId);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetShoeHandleException() throws Exception {
        int shoeId = 1;
        Mockito.doThrow(new IOException()).when(this.mockShoeDAO).getShoeById(shoeId);
        ResponseEntity<Shoe> response = this.shoeController.getShoeById(shoeId);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCreateShoe() throws IOException {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        Mockito.when(this.mockShoeDAO.createShoe(shoe)).thenReturn(shoe);
        ResponseEntity<Shoe> response = this.shoeController.create(shoe);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testCreateShoeFailed() throws IOException {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        Mockito.when(this.mockShoeDAO.createShoe(shoe)).thenThrow(new FileAlreadyExistsException("This Shoe Already Exists"));
        ResponseEntity<Shoe> response = this.shoeController.create(shoe);
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateShoeHandleException() throws IOException {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        Mockito.doThrow(new IOException()).when(this.mockShoeDAO).createShoe(shoe);
        ResponseEntity<Shoe> response = this.shoeController.create(shoe);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testUpdateShoe() throws IOException {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        Mockito.when(this.mockShoeDAO.updateShoe(shoe)).thenReturn(shoe);
        this.shoeController.update(shoe);
        shoe.setStyle("Jordan 3 White Cement");
        ResponseEntity<Shoe> response = this.shoeController.update(shoe);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testUpdateShoeFailed() throws IOException {
        Shoe shoe = new Shoe(4, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        Mockito.when(this.mockShoeDAO.updateShoe(shoe)).thenThrow(new FileNotFoundException());
        ResponseEntity<Shoe> response = this.shoeController.update(shoe);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateShoeHandleException() throws IOException {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        Mockito.doThrow(new IOException()).when(this.mockShoeDAO).updateShoe(shoe);
        ResponseEntity<Shoe> response = this.shoeController.update(shoe);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetAllShoes() throws IOException {
        Shoe[] shoes = new Shoe[]{
                new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red"),
                new Shoe(2, "Yeezy 350 V2", MENS, 12, 229.99, "Adidas", "Mesh", "Zebra")};
        Mockito.when(this.mockShoeDAO.getAllShoes()).thenReturn(shoes);
        ResponseEntity<Shoe[]> response = this.shoeController.getAllShoes();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoes, response.getBody());
    }

    @Test
    public void testGetShoesHandleException() throws IOException {
        Mockito.doThrow(new IOException()).when(this.mockShoeDAO).getAllShoes();
        ResponseEntity<Shoe[]> response = this.shoeController.getAllShoes();
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testSearchShoes() throws IOException {
        String searchString = "Jordan";
        Shoe[] shoes = new Shoe[]{
                new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red"),
                new Shoe(2, "Yeezy 350 V2", MENS, 12, 229.99, "Adidas", "Mesh", "Zebra")};
        Mockito.when(this.mockShoeDAO.searchShoes(searchString)).thenReturn(shoes);
        ResponseEntity<Shoe[]> response = this.shoeController.search(searchString);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoes, response.getBody());
    }

    @Test
    public void testSearchShoeHandleException() throws IOException {
        String searchString = "Asics";
        Mockito.doThrow(new IOException()).when(this.mockShoeDAO).searchShoes(searchString);
        ResponseEntity<Shoe[]> response = this.shoeController.search(searchString);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteShoe() throws IOException {
        int shoeId = 1;
        Mockito.when(this.mockShoeDAO.deleteShoeById(shoeId)).thenReturn(true);
        ResponseEntity<Shoe> response = this.shoeController.delete(shoeId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteShoeNotFound() throws IOException {
        int shoeId = 1;
        Mockito.when(this.mockShoeDAO.deleteShoeById(shoeId)).thenReturn(false);
        ResponseEntity<Shoe> response = this.shoeController.delete(shoeId);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteShoeHandleException() throws IOException {
        int shoeId = 99;
        Mockito.doThrow(new IOException(new FileNotFoundException())).when(this.mockShoeDAO).deleteShoeById(shoeId);
        ResponseEntity<Shoe> response = this.shoeController.delete(shoeId);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}


