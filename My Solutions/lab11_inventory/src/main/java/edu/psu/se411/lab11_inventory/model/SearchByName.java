package edu.psu.se411.lab11_inventory.model;

public class SearchByName implements SearchCriteria<Item> {
    private String name;

    public SearchByName(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(Item item) {
        return item.getName().equalsIgnoreCase(name);
    }
}
