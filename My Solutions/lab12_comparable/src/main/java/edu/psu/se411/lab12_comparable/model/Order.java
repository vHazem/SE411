package edu.psu.se411.lab12_comparable.model;

import java.time.LocalDate;
import java.util.Arrays;

public class Order implements Comparable<Order>, Cloneable {

	private String orderId;
	private String customerName;
	private LocalDate deliveryDate;
	private Item[] orderedItems;
    
	public Order(String orderId, String customerName, LocalDate deliveryDate, Item[] orderedItems) {
		this.orderId = orderId;
		this.customerName = customerName;
		this.deliveryDate = deliveryDate;
		this.orderedItems = orderedItems;
	}

	@Override
	public int compareTo(Order other) {
		// First, compare by delivery date (earliest first)
		int dateComparison = this.deliveryDate.compareTo(other.deliveryDate);
		
		// If delivery dates are the same, compare by order ID
		if (dateComparison == 0) {
			return this.orderId.compareTo(other.orderId);
		}
		
		return dateComparison;
	}
	
	@Override
	public Order clone() throws CloneNotSupportedException {
		// Create a deep copy of the order
		Order clonedOrder = (Order) super.clone();
		
		// Deep copy the orderedItems array - create new Item objects
		if (this.orderedItems != null) {
			clonedOrder.orderedItems = new Item[this.orderedItems.length];
			for (int i = 0; i < this.orderedItems.length; i++) {
				Item original = this.orderedItems[i];
				clonedOrder.orderedItems[i] = new Item(
					original.getId(),
					original.getName(),
					original.getDescription()
				);
			}
		}
		
		return clonedOrder;
	}
	
	@Override
  public String toString() {
		return "Order{" +
							"orderId='" + orderId + '\'' +
							", customerName='" + customerName + '\'' +
							", deliveryDate=" + deliveryDate + '\'' +
							", items=" + Arrays.toString(orderedItems) +
							'}';
  }
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Item[] getOrderedItems() {
		return orderedItems;
	}
	public void setOrderedItems(Item[] orderedItems) {
		this.orderedItems = orderedItems;
	}

}
