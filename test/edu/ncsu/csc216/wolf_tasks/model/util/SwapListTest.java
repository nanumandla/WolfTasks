package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the SwapList class
 * 
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
class SwapListTest {
	/** A SwapList of String */
	private SwapList<String> list;

	/**
	 * Test SwapList Constructor
	 */

	@BeforeEach
	public void setup() {
		list = new SwapList<String>();
	}
	
	@Test
	void testErrorCheck() {
        Exception e = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals("Cannot add null element.", e.getMessage());
	}
	/**
	 * Tests the get method
	 */
	@Test
	void testGet() {
		list.add("Task 1");
        list.add("Task 2");
        list.add("Task 3");
        assertEquals("Task 1", list.get(0));
        assertEquals("Task 2", list.get(1));
        assertEquals("Task 3", list.get(2));
	}

	/**
	 * Tests the size method
	 */
	@Test
	void testSize() {
		list.add("Task 1");
        list.add("Task 2");
        list.add("Task 3");
        assertEquals(3, list.size());
	}

	/**
	 * Test adding to SwapList
	 */
	@Test
	void testAdd() {

		//Add 1 task
		assertEquals(0, list.size());
		list.add("Task 1");
		assertEquals(1, list.size());
		assertEquals("Task 1", list.get(0));
		
		//Add multiple task
		list.add("Task 2");
		list.add("Task 3");
		list.add("Task 4");
		assertEquals(4, list.size());
		assertEquals("Task 3", list.get(2));
	}
	

	/**
	 * Test removing from swaplist
	 */
	@Test
	void testRemove() {
		list.add("Task 1");
        list.add("Task 2");
        list.add("Task 3");
        assertEquals(3, list.size());
        
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals("Task 1", list.get(0));
        assertEquals("Task 3", list.get(1));
        
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-1));
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(10));
	}

	/**
	 * Moving the priority of an object up
	 */
	@Test
	void testMoveUp() {
		list.add("Task 1");
        list.add("Task 2");
        list.add("Task 3");
        list.moveUp(2);
        assertEquals("Task 1", list.get(0));
        assertEquals("Task 3", list.get(1));
        assertEquals("Task 2", list.get(2));
	}

	/**
	 * Moving the priority of an object down
	 */
	@Test
	void testMoveDown() {
		list.add("Task 1");
        list.add("Task 2");
        list.add("Task 3");
        list.moveDown(1);
        assertEquals("Task 1", list.get(0));
        assertEquals("Task 3", list.get(1));
        assertEquals("Task 2", list.get(2));
	}

	/**
	 * Moving the priority of an object to the front
	 */
	@Test
	void testMoveToFront() {
		list.add("Task 1");
        list.add("Task 2");
        list.add("Task 3");
        list.moveToFront(2);
        assertEquals("Task 3", list.get(0));
        assertEquals("Task 1", list.get(1));
        assertEquals("Task 2", list.get(2));
	}

	/**
	 * Moving the priority of an object to the back
	 */
	@Test
	void testMoveToBack() {
		list.add("Task 1");
        list.add("Task 2");
        list.add("Task 3");
        list.moveToBack(0);
        assertEquals("Task 2", list.get(0));
        assertEquals("Task 3", list.get(1));
        assertEquals("Task 1", list.get(2));
	}
	
	@Test
	void testCheckCapacity() {
		list.add("Task 1");
        list.add("Task 2");
        list.add("Task 3");
		list.add("Task 11");
        list.add("Task 21");
        list.add("Task 31");
		list.add("Task 12");
        list.add("Task 22");
        list.add("Task 32");
		list.add("Task 13");
        list.add("Task 23");
        list.add("Task 33");
        
        assertEquals(12, list.size());
	}
	

}
