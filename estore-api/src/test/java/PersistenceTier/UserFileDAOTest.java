package PersistenceTier;

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@Tag("Persistence-Tier")
public class UserFileDAOTest {

    private ObjectMapper objectMapper;

    UserFileDAO userFileDAO;

    User[] mockUserArray;

    public UserFileDAOTest() {
    }

    User user1;
    User user2;

    @BeforeEach
    public void setupUserFileDAO() throws IOException {
        this.user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("user1");

        this.user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("user2");

        this.mockUserArray = new User[]{user1, user2};
        this.objectMapper = mock(ObjectMapper.class);
        when(objectMapper.readValue(any(File.class), eq(User[].class))).thenReturn(this.mockUserArray);

        this.userFileDAO = new UserFileDAO("data/testing.txt", objectMapper);
    }

    @Test
    public void testCreateUser() throws IOException {
        setupUserFileDAO();

        User user = new User();
        user.setUsername("testUser1");
        user.setPassword("testUserPassword");
        User returnedUser = userFileDAO.createUser(user);
        Assertions.assertEquals(user.getUsername(), returnedUser.getUsername());
        Assertions.assertEquals(user.getPassword(), returnedUser.getPassword());
    }

    @Test
    public void testCreateUserAlreadyExists() throws IOException {
        setupUserFileDAO();

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        userFileDAO.createUser(user);
        Assertions.assertThrows(FileAlreadyExistsException.class, () -> userFileDAO.createUser(user));
    }

    @Test
    public void testFindByUsername() throws IOException {
        setupUserFileDAO();

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        userFileDAO.createUser(user);
        User returnedUser = userFileDAO.findByUsername(user.getUsername());
        Assertions.assertEquals(user.getUsername(), returnedUser.getUsername());
        Assertions.assertEquals(user.getPassword(), returnedUser.getPassword());
    }

    @Test
    public void testUpdateUser() throws IOException {
        setupUserFileDAO();

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        userFileDAO.createUser(user);
        User updatedUser = new User();
        updatedUser.setUsername("testUser");
        updatedUser.setPassword("testPassword");
        User returnedUser = userFileDAO.updateUser(updatedUser);
        Assertions.assertEquals(updatedUser.getPassword(), returnedUser.getPassword());
    }

    @Test
    public void testIsAdmin() throws IOException {
        setupUserFileDAO();

        Assertions.assertTrue(userFileDAO.isAdmin("admin", "password"));
        Assertions.assertFalse(userFileDAO.isAdmin("user", "password"));
    }

    @Test
    public void testIsCustomer() throws IOException {
        setupUserFileDAO();

        Assertions.assertTrue(userFileDAO.isCustomer("user", "password"));
        Assertions.assertFalse(userFileDAO.isCustomer("admin", "password"));
    }

    @Test
    public void testGetUsers() throws IOException {
        setupUserFileDAO();

        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("user1");
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("user2");

        try {
            userFileDAO.createUser(user1);
            userFileDAO.createUser(user2);
            Assertions.fail("User already exists");
        } catch (FileAlreadyExistsException ignored) {
        }

        ArrayList<User> users = new ArrayList<>(userFileDAO.getUsers());
        Assertions.assertEquals(2, users.size());
        Assertions.assertTrue(users.contains(user1));
        Assertions.assertTrue(users.contains(user2));
    }

    @Test
    public void testGetUsersWithInfo() throws IOException {
        setupUserFileDAO();

        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("user1");
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("user2");

        ArrayList<User> users = userFileDAO.getUsers("user1");
        Assertions.assertEquals(1, users.size());
        Assertions.assertTrue(users.contains(user1));
        Assertions.assertFalse(users.contains(user2));
    }

}
