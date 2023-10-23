package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents an item available in a store, which can be a bulk item. This class
 * implements the Item interface.
 */
public class StoreItem implements Item {

    private final String name;
    private final BigDecimal price;
    private final int bulkQuantity;
    private final BigDecimal bulkPrice;

    /**
     * Constructs a non-bulk StoreItem with the given name and price.
     *
     * @param name  The name of the item.
     * @param price The price of the item.
     * @throws NullPointerException     if the name or price is null.
     * @throws IllegalArgumentException if the name is empty or if the price is negative.
     */
    public StoreItem(final String name, final BigDecimal price) {
        if (name == null || name.isEmpty()) {
            if (name == null) {
                throw new NullPointerException("Item name cannot be null");
            } else {
                throw new IllegalArgumentException("Item name cannot be empty");
            }
        }
        if (price == null) {
            throw new NullPointerException("Item price cannot be null");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Item price cannot be negative");
        }

        this.name = name;
        this.price = price;
        this.bulkQuantity = 0;
        this.bulkPrice = BigDecimal.ZERO;
    }

    /**
     * Constructs a bulk StoreItem with the given name, price, bulk quantity, and bulk price.
     *
     * @param name        The name of the item.
     * @param price       The price of the item.
     * @param bulkQuantity The bulk quantity (number of items in bulk).
     * @param bulkPrice    The price for the bulk quantity.
     * @throws NullPointerException     if the name, price, or bulkPrice is null.
     * @throws IllegalArgumentException if the name is empty, if the price is negative,
     *                                  if the bulk quantity is negative, or if the bulk price is negative.
     */
    public StoreItem(final String name, final BigDecimal price, final int bulkQuantity, final BigDecimal bulkPrice) {
        if (name == null || name.isEmpty()) {
            if (name == null) {
                throw new NullPointerException("Item name cannot be null");
            } else {
                throw new IllegalArgumentException("Item name cannot be empty");
            }
        }
        if (price == null) {
            throw new NullPointerException("Item price cannot be null");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Item price cannot be negative");
        }
        if (bulkQuantity < 0) {
            throw new IllegalArgumentException("Bulk quantity cannot be negative");
        }
        if (bulkPrice == null) {
            throw new NullPointerException("Bulk price cannot be null");
        }
        if (bulkPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Bulk price cannot be negative");
        }

        this.name = name;
        this.price = price;
        this.bulkQuantity = bulkQuantity;
        this.bulkPrice = bulkPrice;
    }

    /**
     * Get the name of the item.
     *
     * @return The name of the item.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the price of the item.
     *
     * @return The price of the item.
     */
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Get the bulk quantity (number of items in bulk) for this item.
     *
     * @return The bulk quantity for this item.
     */
    @Override
    public int getBulkQuantity() {
        return bulkQuantity;
    }

    /**
     * Get the price for the bulk quantity of this item.
     *
     * @return The price for the bulk quantity of this item.
     */
    @Override
    public BigDecimal getBulkPrice() {
        return bulkPrice;
    }

    /**
     * Check if this item is a bulk item.
     *
     * @return true if this item is a bulk item, false otherwise.
     */
    @Override
    public boolean isBulk() {
        return bulkQuantity > 0 && bulkPrice.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Compares this StoreItem to the specified object for equality.
     *
     * @param o The object to compare this StoreItem to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreItem storeItem = (StoreItem) o;
        return bulkQuantity == storeItem.bulkQuantity &&
                Objects.equals(name, storeItem.name) &&
                Objects.equals(price, storeItem.price) &&
                Objects.equals(bulkPrice, storeItem.bulkPrice);
    }

    /**
     * Returns a hash code value for this StoreItem.
     *
     * @return a hash code value for this StoreItem.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, price, bulkQuantity, bulkPrice);
    }

    /**
     * Returns a string representation of this StoreItem.
     *
     * @return a string representation of this StoreItem.
     */
    @Override
    public String toString() {
        if (isBulk()) {
            return name + ", $" + price + " (" + bulkQuantity + " for $" + bulkPrice + ")";
        } else {
            return name + ", $" + price;
        }
    }
}
