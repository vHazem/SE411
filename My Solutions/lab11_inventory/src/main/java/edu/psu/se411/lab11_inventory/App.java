package edu.psu.se411.lab11_inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_inventory.model.Book;
import edu.psu.se411.lab11_inventory.model.DeviceCategory;
import edu.psu.se411.lab11_inventory.model.ElectronicDevice;
import edu.psu.se411.lab11_inventory.model.Inventory;
import edu.psu.se411.lab11_inventory.model.Item;
import edu.psu.se411.lab11_inventory.model.SearchById;
import edu.psu.se411.lab11_inventory.model.SearchByName;

public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		try {
			// Create an inventory to hold items
			Inventory<Item> inventory = new Inventory<>();
			
			// Add some books to the inventory
			System.out.println("Adding items to inventory...\n");
			inventory.addItem(new Book(1, "Concurrency in Java", "Doug Lea"));
			inventory.addItem(new Book(2, "Effective Java", "Joshua Bloch"));
			inventory.addItem(new Book(3, "Clean Code", "Robert Martin"));
			
			// Add some electronic devices to the inventory
			inventory.addItem(new ElectronicDevice(101, "Laptop", DeviceCategory.LAPTOP));
			inventory.addItem(new ElectronicDevice(102, "Smartphone", DeviceCategory.SMARTPHONE));
			inventory.addItem(new ElectronicDevice(103, "Tablet", DeviceCategory.TABLET));
			
			// Display the entire inventory
			System.out.println("===== Current Inventory =====");
			displayInventory(inventory);
			System.out.println();
			
			// Search for a book by name
			System.out.println("Searching for book: 'Concurrency in Java'");
			Item foundBook = inventory.findItem(new SearchByName("Concurrency in Java"));
			if (foundBook != null) {
				System.out.println("Found: " + foundBook);
			} else {
				System.out.println("Book not found");
			}
			System.out.println();
			
			// Search for a device by name
			System.out.println("Searching for device: 'Laptop'");
			Item foundDevice = inventory.findItem(new SearchByName("Laptop"));
			if (foundDevice != null) {
				System.out.println("Found: " + foundDevice);
			} else {
				System.out.println("Device not found");
			}
			System.out.println();
			
			// Search by ID
			System.out.println("Searching for item with ID: 2");
			Item foundById = inventory.findItem(new SearchById(2));
			if (foundById != null) {
				System.out.println("Found: " + foundById);
			} else {
				System.out.println("Item not found");
			}
			System.out.println();
			
			// Remove an item
			System.out.println("Removing item with ID 103...");
			Item itemToRemove = inventory.findItem(new SearchById(103));
			if (itemToRemove != null) {
				inventory.removeItem(itemToRemove);
				System.out.println("Item removed successfully");
			}
			System.out.println();
			
			// Display inventory after removal
			System.out.println("===== Inventory After Removal =====");
			displayInventory(inventory);
			
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage(), e);
		}
		
		logger.info("Application is closing...");
	}
	
	// Method to display any inventory regardless of type
	public static void displayInventory(Inventory<?> inventory) {
		if (inventory.size() == 0) {
			System.out.println("Inventory is empty");
			return;
		}
		
		for (Object item : inventory.getAllItems()) {
			System.out.println(item);
		}
	}

}
