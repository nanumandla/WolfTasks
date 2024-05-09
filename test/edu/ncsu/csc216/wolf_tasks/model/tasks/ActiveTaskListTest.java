package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the ActiveTaskList class
 * 
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
class ActiveTaskListTest {
	/** Active TaskList to work with */
	private ActiveTaskList activeTaskList;
	/** Task to be setup */
    private Task task1;
    /** Task to be setup */
    private Task task2;
    /** A Task to task test for exception */
    private Task errorTask;

    /**
     * Sets up all Task and ActiveTaskList
     */
    @BeforeEach
    public void setUp() {
        activeTaskList = new ActiveTaskList();
        task1 = new Task("Task 1", "Description 1", false, true);
        task2 = new Task("Task 2", "Description 2", true, true);
        errorTask = new Task("Task 1", "Description 1", false, false);
    }
    
    /**
     * Test the ActiveTaskList.setTaskListName
     */
    @Test
	void testSetTaskListName() {
    	activeTaskList.setTaskListName("Active Tasks");
        assertEquals("Active Tasks", activeTaskList.getTaskListName());
	}

    /**
     * Tests for exceptions to be thrown 
     */
    @Test
    void testErrorCheck() {
    	Exception e = assertThrows(IllegalArgumentException.class, () -> activeTaskList.addTask(errorTask));
		assertEquals("Cannot add task to Active Tasks.", e.getMessage());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> activeTaskList.setTaskListName("Error check"));
		assertEquals("The Active Tasks list may not be edited.", e1.getMessage());
    }
    
    /**
     * Test the addTask method to an ActiveTaskList
     */
	@Test
	void testAddTask() {
		activeTaskList.addTask(task1);
        assertEquals(1, activeTaskList.getTasks().size());
        assertEquals("Task 1", activeTaskList.getTask(0).getTaskName());
        assertEquals("Description 1", activeTaskList.getTask(0).getTaskDescription());
        assertFalse(activeTaskList.getTask(0).isRecurring());
        assertTrue(activeTaskList.getTask(0).isActive());
	}

	/**
	 * Tests the getTasksAsArray method
	 */
	@Test
	void testGetTasksAsArray() {
		activeTaskList.addTask(task1);
        String[][] tasksArray = activeTaskList.getTasksAsArray();
        assertEquals(1, tasksArray.length);
        assertEquals("Active Tasks", tasksArray[0][0]);
        assertEquals("Task 1", tasksArray[0][1]);
	}

	/**
	 * Tests the ActiveTaskList Constructor
	 */
	@Test
	void testActiveTaskList() {
		Task task = new Task("Task 1", "Test Description", true, true);
        assertEquals("Task 1", task.getTaskName());
        assertEquals("Test Description", task.getTaskDescription());
        assertTrue(task.isRecurring());
        assertTrue(task.isActive());
	}

	/**
	 * Tests the clearTasks method
	 */
	@Test
	void testClearTasks() {
		activeTaskList.addTask(task1);
        activeTaskList.addTask(task2);
        assertEquals(2, activeTaskList.getTasks().size());
        activeTaskList.clearTasks();
        assertEquals(0, activeTaskList.getTasks().size());
	}

}
