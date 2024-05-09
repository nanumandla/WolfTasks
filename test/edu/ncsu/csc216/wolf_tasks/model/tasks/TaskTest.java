package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Task class
 * 
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
class TaskTest {

	/**
	 * Tests the construction of a Task
	 */
	@Test
	void testTask() {
		Task t = new Task("Task 1", "This is a test", false, true);
		assertEquals("Task 1", t.getTaskName());
		assertEquals("This is a test", t.getTaskDescription());
		assertFalse(t.isRecurring());
		assertTrue(t.isActive());
		assertEquals("* Task 1,active\nThis is a test", t.toString());
		
	}

	/**
	 * Test Invalid Task.setTaskName method
	 */
	@Test
	void testSetTaskName() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Task(null, "This is a test", false, false));
		assertEquals("Incomplete task information.", e.getMessage());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Task("", "This is a test", false, false));
		assertEquals("Incomplete task information.", e1.getMessage());
	}

	/**
	 * Test Task.setTaskDescription method
	 */
	@Test
	void testSetTaskDescription() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Task("Task 1", null, false, false));
		assertEquals("Incomplete task information.", e.getMessage());
	}

	/**
	 * Test Task.getTaskListName method and all exceptions for the method
	 */
	@Test
	void testGetTaskListName() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		Task t = new Task("Task 1", "This is a test", false, false);
		t.addTaskList(taskList);
		
		assertEquals("Task List 1", t.getTaskListName());
		
		Task t2 = new Task("Task 2", "This is a test", false, false);
		assertEquals("", t2.getTaskListName());
	}

	/**
	 * Test Task.addTaskList method and all exceptions for the method
	 */
	@Test
	void testAddTaskList() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		AbstractTaskList nullList = null;
		
		Task t = new Task("Task 1", "This is a test", false, false);
		
		t.addTaskList(taskList);
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> t.addTaskList(nullList));
		assertEquals("Incomplete task information.", e.getMessage());
		
		
	}

	/**
	 * Test Task.completeTask method
	 */
	@Test
	void testCompleteTask() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		Task t = new Task("Task 1", "This is a test", true, false);
		Task t1 = new Task("Task 2", "This is a test", true, true);
		Task t2 = new Task("Task 3", "This is a test", false, true);
		Task t3 = new Task("Task 4", "This is a test", false, false);


		
		taskList.addTask(t);
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);

		assertEquals(4, taskList.getTasks().size());
		t1.setActive(false);
		t1.completeTask();
		assertEquals(4, taskList.getTasks().size());
		
	}

	/**
	 * Test Task.clone method and tests that it can be cloned
	 */
	@Test
	void testClone() {			
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		
		Task t = new Task("Task 1", "This is a test", false, true);
		assertEquals("Task 1", t.getTaskName());
		assertEquals("This is a test", t.getTaskDescription());
		assertFalse(t.isRecurring());
		assertTrue(t.isActive());
		assertEquals("* Task 1,active\nThis is a test", t.toString());
		t.addTaskList(taskList);
		taskList.addTask(t);

		
		Task clone = null;
		
		try {
			clone = (Task) taskList.getTask(0).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		assertEquals("Task 1", clone.getTaskName());
		assertEquals("This is a test", clone.getTaskDescription());
		assertFalse(clone.isRecurring());
		assertTrue(clone.isActive());
		assertEquals("* Task 1,active\nThis is a test", clone.toString());
		
		Task t2 = new Task("Task 2", "This is a test", false, false);
		Exception e = assertThrows(CloneNotSupportedException.class, () -> t2.clone());
		assertEquals("Cannot clone.", e.getMessage());

	}

	/**
	 * Test toString method
	 */
	@Test
	void testToString() {
		Task t = new Task("Task 1", "This is a test", false, true);
		assertEquals("Task 1", t.getTaskName());
		assertEquals("This is a test", t.getTaskDescription());
		assertFalse(t.isRecurring());
		assertTrue(t.isActive());
		assertEquals("* Task 1,active\nThis is a test", t.toString());
		
		Task t1 = new Task("Task 2", "This is a test", true, true);
		assertEquals("Task 2", t1.getTaskName());
		assertEquals("This is a test", t1.getTaskDescription());
		assertTrue(t1.isRecurring());
		assertTrue(t1.isActive());
		assertEquals("* Task 2,recurring,active\nThis is a test", t1.toString());
		
		Task t2 = new Task("Task 3", "This is a test", true, false);
		assertEquals("Task 3", t2.getTaskName());
		assertEquals("This is a test", t2.getTaskDescription());
		assertTrue(t2.isRecurring());
		assertFalse(t2.isActive());
		assertEquals("* Task 3,recurring\nThis is a test", t2.toString());
		
		Task t3 = new Task("Task 4", "This is a test", false, false);
		assertEquals("Task 4", t3.getTaskName());
		assertEquals("This is a test", t3.getTaskDescription());
		assertFalse(t3.isRecurring());
		assertFalse(t3.isActive());
		assertEquals("* Task 4\nThis is a test", t3.toString());


	}

}
