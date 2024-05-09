package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;

/**
 * Tests NotebookReader class
 */
class NotebookReaderTest {
	/** Represents a valid test file */
	private File validTestFile;

	/** Represents a invalid test file */
	private File errorCheck;

	/** Represents a non-existing test file */
	private File nonExsisting;

	/** Represents a valid test file location */
	private String testFile = "test-files/notebook1.txt";

	/** Represents a invalid test file location */
	private String fileCheck = "test-files/notebook3.txt";

	/** Represents a non-existing test file location */
	private String fileCheck1 = "test-files/random.txt";

	/**
	 * Sets up the test environment before each test method is run.
	 */
	@BeforeEach
	void setUp() {
		validTestFile = new File(testFile);
		errorCheck = new File(fileCheck);
		nonExsisting = new File(fileCheck1);
	}

	/**
	 * tests NotebookReader to read a valid notebook file.
	 */
	@Test
	void testNotebookReader() {
		// Notebook
		Notebook notebook = NotebookReader.readNotebookFile(validTestFile);

		assertEquals("School", notebook.getNotebookName());

		// Task lists in Notebook
		assertEquals(4, notebook.getTaskListsNames().length);
		assertEquals("Active Tasks", notebook.getTaskListsNames()[0]);
		assertEquals("CSC 216", notebook.getTaskListsNames()[1]);
		assertEquals("CSC 226", notebook.getTaskListsNames()[2]);
		assertEquals("Habits", notebook.getTaskListsNames()[3]);

		assertEquals(5, notebook.getCurrentTaskList().getTasks().size());
		notebook.setCurrentTaskList("Habits");

		assertEquals(2, notebook.getCurrentTaskList().getTasks().size());
		assertEquals("Exercise", notebook.getCurrentTaskList().getTask(0).getTaskName());
		assertTrue(notebook.getCurrentTaskList().getTask(0).isActive());
		assertTrue(notebook.getCurrentTaskList().getTask(0).isRecurring());
		assertEquals("Exercise every day.\nAlternate between cardio and weight training",
				notebook.getCurrentTaskList().getTask(0).getTaskDescription());

		// Tasks in CSC 216 task list
		notebook.setCurrentTaskList("CSC 216");
		assertEquals(9, notebook.getCurrentTaskList().getTasks().size());
		Task task1 = notebook.getCurrentTaskList().getTask(0);
		assertEquals("Read Project 2 Requirements", task1.getTaskName());
		assertEquals(
				"Read Project 2 requirements\n(https://pages.github.ncsu.edu/engr-csc216-staff/CSC216-SE-Materials/projects/project2/project2-part1.html)\nand identify candidate classes and methods.",
				task1.getTaskDescription());
		assertFalse(task1.isRecurring());
		assertFalse(task1.isActive());

		Task task2 = notebook.getCurrentTaskList().getTask(1);
		assertEquals("Create CRC Cards", task2.getTaskName());
		assertEquals(
				"Identify the key classes and create CRC cards. Note\nresponsibilities, collaborators, and possible state.",
				task2.getTaskDescription());
		assertFalse(task2.isRecurring());
		assertTrue(task2.isActive());

		Task task3 = notebook.getCurrentTaskList().getTask(2);
		assertEquals("Transfer CRC Cards to UMLetino", task3.getTaskName());
		assertEquals("Start creating a UML class diagram from the requirements", task3.getTaskDescription());
		assertFalse(task3.isRecurring());
		assertFalse(task3.isActive());

		Task task4 = notebook.getCurrentTaskList().getTask(3);
		assertEquals("Download design proposal and rational template", task4.getTaskName());
		assertEquals(
				"See (https://pages.github.ncsu.edu/engr-csc216-staff/CSC216-SE-Materials/projects/project2/project2-part1.html)\nfor template link",
				task4.getTaskDescription());
		assertFalse(task4.isRecurring());
		assertFalse(task4.isActive());

		Task task5 = notebook.getCurrentTaskList().getTask(4);
		assertEquals("Write design proposal and rationale", task5.getTaskName());
		assertEquals("Start with UML class diagram description. Incorporate feedback\nfrom Project 1.",
				task5.getTaskDescription());
		assertFalse(task5.isRecurring());
		assertFalse(task5.isActive());

		// Skipping the other tasks in the CSC 226 task list...

		notebook.setCurrentTaskList("CSC 226");
		assertEquals(5, notebook.getCurrentTaskList().getTasks().size());
		Task task6 = notebook.getCurrentTaskList().getTask(0);
		assertEquals("Homework 7", task6.getTaskName());
		assertEquals(
				"- Review the assignment" + "\n" + "- Schedule time to work on the assignment\nDon't forget to submit!",
				task6.getTaskDescription());
		assertFalse(task6.isRecurring());
		assertFalse(task6.isActive());

		Task task7 = notebook.getCurrentTaskList().getTask(1);
		assertEquals("Homework 8", task7.getTaskName());
		assertEquals(
				"- Review the assignment" + "\n" + "- Schedule time to work on the assignment\nDon't forget to submit!",
				task7.getTaskDescription());
		assertFalse(task7.isRecurring());
		assertFalse(task7.isActive());

	}

	/**
	 * Tests NotebookReader's behavior when attempting to read from an invalid file.
	 */
	@Test
	void testReadIssuesFromFileInvalidFile() {

		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			NotebookReader.readNotebookFile(errorCheck);
		});
		assertEquals("Unable to load file.", e.getMessage());

		// Ensure that attempting to read from a nonexistent file throws IllegalArgumentException
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
			NotebookReader.readNotebookFile(nonExsisting);
		});
		assertEquals("File cannot be found.", e1.getMessage());
	}
}
