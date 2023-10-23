package edu.uw.tcss.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class StoreItemOrderTest {
    private Item item;
    private ItemOrder itemOrder;

    @BeforeEach
    void setUp() {
        item = new StoreItem("Item", new BigDecimal("10.00"));
        itemOrder = new StoreItemOrder(item, 3);
    }

    // Test to verify that the `getItem` method returns the expected item.
    @Test
    void testGetItem() {
        assertEquals(item, itemOrder.getItem());
    }

    // Test to verify that the `getQuantity` method returns the expected quantity.
    @Test
    void testGetQuantity() {
        assertEquals(3, itemOrder.getQuantity());
    }

    // Test to verify that the `toString` method generates the expected string representation.
    @Test
    void testToString() {
        assertEquals("Item: Item, Quantity: 3", itemOrder.toString());
    }
}
