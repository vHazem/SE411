package lab02;

import java.util.*;

//Exercise 1: Generic PrintableList
class PrintableList<T> {
	private List<T> items;

	public PrintableList(T... array) {
		this.items = Arrays.asList(array);
	}

	public void printItems() {
		for (T item : items) {
			System.out.println(item);
		}
	}
}
