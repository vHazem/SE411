package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class StackTest {

	private Stack<String> stringStack = new Stack<>();

	@BeforeEach
	public void init() {
		stringStack = new Stack<>();
	}

	@Test
	public void testPushPushPop() {
		stringStack.push("Z");
		stringStack.push("A");
		assertEquals("A", stringStack.pop(), "Popped element should be 'A'");
	}

	@Test
	public void testPushPushPop_fail() {
		stringStack.push("Z");
		stringStack.push("A");
		// This is intentionally wrong to force the test to fail
		assertEquals("Z", stringStack.pop(), "This test should fail because popped element is 'A', not 'Z'");
	}

	@Test
	public void pop_empty_stack() {
	    NoSuchElementException thrown = assertThrows(
	        NoSuchElementException.class,
	        () -> stringStack.pop(),
	        "Expected pop from empty Stack to throw, but it didn't"
	    );

	    // Check the actual message your Stack throws
	    assertTrue(thrown.getMessage().equals("Stack is empty, cannot pop"));
	}


	@Test
	public void testPushPopOrder() {
		stringStack.push("X");
		stringStack.push("Y");
		stringStack.push("Z");

		assertEquals("Z", stringStack.pop());
		assertEquals("Y", stringStack.pop());
		assertEquals("X", stringStack.pop());
	}
}
