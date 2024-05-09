package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test the TaskList class
 * 
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
class TaskListTest {

	/**
	 * Test the construction of a new TaskList
	 */
	@Test
	void testTaskList() {
		TaskList taskList = new TaskList("Task List 1", 0);
		assertEquals("Task List 1", taskList.getTaskListName());
		assertEquals(0, taskList.getCompletedCount());
	}
	
	/**
	 * Test TaskList.getTasksAsArray method
	 */
	@Test
	void testGetTasksAsArray() {
		TaskList taskList = new TaskList("Task List 1", 0);
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

	/**
	 * Test the compareTo method
	 */
	@Test
	void testCompareTo() {
		TaskList taskList1 = new TaskList("Task List 1", 0);
		TaskList taskList2 = new TaskList("Task List 2", 0);
		TaskList taskList3 = new TaskList("Task List 3", 0);
		
		assertEquals(-1, taskList1.compareTo(taskList2));
		assertEquals(0, taskList1.compareTo(taskList1));
		assertEquals(2, taskList3.compareTo(taskList1));

	}

}
