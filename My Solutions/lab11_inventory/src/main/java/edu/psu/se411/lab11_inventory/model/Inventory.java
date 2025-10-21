package edu.psu.se411.lab11_inventory.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> {
    private List<T> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    // Add an item to the inventory
    public void addItem(T item) {
        items.add(item);
    }

    // Remove an item from the inventory
    public boolean removeItem(T item) {
        return items.remove(item);
    }

    // Get all items in the inventory
    public List<T> getAllItems() {
        return new ArrayList<>(items);
    }

    // Find an item based on search criteria
    public T findItem(SearchCriteria<T> criteria) {
        for (T item : items) {
            if (criteria.matches(item)) {
                return item;
            }
        }
        return null;
    }

    // Get the size of the inventory
    public int size() {
        return items.size();
    }
}
