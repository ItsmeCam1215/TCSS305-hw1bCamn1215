package edu.uw.tcss.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class StoreCartTest {
    private Cart cart;
    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        cart = new StoreCart();
        item1 = new StoreItem("Item1", new BigDecimal("10.00"));
        item2 = new StoreItem("Item2", new BigDecimal("5.00"), 5, new BigDecimal("20.00"));
    }

    // Test to verify that items are added to the cart and the cart size is updated correctly.
    @Test
    void testAdd() {
        cart.add(new StoreItemOrder(item1, 2));
        cart.add(new StoreItemOrder(item2, 3));

        assertEquals(2, cart.getCartSize());
    }

    // Test to verify that the membership status of the cart can be set and retrieved correctly.
    @Test
    void testSetMembership() {
        cart.setMembership(true);
        assertTrue(((StoreCart)cart).isMember());
        cart.setMembership(false);
        assertFalse(((StoreCart)cart).isMember());
    }

    // Test to calculate the total cost of items in the cart.
    @Test
    void testCalculateTotal() {
        cart.add(new StoreItemOrder(item1, 2));
        cart.add(new StoreItemOrder(item2, 3));

        assertEquals(new BigDecimal("35.00"), cart.calculateTotal());
    }

    // Test to verify that the cart can be cleared, and the cart size is set to 0.
    @Test
    void testClear() {
        cart.add(new StoreItemOrder(item1, 2));
        cart.clear();
        assertEquals(0, cart.getCartSize());
    }
}
