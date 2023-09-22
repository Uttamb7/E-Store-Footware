package com.estore.api.estoreapi.controller;

import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.ShoeDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

@RestController
@RequestMapping("shoe")
public class ShoeController {

    private final ShoeDAO shoeDAO;

    public ShoeController(ShoeDAO shoeDAO) {
        this.shoeDAO = shoeDAO;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Shoe> getShoeById(@PathVariable int id) {
        try {
            Shoe shoe = shoeDAO.getShoeById(id);
            if (shoe == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(shoe, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Shoe[]> getAllShoes() {
        try {
            Shoe[] shoes = shoeDAO.getAllShoes();
            return new ResponseEntity<>(shoes, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Shoe[]> search(@RequestParam String query) {
        try {
            Shoe[] shoes = shoeDAO.searchShoes(query);
            return new ResponseEntity<>(shoes, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Shoe> create(@RequestBody Shoe rawShoe) {
        try {
            Shoe newShoe = shoeDAO.createShoe(rawShoe);
            return new ResponseEntity<>(newShoe, HttpStatus.CREATED);
        } catch (FileAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/")
    public ResponseEntity<Shoe> update(@RequestBody Shoe rawShoe) {
        try {
            Shoe updatedShoe = shoeDAO.updateShoe(rawShoe);
            return new ResponseEntity<>(updatedShoe, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Shoe> delete(@PathVariable int id) {
        try {
            boolean deleted = shoeDAO.deleteShoeById(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
