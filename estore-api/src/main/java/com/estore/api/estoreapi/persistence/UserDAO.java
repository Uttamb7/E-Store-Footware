package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.User;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Persistence for the User
 *
 * @author Connor Bastian, crb1759@rit.edu
 */
public interface UserDAO {
    //Create a User
    User createUser(User user) throws IOException;

    //Find a user based on username
    User findByUsername(String username) throws IOException;

    //Update a user's information
    User updateUser(User user) throws IOException;

    //Check is user has admin privileges
    boolean isAdmin(String username, String password) throws IOException;

    //Check if user is a customer
    boolean isCustomer(String username, String password) throws IOException;

    ArrayList<User> getUsers(String userInfo) throws IOException;

    ArrayList<User> getUsers() throws IOException;
}
