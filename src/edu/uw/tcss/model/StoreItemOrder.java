/**
 * Represents an order for a specific item, indicating the item and the quantity ordered.
 * This class implements the ItemOrder interface.
 */
package edu.uw.tcss.model;

public final class StoreItemOrder implements ItemOrder {
    private final Item item;
    private final int quantity;

    /**
     * Constructs a StoreItemOrder for a given item and quantity.
     *
     * @param theItem     The item to be ordered.
     * @param theQuantity The quantity of the item to be ordered.
     */
    public StoreItemOrder(final Item theItem, final int theQuantity) {
        // Store the provided item and quantity
        this.item = theItem;
        this.quantity = theQuantity;
    }

    /**
     * Get the item associated with this order.
     *
     * @return The item in the order.
     */
    @Override
    public Item getItem() {
        // Return the stored item
        return item;
    }

    /**
     * Get the quantity of the item in this order.
     *
     * @return The quantity of the item in the order.
     */
    @Override
    public int getQuantity() {
        // Return the stored quantity
        return quantity;
    }

    /**
     * Returns a string representation of this item order.
     *
     * @return A string representing the item and its quantity in the order.
     */
    @Override
    public String toString() {
        // Create a string representation of the item order
        return "Item: " + item.getName() + ", Quantity: " + quantity;
    }
}
