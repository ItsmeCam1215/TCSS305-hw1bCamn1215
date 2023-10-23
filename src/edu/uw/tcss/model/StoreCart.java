/**
 * StoreCart is an implementation of the Cart interface designed to manage a shopping cart
 * for a store. It allows adding, removing, and calculating the total price of items in the cart.
 * Additionally, it can set and check if a customer has a membership.
 */
package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StoreCart implements Cart {

    /**
     * A list to store the orders in the cart.
     */
    private List<StoreItemOrder> orders;

    /**
     * A boolean indicating whether the customer has a membership.
     */
    private boolean hasMembership;

    /**
     * Constructs an empty StoreCart with no membership.
     */
    public StoreCart() {
        orders = new ArrayList<>();
        hasMembership = false;
    }

    /**
     * Adds a new order to the cart. If an equivalent order already exists, it is replaced.
     *
     * @param theOrder The order to add to the cart.
     */
    @Override
    public void add(final StoreItemOrder theOrder) {
        // Replace any previous order for an equivalent item
        StoreItemOrder existingOrder = findExistingOrder(theOrder);
        if (existingOrder != null) {
            orders.remove(existingOrder);
        }
        orders.add(theOrder);
    }

    /**
     * Sets the membership status of the customer.
     *
     * @param theMembership True if the customer has a membership; false otherwise.
     */
    @Override
    public void setMembership(final boolean theMembership) {
        hasMembership = theMembership;
    }

    /**
     * Calculates the total price of all items in the cart, considering membership discounts.
     *
     * @return The total price of items in the cart.
     */
    @Override
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (StoreItemOrder order : orders) {
            StoreItem item = (StoreItem) order.getItem();
            int quantity = order.getQuantity();

            if (hasMembership) {
                if (item.isBulk()) {
                    int bulkQuantity = item.getBulkQuantity();
                    BigDecimal bulkPrice = item.getBulkPrice();

                    int bulkCount = quantity / bulkQuantity;
                    int remainingQuantity = quantity % bulkQuantity;

                    total = total.add(bulkPrice.multiply(BigDecimal.valueOf(bulkCount)));
                    total = total.add(item.getPrice().multiply(BigDecimal.valueOf(remainingQuantity)));
                } else {
                    total = total.add(item.getPrice().multiply(BigDecimal.valueOf(quantity)));
                }
            } else {
                total = total.add(item.getPrice().multiply(BigDecimal.valueOf(quantity)));
            }
        }

        return total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Clears the cart by removing all items.
     */
    @Override
    public void clear() {
        orders.clear();
    }

    /**
     * Retrieves the number of items in the cart.
     *
     * @return The number of items in the cart.
     */
    @Override
    public int getCartSize() {
        return orders.size();
    }

    /**
     * Provides a string representation of the cart's contents and total price.
     *
     * @return A string representing the cart's contents and total price.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cart Contents:\n");
        for (StoreItemOrder order : orders) {
            builder.append(order.toString()).append("\n");
        }
        builder.append("Total: $").append(calculateTotal());
        return builder.toString();
    }

    /**
     * Searches for an existing order in the cart based on the item in the new order.
     *
     * @param newOrder The order to search for in the cart.
     * @return The existing order if found; otherwise, null.
     */
    private StoreItemOrder findExistingOrder(StoreItemOrder newOrder) {
        for (StoreItemOrder order : orders) {
            if (order.getItem().equals(newOrder.getItem())) {
                return order;
            }
        }
        return null;
    }

    /**
     * Checks if the customer has a membership.
     *
     * @return True if the customer has a membership; false otherwise.
     */
    public boolean isMember() {
        return hasMembership;
    }
}
