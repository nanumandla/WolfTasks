package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;

/**
 * Test the AbstractTaskList class
 * 
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
class AbstractTaskListTest {

	/**
	 * Tests the construction of a new AbstractTaskList
	 */
	@Test
	void testAbstractTaskList() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		assertEquals("Task List 1", taskList.getTaskListName());
		assertEquals(0, taskList.getCompletedCount());
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> new TaskList("Task List 2", -9));
		assertEquals("Invalid completed count.", e.getMessage());
	}

	/**
	 * Tests the invalid names for setTaskListName
	 */
	@Test
	void testSetInvalidTaskListName() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new TaskList(null, 0));
		assertEquals("Invalid name.", e.getMessage());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new TaskList("", 0));
		assertEquals("Invalid name.", e1.getMessage());
	}

	/**
	 * Test the getTasks method
	 */
	@Test
	void testGetTasks() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		Task t1 = new Task("Task 1", "This is a test", false, false);
		Task t2 = new Task("Task 2", "This is a test", false, false);
		Task t3 = new Task("Task 3", "This is a test", false, false);
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);
		
		ISwapList<Task> list = taskList.getTasks();
		assertEquals(3, list.size());
		assertEquals("Task 1", list.get(0).getTaskName());
		assertEquals("Task 2", list.get(1).getTaskName());
		assertEquals("Task 3", list.get(2).getTaskName());
		

		

	}

	/**
	 * Tests the addTask method
	 */
	@Test
	void testAddTask() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		Task t1 = new Task("Task 1", "This is a test", false, false);
		Task t2 = new Task("Task 2", "This is a test", false, false);
		Task t3 = new Task("Task 3", "This is a test", false, false);
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);
		
		assertEquals(t1, taskList.getTask(0));
		assertEquals(t2, taskList.getTask(1));
		assertEquals(t3, taskList.getTask(2));
	}

	/**
	 * Test the removeTask method
	 */
	@Test
	void testRemoveTask() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		Task t1 = new Task("Task 1", "This is a test", false, false);
		Task t2 = new Task("Task 2", "This is a test", false, false);
		Task t3 = new Task("Task 3", "This is a test", false, false);
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);
		
		assertEquals(3, taskList.getTasks().size());
		assertEquals("Task 1", taskList.removeTask(0).getTaskName());
		
		assertEquals(2, taskList.getTasks().size());
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> taskList.removeTask(4));
		assertEquals("Invalid index.", e.getMessage());
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> taskList.removeTask(-4));
		assertEquals("Invalid index.", e1.getMessage());
	}

	/**
	 * Test the getTask method
	 */
	@Test
	void testGetTask() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		Task t1 = new Task("Task 1", "This is a test", false, false);
		Task t2 = new Task("Task 2", "This is a test", false, false);
		Task t3 = new Task("Task 3", "This is a test", false, false);
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);
		
		assertEquals("Task 3", taskList.getTask(2).getTaskName());
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(4));
		assertEquals("Invalid index.", e.getMessage());
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(-4));
		assertEquals("Invalid index.", e1.getMessage());
	}

	/**
	 * Test the completeTask method
	 */
	@Test
	void testCompleteTask() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		Task t1 = new Task("Task 1", "This is a test", false, false);
		Task t2 = new Task("Task 2", "This is a test", false, false);
		Task t3 = new Task("Task 3", "This is a test", false, false);
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);
		
		taskList.completeTask(t3);
		assertEquals(1, taskList.getCompletedCount());
		assertEquals(2, taskList.getTasks().size());
		
	}

	/**
	 * Test the getTasksAsArray method
	 */
	@Test
	void testGetTasksAsArray() {
		AbstractTaskList taskList = new TaskList("Task List 1", 0);
		Task t1 = new Task("Task 1", "This is a test", false, false);
		Task t2 = new Task("Task 2", "This is a test", false, false);
		Task t3 = new Task("Task 3", "This is a test", false, false);
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);
		
		String[][] tasksArray = taskList.getTasksAsArray();
		assertEquals("1", tasksArray[0][0]);
		assertEquals("Task 1", tasksArray[0][1]);
		
		assertEquals("2", tasksArray[1][0]);
		assertEquals("Task 2", tasksArray[1][1]);
		
		assertEquals("3", tasksArray[2][0]);
		assertEquals("Task 3", tasksArray[2][1]);
	}

}
