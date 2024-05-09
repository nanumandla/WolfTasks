package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing SortedList class
 * 
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
class SortedListTest {
	
	/** A Sorted List of Strings */
	private SortedList<String> list;
	
	/**
	 * Sets up a new list with nothing in it before each test
	 */
	@BeforeEach
	void setUp() {
		list = new SortedList<String>();
	}
	
	/**
	 * Testing the construction of a new sorted list
	 */
	@Test
	void testSortedList() {
		SortedList<String> l = new SortedList<String>();
		assertEquals(0, l.size());
	}

	/**
	 * Testing the add method for a sorted list
	 */
	@Test
	void testAdd() {
		
		list.add("Hi");
		assertEquals("Hi", list.get(0));
		
		Exception e = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals("Cannot add null element.", e.getMessage());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> list.add("Hi"));
		assertEquals("Cannot add duplicate element.", e1.getMessage());
		
		list.add("Bye");
		assertEquals("Bye", list.get(0));
		assertEquals("Hi", list.get(1));
		
		list.add("Cool");
		assertEquals("Bye", list.get(0));
		assertEquals("Cool", list.get(1));
		assertEquals("Hi", list.get(2));
		
		assertEquals(3, list.size());


	}

	/**
	 * Testing the SortedList.remove
	 */
	@Test
	void testRemove() {
		list.add("Hi");
		list.add("Bye");
		list.add("Cool");
		assertEquals(3, list.size());
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-2));
		assertEquals("Invalid index.", e.getMessage());
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
		assertEquals("Invalid index.", e1.getMessage());
		
		assertEquals("Cool", list.remove(1));
		assertEquals(2, list.size());
		
		assertEquals("Bye", list.get(0));
		assertEquals("Hi", list.get(1));
		
		assertEquals("Bye", list.remove(0));
		assertEquals("Hi", list.get(0));


	}

	/**
	 * Testing if the list contains certain messages
	 */
	@Test
	void testContains() {
		list.add("Hi");
		list.add("Bye");
		list.add("Cool");
		assertEquals(3, list.size());
		
		assertTrue(list.contains("Hi"));
		assertFalse(list.contains("Hello"));
	}

}
