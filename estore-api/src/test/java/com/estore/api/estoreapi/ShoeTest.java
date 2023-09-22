package com.estore.api.estoreapi;

import com.estore.api.estoreapi.enums.Sizing;
import com.estore.api.estoreapi.model.Shoe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.estore.api.estoreapi.enums.Sizing.WOMENS;


@Tag("Model-tier")
public class ShoeTest {

    Shoe testShoe = new Shoe();

    @Test
    public void testID() {
        testShoe.setId(3);
        int expectedID = 3;
        int actualID = testShoe.getId();
        Assertions.assertEquals(expectedID, actualID);
    }

    @Test
    public void testStyle() {
        testShoe.setStyle("Jordan 3 White Cement");
        String expectedStyle = "Jordan 3 White Cement";
        String actualStyle = testShoe.getStyle();
        Assertions.assertEquals(expectedStyle, actualStyle);
    }

    @Test
    public void testSizing() {
        testShoe.setSizing(WOMENS);
        Sizing expectedSizing = WOMENS;
        Sizing actualSizing = testShoe.getSizing();
        Assertions.assertEquals(expectedSizing, actualSizing);
    }

    @Test
    public void testShoeSize() {
        testShoe.setSize(7);
        double expectedSize = 7;
        double actualSize = testShoe.getSize();
        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testShoePrice() {
        testShoe.setPrice(224.99);
        double expectedPrice = 224.99;
        double actualPrice = testShoe.getPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testBrand() {
        testShoe.setBrand("Jordan");
        String expectedBrand = "Jordan";
        String actualBrand = testShoe.getBrand();
        Assertions.assertEquals(expectedBrand, actualBrand);
    }

    @Test
    public void testMaterial() {
        testShoe.setMaterial("Leather");
        String expectedMaterial = "Leather";
        String actualMaterial = testShoe.getMaterial();
        Assertions.assertEquals(expectedMaterial, actualMaterial);
    }

    @Test
    public void testColor() {
        testShoe.setColor("White");
        String expectedColor = "White";
        String actualColor = testShoe.getColor();
        Assertions.assertEquals(expectedColor, actualColor);
    }

}
