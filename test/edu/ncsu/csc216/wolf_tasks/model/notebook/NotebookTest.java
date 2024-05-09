package edu.ncsu.csc216.wolf_tasks.model.notebook;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Test the Notebook class
 * 
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
class NotebookTest {

	/** The notebook to be worked on */
	private Notebook notebook;
	/** A TaskList to test the notebook with */
	private TaskList taskList1;
	/** A TaskList to test the notebook with */
	private TaskList taskList2;
	/** A Task to test the notebook with */
	private Task task1;
	/** A Task to test the notebook with */
	private Task task2;
	/** A Task to test the notebook with */
	private Task task3;
	/** A Task to test the notebook with */
	private Task task4;

	/**
	 * Sets up all private field before testing
	 */
	@BeforeEach
	void setUp() {
		notebook = new Notebook("My Notebook");
		taskList1 = new TaskList("Task List 1", 0);
		taskList2 = new TaskList("Task List 2", 7);

		task1 = new Task("Task 1", "This is a test", false, true);
		task2 = new Task("Task 2", "This is a test", true, true);
		task3 = new Task("Task 3", "This is a test", true, false);
		task4 = new Task("Task 4", "This is a test", false, false);

	}

	/**
	 * Test that the construction has the correct fields
	 */
	@Test
	void testNotebook() {

		assertEquals("My Notebook", notebook.getNotebookName());
		assertEquals("Active Tasks", notebook.getCurrentTaskList().getTaskListName());
		assertTrue(notebook.isChanged());
		assertEquals(notebook.getTaskListsNames().length, 1);

		Exception e = assertThrows(IllegalArgumentException.class, () -> new Notebook(null));
		assertEquals("Invalid name.", e.getMessage());

	}

	/**
	 * Test that the notebook will be saved
	 */
	@Test
	void testSaveNotebook() {
		notebook = new Notebook("Save Notebook");

		// Set up a file to save the notebook to
		File file = new File("save_notebook.txt");

		// Save the notebook to the file
		notebook.saveNotebook(file);

		// Assert that the notebook is no longer marked as changed after saving
		assertFalse(notebook.isChanged());
	}

	/**
	 * Tests the setChanged method
	 */
	@Test
	void testSetChanged() {
		assertTrue(notebook.isChanged());

		notebook.setChanged(false);
		assertFalse(notebook.isChanged());
	}

	/**
	 * Test the invalid cases for Notebook
	 */
	@Test
	void testInvalidNotebookNames() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Notebook(""));
		assertEquals("Invalid name.", e.getMessage());

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Notebook("Active Tasks"));
		assertEquals("Invalid name.", e1.getMessage());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Notebook(null));
		assertEquals("Invalid name.", e2.getMessage());
	}

	/**
	 * Test the addTaskList method
	 */
	@Test
	void testAddTaskList() {
		notebook.addTaskList(taskList1);
		notebook.addTaskList(taskList2);

		String[] expected = { "Active Tasks", "Task List 1", "Task List 2" };

		assertEquals(3, notebook.getTaskListsNames().length);
		assertEquals(expected[0], notebook.getTaskListsNames()[0]);
		assertEquals(expected[1], notebook.getTaskListsNames()[1]);
		assertEquals(expected[2], notebook.getTaskListsNames()[2]);

		TaskList activeN = new TaskList("Active Tasks", 0);
		TaskList doubleN = new TaskList("Task List 2", 0);

		Exception e = assertThrows(IllegalArgumentException.class, () -> notebook.addTaskList(activeN));
		assertEquals("Invalid name.", e.getMessage());

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> notebook.addTaskList(doubleN));
		assertEquals("Invalid name.", e1.getMessage());

	}

	/**
	 * Test setCurrentTaskList method
	 */
	@Test
	void testSetCurrentTaskList() {
		notebook.addTaskList(taskList1);
		notebook.addTaskList(taskList2);
		assertEquals(notebook.getTaskListsNames().length, 3);

		assertEquals("Task List 2", notebook.getCurrentTaskList().getTaskListName());
		notebook.setCurrentTaskList("Active Tasks");

		assertEquals("Active Tasks", notebook.getCurrentTaskList().getTaskListName());
		notebook.setCurrentTaskList("Doesn't Exist");

		assertEquals("Active Tasks", notebook.getCurrentTaskList().getTaskListName());
		notebook.setCurrentTaskList("Task List 2");

		assertEquals("Task List 2", notebook.getCurrentTaskList().getTaskListName());

	}

	/**
	 * Test the editTaskList method
	 */
	@Test
	void testEditTaskList() {
		notebook.addTaskList(taskList1);
		notebook.addTaskList(taskList2);
		assertEquals(notebook.getTaskListsNames().length, 3);

		notebook.setCurrentTaskList("Active Tasks");

		Exception e = assertThrows(IllegalArgumentException.class, () -> notebook.editTaskList("Task List"));
		assertEquals("The Active Tasks list may not be edited.", e.getMessage());

		notebook.setCurrentTaskList("Task List 2");

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> notebook.editTaskList("Task List 2"));
		assertEquals("Invalid name.", e1.getMessage());

		notebook.editTaskList("IT WORKS");
		assertEquals("IT WORKS", notebook.getCurrentTaskList().getTaskListName());

	}

	/**
	 * Test the removeTaskList method
	 */
	@Test
	void testRemoveTaskList() {
		notebook.addTaskList(taskList1);
		notebook.addTaskList(taskList2);
		assertEquals(notebook.getTaskListsNames().length, 3);

		notebook.setCurrentTaskList("Active Tasks");

		Exception e = assertThrows(IllegalArgumentException.class, () -> notebook.removeTaskList());
		assertEquals("The Active Tasks list may not be deleted.", e.getMessage());

		assertEquals(3, notebook.getTaskListsNames().length);
		notebook.setCurrentTaskList("Task List 2");

		notebook.removeTaskList();
		assertEquals(2, notebook.getTaskListsNames().length);

		assertEquals("Active Tasks", notebook.getCurrentTaskList().getTaskListName());
		assertTrue(notebook.isChanged());

	}

	/**
	 * Test the addTask method
	 */
	@Test
	void testAddTask() {
		notebook.addTaskList(taskList1);
		notebook.addTaskList(taskList2);
		assertEquals(notebook.getTaskListsNames().length, 3);

		notebook.addTask(task1);
		notebook.addTask(task2);
		notebook.addTask(task3);
		notebook.addTask(task4);

		assertEquals(4, notebook.getCurrentTaskList().getTasks().size());
		notebook.setCurrentTaskList("Active Tasks");

		assertEquals(2, notebook.getCurrentTaskList().getTasks().size());

	}

	/**
	 * Tests the editTask method
	 */
	@Test
	void testEditTask() {
		notebook.addTaskList(taskList1);
		notebook.addTaskList(taskList2);
		assertEquals(notebook.getTaskListsNames().length, 3);

		notebook.addTask(task1);
		notebook.addTask(task2);
		notebook.addTask(task3);
		notebook.addTask(task4);

		assertEquals(4, notebook.getCurrentTaskList().getTasks().size());

		assertEquals("Task 1", notebook.getCurrentTaskList().getTask(0).getTaskName());
		assertEquals("This is a test", notebook.getCurrentTaskList().getTask(0).getTaskDescription());
		assertFalse(notebook.getCurrentTaskList().getTask(0).isRecurring());
		assertTrue(notebook.getCurrentTaskList().getTask(0).isActive());

		notebook.editTask(0, "NEW TASK 1", "HI", true, false);

		assertEquals("NEW TASK 1", notebook.getCurrentTaskList().getTask(0).getTaskName());
		assertEquals("HI", notebook.getCurrentTaskList().getTask(0).getTaskDescription());
		assertTrue(notebook.getCurrentTaskList().getTask(0).isRecurring());
		assertFalse(notebook.getCurrentTaskList().getTask(0).isActive());

		notebook.editTask(0, "NEW TASK 1", "HI", true, true);

		assertTrue(notebook.getCurrentTaskList().getTask(0).isActive());

		notebook.setCurrentTaskList("Active Tasks");
		assertEquals(3, notebook.getTaskListsNames().length);
		assertEquals("NEW TASK 1", notebook.getCurrentTaskList().getTask(0).getTaskName());

	}

}
