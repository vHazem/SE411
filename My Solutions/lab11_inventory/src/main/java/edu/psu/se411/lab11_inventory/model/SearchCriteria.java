package edu.psu.se411.lab11_inventory.model;

public interface SearchCriteria<T> {
    boolean matches(T item);
}
