package edu.psu.se411.lab11_inventory.model;

public class SearchByCategory implements SearchCriteria<ElectronicDevice> {
    private DeviceCategory category;

    public SearchByCategory(DeviceCategory category) {
        this.category = category;
    }

    @Override
    public boolean matches(ElectronicDevice device) {
        return device.getCategory() == category;
    }
}
