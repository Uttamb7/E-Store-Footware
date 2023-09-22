package com.estore.api.estoreapi;

import com.estore.api.estoreapi.enums.Sizing;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import static org.junit.Assert.assertEquals;

@Tag("Enums")
public class SizingTest {

    @Test
    public void testGetSizingForMens() {
        String expectedUnit = "Mens";
        String actualUnit = Sizing.MENS.getSizing();
        assertEquals(expectedUnit, actualUnit);
    }

    @Test
    public void testGetSizingForWomens() {
        String expectedUnit = "Womens";
        String actualUnit = Sizing.WOMENS.getSizing();
        assertEquals(expectedUnit, actualUnit);
    }

    @Test
    public void testGetSizingForKids() {
        String expectedUnit = "Kids";
        String actualUnit = Sizing.KIDS.getSizing();
        assertEquals(expectedUnit, actualUnit);
    }
}
