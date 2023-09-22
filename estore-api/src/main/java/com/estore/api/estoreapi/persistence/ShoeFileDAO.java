package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.utils.FlatFileOps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class ShoeFileDAO implements ShoeDAO {
    private static final Logger LOG = Logger.getLogger(ShoeFileDAO.class.getName());
    // objects and JSON text format written
    // to the file
    private static int nextId;  // The next Id to assign to a new shoe
    // so that we don't need to read from the file
    // each time
    private final ObjectMapper objectMapper;  // Provides conversion between Shoe
    private final String filename;    // Filename to read from and write to
    Map<Integer, Shoe> shoes = new HashMap<>();   // Provides a local cache of the shoe objects

    /**
     * Creates a Shoe File Data Access Object
     *
     * @param filename     Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * @throws IOException when file cannot be accessed or read from
     */
    public ShoeFileDAO(@Value("${dao.shoes}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the heroes from the file
    }

    /**
     * Generates the next id for a new {@linkplain Shoe shoe}
     *
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Shoe shoes} from the tree map
     *
     * @return The array of {@link Shoe shoes}, may be empty
     */
    public Shoe[] getAllShoes() {
        return getAllShoes(null);
    }

    /**
     * Generates an array of {@linkplain Shoe shoes} from the tree map for any
     * {@linkplain Shoe shoes} that contains the text specified by containsText
     * <br>
     *
     * @return The array of {@link Shoe shoes}, may be empty
     */
    private Shoe[] getAllShoes(String containsText) { // if containsText == null, no filter
        ArrayList<Shoe> shoeArrayList = new ArrayList<>();


        if (containsText == null) {
            shoeArrayList.addAll(shoes.values());
        } else {
            String searchText = containsText.trim().toLowerCase();
            for (Shoe shoe : shoes.values()) {
                if (shoe.toString().toLowerCase().contains(searchText)) {
                    shoeArrayList.add(shoe);
                }
            }
        }

        Shoe[] shoeArray = new Shoe[shoeArrayList.size()];
        shoeArrayList.toArray(shoeArray);
        return shoeArray;
    }

    /**
     * Saves the {@linkplain Shoe shoes} from the map into the file as an array of JSON objects
     *
     * @return true if the {@link Shoe shoes} were written successfully
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Shoe[] shoeArray = getAllShoes();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename), shoeArray);
        return true;
    }

    /**
     * Loads {@linkplain Shoe shoes} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     *
     * @return true if the file was read successfully
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        nextId = 0;
        // Deserializes the JSON objects from the file into an array of shoes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file

        FlatFileOps.ensureDataFileExists(filename);
        Shoe[] shoeArray = objectMapper.readValue(new File(filename), Shoe[].class);

        // Add each shoe to the tree map and keep track of the greatest id
        for (Shoe shoe : shoeArray) {
            shoes.put(shoe.getId(), shoe);
            if (shoe.getId() > nextId) nextId = shoe.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
     * * {@inheritDoc}
     */
    @Override
    public Shoe[] searchShoes(String containsText) {
        synchronized (shoes) {
            return getAllShoes(containsText);
        }
    }

    /**
     * * {@inheritDoc}
     */
    @Override
    public Shoe createShoe(Shoe inputShoe) throws IOException {
        synchronized (shoes) {
            if (shoes.containsKey(inputShoe.getId())) {
                throw new FileAlreadyExistsException("ID already exists.");
            }

            if (inputShoe.getId() == 0) {
                inputShoe.setId(nextId());
            }

            Shoe newShoe = new Shoe();
            newShoe.setBrand(inputShoe.getBrand());
            newShoe.setColor(inputShoe.getColor());
            newShoe.setId(inputShoe.getId());
            newShoe.setSize(inputShoe.getSize());
            newShoe.setPrice(inputShoe.getPrice());
            newShoe.setMaterial(inputShoe.getMaterial());
            newShoe.setStyle(inputShoe.getStyle());
            newShoe.setSizing(inputShoe.getSizing());

            shoes.put(newShoe.getId(), newShoe);
            save(); // may throw an IOException
            return newShoe;
        }
    }

    /**
     * * {@inheritDoc}
     */
    @Override
    public Shoe updateShoe(Shoe updateShoe) throws IOException {
        synchronized (shoes) {
            if (!shoes.containsKey(updateShoe.getId())) {
                throw new FileNotFoundException("Shoe does not exist");
            }

            Shoe currentShoe = shoes.get(updateShoe.getId());
            if (updateShoe.getStyle() != null) currentShoe.setStyle(updateShoe.getStyle());
            if (updateShoe.getBrand() != null) currentShoe.setBrand(updateShoe.getBrand());
            if (updateShoe.getColor() != null) currentShoe.setColor(updateShoe.getColor());
            if (updateShoe.getMaterial() != null) currentShoe.setMaterial(updateShoe.getMaterial());
            if (updateShoe.getPrice() != 0) currentShoe.setPrice(updateShoe.getPrice());
            if (updateShoe.getSize() != 0) currentShoe.setSize(updateShoe.getSize());
            if (updateShoe.getSizing() != null) currentShoe.setSizing(updateShoe.getSizing());

            shoes.put(currentShoe.getId(), currentShoe);
            save(); // may throw an IOException
            return currentShoe;
        }
    }

    @Override
    public Shoe getShoeById(int id) throws IOException {
        synchronized (shoes) {
            return (shoes.getOrDefault(id, null));
        }
    }

    /**
     * * {@inheritDoc}
     */
    @Override
    public boolean deleteShoeById(int id) throws IOException {
        synchronized (shoes) {
            if (shoes.containsKey(id)) {
                shoes.remove(id);
                return save();
            } else return false;
        }
    }
}
