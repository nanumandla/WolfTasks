package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Test NotebookWriter class
 */
class NotebookWriterTest {
	/**Represents a list of task lists sorted by their names. */
	private ISortedList<TaskList> taskLists;
	/**Represents a task list containing tasks. */
	private TaskList taskList1;
	/**Represents a task with a name, description, and status. */
	private Task task1;
	/**Represents another task with a name, description, and status. */
	private Task task2;

	/**
	 * Tests the writeNotebookFile method
	 */
	@Test
	void testWriteNotebookFile() {
		taskLists = new SortedList<>();
		taskList1 = new TaskList("Task List 1", 0);
		task1 = new Task("Task 1", "Description 1", true, true);
		task2 = new Task("Task 2", "Description 2", false, true);
		taskList1.addTask(task1);
		taskList1.addTask(task2);
		taskLists.add(taskList1);

		File file = new File("test-files/actualTasks.txt");

		try {
			NotebookWriter.writeNotebookFile(file, "My Notebook", taskLists);
		} catch (Exception e) {
			e = assertThrows(IllegalArgumentException.class, () -> {
			});
			assertEquals("Unable to save to file.", e.getMessage());
		}
	}
	
	/**
	 * Tests the constructor of the Notebook writer
	 */
	@Test
	void testConstructor() {
		NotebookWriter writer = new NotebookWriter();
		assertEquals(writer.getClass(), new NotebookWriter().getClass());
		
	}
}
