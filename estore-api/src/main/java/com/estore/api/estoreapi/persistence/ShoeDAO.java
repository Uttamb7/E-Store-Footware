package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Shoe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

/**
 * This interface lays out the required functions for a Shoe DAO
 * The Shoe DAO is expected to be able to interact with these operations
 * in a timely manner.
 */
public interface ShoeDAO {

    /**
     * Retrieves a shoe by its ID
     *
     * @param id The ID of the shoe to retrieve
     * @return The shoe in question, or null if no shoe by that id exists
     * @throws IOException if there is an issue within the DAO
     */
    Shoe getShoeById(int id) throws IOException;

    /**
     * Retrieves all the shoes
     *
     * @return An array of all the shoes in the DAO
     * @throws IOException if there is an issue within the DAO
     */
    Shoe[] getAllShoes() throws IOException;

    /**
     * Searches the shoes' properties with a given string
     *
     * @param searchText The text to search the properties by
     * @return An array of shoes that match the criteria, can be empty if no results
     * @throws IOException if there is an issue within the DAO
     */
    Shoe[] searchShoes(String searchText) throws IOException;

    /**
     * Creates a shoe given a blank shoe object.
     * If the id is 0 within newShoe, the DAO is responsible for finding a valid id
     *
     * @param newShoe The new shoe object to create
     * @return The created shoe object as it exists within the DAO
     * @throws IOException                if there is an issue within the DAO
     * @throws FileAlreadyExistsException if the given id conflicts with one within the DAO
     */
    Shoe createShoe(Shoe newShoe) throws IOException, FileAlreadyExistsException;

    /**
     * Updates the given shoe object within the DAO, given the shoe object to update
     * with it's corresponding id
     *
     * @param updateShoe The new shoe object to update within the DAO
     * @return The updated shoe object as it exists within the DAO
     * @throws IOException           if there is an issue within the DAO
     * @throws FileNotFoundException if the given shoe id does not exist within the DAO
     */
    Shoe updateShoe(Shoe updateShoe) throws IOException, FileNotFoundException;

    /**
     * Deletes the shoe given the id
     *
     * @param id The id to delete the shoe by
     * @return a boolean represneting if the shoe was deleted successfully (true) or if it didn't exist (false)
     * @throws IOException if there is an issue within the DAO
     */
    boolean deleteShoeById(int id) throws IOException;

}
