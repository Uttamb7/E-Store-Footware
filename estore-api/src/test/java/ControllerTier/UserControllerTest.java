package ControllerTier;
import com.estore.api.estoreapi.controller.UserController;
import com.estore.api.estoreapi.persistence.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.estore.api.estoreapi.model.User;
@Tag("Controller-tier")
class UserControllerTest {

    private UserController userController;

    private UserDAO mockUserDao;

    public UserControllerTest() {
    }

    @BeforeEach
    public void setUpUserController() {
        this.mockUserDao = Mockito.mock(UserDAO.class);
        this.userController = new UserController(this.mockUserDao);
    }

    @Test
    public void getUsersTest() throws IOException {
        ArrayList<User> expectedUsers = new ArrayList<>();
        when(mockUserDao.getUsers(any())).thenReturn(expectedUsers);

        ResponseEntity<ArrayList<User>> response = userController.getUsers("search");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
        Mockito.verify(mockUserDao).getUsers("search");
    }

    @Test
    public void getUsersIOExceptionTest() throws IOException {
        when(mockUserDao.getUsers(any())).thenThrow(new IOException());

        ResponseEntity<ArrayList<User>> response = userController.getUsers("search");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Mockito.verify(mockUserDao).getUsers("search");
    }

    @Test
    public void createUserTest() throws IOException {
        User expectedUser = new User();
        Mockito.when(mockUserDao.createUser(any())).thenReturn(expectedUser);

        ResponseEntity<?> response = userController.createUser(expectedUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
        Mockito.verify(mockUserDao).createUser(expectedUser);
    }

    @Test
    public void createUserIOExceptionTest() throws IOException {
        Mockito.when(mockUserDao.createUser(any())).thenThrow(new IOException());

        ResponseEntity<?> response = userController.createUser(new User());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Mockito.verify(mockUserDao).createUser(any());
    }

    @Test
    public void loginTest() throws IOException {
        User expectedUser = new User();
        expectedUser.setUsername("username");
        Mockito.when(mockUserDao.findByUsername("username")).thenReturn(expectedUser);

        ResponseEntity<User> response = userController.login(expectedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
        Mockito.verify(mockUserDao).findByUsername("username");
    }
    @Test
    public void testLoginUserNotFound() throws IOException {
        User loginAttempt = new User();
        loginAttempt.setUsername("nonExistingUser");
        Mockito.when(mockUserDao.findByUsername(loginAttempt.getUsername())).thenReturn(null);

        ResponseEntity<User> response = userController.login(loginAttempt);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Mockito.verify(mockUserDao).findByUsername(loginAttempt.getUsername());
    }

    @Test
    public void testLoginWrongPassword() throws IOException {
        User expectedUser = new User();
        expectedUser.setUsername("testUser");
        expectedUser.setPassword("password");
        User loginAttempt = new User();
        loginAttempt.setUsername("testUser");
        loginAttempt.setPassword("wrongPassword");
        Mockito.when(mockUserDao.findByUsername("testUser")).thenReturn(expectedUser);

        ResponseEntity<User> response = userController.login(loginAttempt);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        Mockito.verify(mockUserDao).findByUsername("testUser");
    }
    @Test
    public void testLoginIOException() throws IOException {
        User expectedUser = new User();
        expectedUser.setUsername("username");
        expectedUser.setPassword("password");

        Mockito.when(mockUserDao.findByUsername(expectedUser.getUsername())).thenThrow(new IOException());

        ResponseEntity<User> response = userController.login(expectedUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Mockito.verify(mockUserDao).findByUsername(expectedUser.getUsername());
    }
    @Test
    public void getUserByUsernameTest() throws IOException {
        User expectedUser = new User();
        expectedUser.setUsername("testuser");
        Mockito.when(mockUserDao.findByUsername(any())).thenReturn(expectedUser);

        ResponseEntity<?> response = userController.getUserByUsername("testuser");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
        Mockito.verify(mockUserDao).findByUsername("testuser");
    }

    @Test
    public void getUserByUsernameNotFoundTest() throws IOException {
        Mockito.when(mockUserDao.findByUsername(any())).thenReturn(null);

        ResponseEntity<?> response = userController.getUserByUsername("testuser");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
        Mockito.verify(mockUserDao).findByUsername("testuser");
    }

    @Test
    public void getUserByUsernameIOExceptionTest() throws IOException {
        Mockito.when(mockUserDao.findByUsername(any())).thenThrow(new IOException());

        ResponseEntity<?> response = userController.getUserByUsername("testuser");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error getting user", response.getBody());
        Mockito.verify(mockUserDao).findByUsername("testuser");
    }

    @Test
    public void updateUserTest() throws IOException {
        User user = new User();
        User updatedUser = new User();
        Mockito.when(mockUserDao.updateUser(Mockito.any(User.class))).thenReturn(updatedUser);

        ResponseEntity<String> response = userController.updateUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User updated successfully", response.getBody());

        Mockito.verify(mockUserDao).updateUser(Mockito.eq(updatedUser));
    }

    @Test
    public void updateUserIOExceptionTest() throws IOException {
        User user = new User();
        Mockito.doThrow(new IOException()).when(mockUserDao).updateUser(user);

        ResponseEntity<String> response = userController.updateUser(user);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error updating user", response.getBody());
        Mockito.verify(mockUserDao).updateUser(user);
    }
}