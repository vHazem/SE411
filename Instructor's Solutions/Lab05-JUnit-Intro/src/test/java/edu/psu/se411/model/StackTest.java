package edu.psu.se411.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class StackTest {

	private Stack<String> stringStack = new Stack<String>(20);
	
	@Test
	public void testInsertion() {
		stringStack.push("Salah");
		stringStack.push("Mounir");
		assertEquals("Mounir", stringStack.pop());  // try to change this to assertEqual("anything", stringStack.pop()) and re-run tests
	}
	
	@Test
	public void pop_empty_stack() {
		NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
		           () -> stringStack.pop(),   // try to change this to .push("anything" and re-run tests)
		           "Expected pop from empty Stack to throw, but it didn't"
		    );

		    assertTrue(thrown.getMessage().equals("Stack is empty, cannot pop"));
	}
	
	@Test
	public void testOrderLogic() {
		String[] elements = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		for(String e: elements) {
			stringStack.push(e);
		}
		
		String result = "";
		for(int i = 0; i < elements.length; i++) {
			result += stringStack.pop();
		}
		
		assertEquals("IHGFEDCBA", result); // try changing the expected result and re-run tests
	}
	
}
