package edu.psu.se411.lab11_inventory.model;

public class ElectronicDevice extends Item {
    private DeviceCategory category;

    public ElectronicDevice(int id, String name, DeviceCategory category) {
        super(id, name);
        this.category = category;
    }

    public DeviceCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        String categoryName = category.toString().charAt(0) + 
                            category.toString().substring(1).toLowerCase();
        return String.format("%-20s | ID: %-5d | Name: %-35s | Category: %s", 
                            "Electronic Device", getId(), getName(), categoryName);
    }
}
