package edu.psu.se411.lab11_inventory.model;

public class Item {
    private int id;
    private String name;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%-20s | ID: %-5d | Name: %s", 
                            "Item", id, name);
    }
}
