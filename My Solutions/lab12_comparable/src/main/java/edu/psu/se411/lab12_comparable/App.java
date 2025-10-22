package edu.psu.se411.lab12_comparable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_comparable.model.Item;
import edu.psu.se411.lab12_comparable.model.Order;

public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		// Create a list of orders
		List<Order> orders = new ArrayList<>();
		orders.add(new Order("A102", "Salah", LocalDate.of(2025, 2, 10), new Item[] {
				new Item("I002", "Screen", "A 43 inch screen"),
				new Item("I003", "Mouse", "An optical mouse")
		}));
		orders.add(new Order("A101", "Ayman", LocalDate.of(2025, 2, 8), new Item[] {
				new Item("I007", "Keyboard", "A gaming keyboard"),
				new Item("I009", "Mouse", "An optical mouse")
		}));
		orders.add(new Order("A103", "Ramzi", LocalDate.of(2025, 1, 22), new Item[] {
				new Item("I034", "Pen", "A blue pen"),
				new Item("I056", "Printer", "A laser printer")
		}));
		orders.add(new Order("A104", "Sarah", LocalDate.of(2025, 2, 8), new Item[] {
				new Item("I011", "Laptop", "A gaming laptop")
		}));
		
		// Print orders before sorting
		System.out.println("===== Orders Before Sorting =====");
		for (Order order : orders) {
			System.out.println(order);
		}
		
		// Sort orders using Collections.sort()
		Collections.sort(orders);
		
		// Print orders after sorting
		System.out.println("\n===== Orders After Sorting (by delivery date, then by order ID) =====");
		for (Order order : orders) {
			System.out.println(order);
		}
		
		// Clone an order
		try {
			System.out.println("\n===== Cloning Demonstration =====");
			Order originalOrder = orders.get(0);
			System.out.println("Original Order: " + originalOrder);
			
			// Clone the order and assign a new order ID
			Order clonedOrder = originalOrder.clone();
			clonedOrder.setOrderId(UUID.randomUUID().toString());
			
			System.out.println("\nCloned Order (with new ID): " + clonedOrder);
			
			// Verify deep copy by modifying the cloned order
			System.out.println("\n===== Verifying Deep Copy =====");
			clonedOrder.setCustomerName("Modified Customer");
			
			// Modify items in the cloned order to verify deep copy
			if (clonedOrder.getOrderedItems() != null && clonedOrder.getOrderedItems().length > 0) {
				clonedOrder.getOrderedItems()[0].setName("MODIFIED ITEM");
				clonedOrder.getOrderedItems()[0].setDescription("This item was changed in cloned order");
			}
			
			System.out.println("After modifying cloned order's customer name and first item:");
			System.out.println("Original Order: " + originalOrder);
			System.out.println("Cloned Order: " + clonedOrder);
			System.out.println("\nNotice: Original order items remain unchanged - proving deep copy works!");
			
		} catch (CloneNotSupportedException e) {
			logger.error("Error cloning order", e);
			System.err.println("Error cloning order: " + e.getMessage());
		}

		logger.info("Application is closing...");
	}

}
