package PersistenceTier;

import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.ShoeFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.estore.api.estoreapi.enums.Sizing.MENS;
import static com.estore.api.estoreapi.enums.Sizing.WOMENS;
import static org.mockito.Mockito.*;

@Tag("Persistence-tier")
public class ShoeFileDAOTest {

    Shoe shoe1, shoe2, shoe3;

    Shoe[] mockShoeArray;

    ShoeFileDAO mockShoeFileDAO;

    public ShoeFileDAOTest() {
    }

    @BeforeEach
    public void setupShoeFileDAO() throws IOException {
        shoe1 = new Shoe(1, "Air Max 90", MENS, 9, 129.99, "Nike", "Leather", "Green");
        shoe2 = new Shoe(2, "Yeezy 500", MENS, 11, 229.99, "Adidas", "Multiple", "Tan");
        shoe3 = new Shoe(3, "Jordan 3 White Cement", WOMENS, 7, 224.99, "Jordan", "Leather", "White");

        this.mockShoeArray = new Shoe[]{shoe1, shoe2, shoe3};
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        when(mockObjectMapper.readValue(any(File.class), eq(Shoe[].class))).thenReturn(this.mockShoeArray);

        this.mockShoeFileDAO = new ShoeFileDAO("data/testing.txt", mockObjectMapper);
    }

    @Test
    public void testGetShoes() throws IOException {
        Shoe[] shoeArray = mockShoeFileDAO.getAllShoes();
        Assertions.assertNotNull(shoeArray);
        Assertions.assertEquals(3, shoeArray.length);
        for (int i = 0; i < shoeArray.length; i++) {
            Assertions.assertEquals(shoeArray[i], this.mockShoeArray[i]);
        }
    }

    @Test
    public void testSearchShoesByStyle() throws IOException {
        Shoe[] shoeArray = this.mockShoeFileDAO.searchShoes("Air");
        Assertions.assertEquals(shoeArray.length, 1);
        Assertions.assertEquals(shoeArray[0], this.mockShoeArray[0]);
    }

    @Test
    public void testGetShoeById() throws IOException {
        Shoe shoe = this.mockShoeFileDAO.getShoeById(1);
        Assertions.assertEquals(shoe, this.mockShoeArray[0]);
    }

    @Test
    public void testDeleteShoeById() throws IOException {
        boolean result = Assertions.assertDoesNotThrow(() -> {
            return this.mockShoeFileDAO.deleteShoeById(1);
        }, "Unexpected exception thrown");
        Assertions.assertTrue(result);
        Assertions.assertEquals(this.mockShoeFileDAO.getAllShoes().length, this.mockShoeArray.length - 1);
    }

    @Test
    public void testCreateShoe() throws Exception {
        Shoe shoe = new Shoe(4, "Chuck Taylor", MENS, 8, 79.99, "Converse", "Canvas", "Black");
        Shoe result = Assertions.assertDoesNotThrow(() -> {
            return this.mockShoeFileDAO.createShoe(shoe);
        }, "Unexpected Exception Thrown");
        Assertions.assertNotNull(result);
        Shoe actual = this.mockShoeFileDAO.getShoeById(shoe.getId());
        Assertions.assertEquals(actual.getId(), shoe.getId());
        Assertions.assertEquals(actual.getStyle(), shoe.getStyle());
        Assertions.assertEquals(actual.getSizing(), shoe.getSizing());
        Assertions.assertEquals(actual.getSize(), shoe.getSize());
        Assertions.assertEquals(actual.getPrice(), shoe.getPrice());
        Assertions.assertEquals(actual.getBrand(), shoe.getBrand());
        Assertions.assertEquals(actual.getMaterial(), shoe.getMaterial());
        Assertions.assertEquals(actual.getColor(), shoe.getColor());
    }

    @Test
    public void testUpdateShoe() throws IOException {
        Shoe shoe = new Shoe(5, "Stan Smiths", MENS, 11, 89.99, "Adidas", "Leather", "White");
        Shoe updatedShoe = new Shoe(5, "Air Jordans", WOMENS, 10, 119.99, "Nike", "Synthetic", "Black");
        try {
            mockShoeFileDAO.createShoe(shoe);
            Shoe result = mockShoeFileDAO.updateShoe(updatedShoe);

            Assertions.assertNotNull(result);
            Assertions.assertEquals(updatedShoe.getId(), result.getId());
            Assertions.assertEquals(updatedShoe.getStyle(), result.getStyle());
            Assertions.assertEquals(updatedShoe.getSizing(), result.getSizing());
            Assertions.assertEquals(updatedShoe.getSize(), result.getSize());
            Assertions.assertEquals(updatedShoe.getPrice(), result.getPrice());
            Assertions.assertEquals(updatedShoe.getBrand(), result.getBrand());
            Assertions.assertEquals(updatedShoe.getMaterial(), result.getMaterial());
            Assertions.assertEquals(updatedShoe.getColor(), result.getColor());
        } catch (Exception e) {
            Assertions.fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetShoeNotFound() throws IOException {
        Shoe shoe = this.mockShoeFileDAO.getShoeById(98);
        Assertions.assertEquals(shoe, null);
    }

    @Test
    public void testUpdateShoeNotFound() {
        Shoe shoe = new Shoe(5, "Stan Smiths", MENS, 11, 89.99, "Adidas", "Leather", "White");
        Shoe result = null;
        try {
            result = this.mockShoeFileDAO.updateShoe(shoe);
            Assertions.fail("Exception not thrown");
        } catch (IOException ignored) {
        }
        Assertions.assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        doThrow(new IOException()).when(mockObjectMapper).readValue(new File("data/doesnt_matter.txt"), Shoe[].class);
        Assertions.assertThrows(IOException.class, () -> {
            new ShoeFileDAO("data/doesnt_matter.txt", mockObjectMapper);
        }, "IOException not thrown");
    }
    @Test
    public void testDeleteShoeByIdNotFound() throws IOException {
        boolean result = Assertions.assertDoesNotThrow(() -> {
            return this.mockShoeFileDAO.deleteShoeById(100);
        }, "Unexpected exception thrown");
        Assertions.assertFalse(result);
        Assertions.assertEquals(this.mockShoeFileDAO.getAllShoes().length, this.mockShoeArray.length);
    }
    @Test
    public void testCreateDuplicateShoe() throws Exception {
        Shoe shoe = new Shoe(1, "Air Max 90", MENS, 9, 129.99, "Nike", "Leather", "Green");
        Assertions.assertThrows(Exception.class, () -> {
            this.mockShoeFileDAO.createShoe(shoe);
        }, "Expected exception not thrown");
    }
    @Test
    public void testSearchShoesBySize() throws IOException {
        Shoe[] shoeArray = this.mockShoeFileDAO.searchShoes(String.valueOf(9));
        Assertions.assertEquals(shoeArray.length, 3);
        Assertions.assertEquals(shoeArray[0], this.mockShoeArray[0]);
    }
    @Test
    public void testUpdateShoeInvalidId() throws IOException {
        Shoe shoeToUpdate = new Shoe(100, "Air Max 90", MENS, 9, 129.99, "Nike", "Leather", "Green");
        Shoe updatedShoe = new Shoe(100, "Air Max 90", MENS, 9, 149.99, "Nike", "Leather", "Green");
        Shoe result = null;
        try {
            result = this.mockShoeFileDAO.updateShoe(updatedShoe);
            Assertions.fail("Expected exception not thrown");
        } catch (IOException ignored) {
        }
        Assertions.assertNull(result);
    }
}