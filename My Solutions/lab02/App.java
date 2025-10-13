package lab02;

import java.util.*;

public class App {
	// Exercise 3: Wildcards
	public static void printList(List<?> list) {
		for (Object item : list) {
			System.out.println(item);
		}
	}

	public static double sumNumbers(List<? extends Number> list) {
		double sum = 0;
		for (Number num : list) {
			sum += num.doubleValue();
		}
		return sum;
	}

	public static void main(String[] args) {
       
        System.out.println("Exercise 1 - PrintableList:");
        String[] stringArray = {"Ahmed", "Yasser", "Hazem"};
        PrintableList<String> printableList = new PrintableList<>(stringArray);
        printableList.printItems();

        System.out.println("\nExercise 2 - NumberBox:");
        NumberBox<Integer> intBox = new NumberBox<>();
        intBox.setItem(10);
        System.out.println("Integer value: " + intBox.getItem());
        System.out.println("Add 5: " + intBox.add(5));

        NumberBox<Double> doubleBox = new NumberBox<>();
        doubleBox.setItem(3.14);
        System.out.println("Double value: " + doubleBox.getItem());
        System.out.println("Add 2.80: " + doubleBox.add(2.80));

        System.out.println("\nExercise 3 - Wildcards:");
        List<String> stringList = Arrays.asList("One", "Two", "Three");
        printList(stringList);

        List<Number> numbers = Arrays.asList(1, 2.5, 3, 4.5);
        System.out.println("Sum of numbers: " + sumNumbers(numbers));
    }
}