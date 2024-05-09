package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Class reads information from a file that contains a notebook of tasklists
 * 
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
public class NotebookReader {
	/**
	 * Reads the information from a file and recreates a notebook
	 * 
	 * @param file to be read
	 * @return a notebook
	 */
	public static Notebook readNotebookFile(File file) {
		Scanner read;
		Notebook notebook;
		String notebookList = "";
		try {
			read = new Scanner(new FileInputStream(file));
			String notebookName = read.nextLine();

			if (!"!".equals(notebookName.substring(0, 1))) {
				read.close();
				throw new IllegalArgumentException("Unable to load file.");
			}

			// Used to get the notebook name without any special characters and whitespaces
			notebookName = notebookName.substring(notebookName.indexOf("!") + 1).trim();
			notebook = new Notebook(notebookName);

			// Reads in everyline of the notebook besides the title
			while (read.hasNextLine()) {
				notebookList += read.nextLine() + "\n";
			}

			// Splits the notebook into task lists which will be used to be processed
			String[] taskLists = notebookList.split("\\r?\\n?[#]");
			for (int i = 1; i < taskLists.length; i++) {
				try {
					// Processes the strings of task lists and adds it into the notebook
					TaskList tasklist = processTaskList(taskLists[i]);
					notebook.addTaskList(tasklist);
				} catch(Exception e) {
					//Do nothing
				}
			}

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File cannot be found.");
		}

		notebook.setCurrentTaskList("Active Tasks");
		read.close();
		return notebook;
	}

	/**
	 * Process the task lists that are within the notebook
	 * 
	 * @param tasklist process each tasklist
	 * @return a TaskList that will be broken down again
	 */
	private static TaskList processTaskList(String tasklist) {
		Scanner read = new Scanner(tasklist);
		String infoLine = read.nextLine();
		// The first line will be used for the task list name and the amount of completed tasks
		String[] taskListNameLine = infoLine.split(",");
		String taskLines = "";
		TaskList taskList;
		
		// Gathers information for the task list name
		String taskListName = taskListNameLine[0].trim();
		int completeCount = Integer.parseInt(taskListNameLine[1].trim());


		taskList = new TaskList(taskListName, completeCount);

		// Reads every line which are the tasks and their information to be processed
		while (read.hasNextLine()) {
			taskLines += read.nextLine() + "\n";
		}
		String[] tasks = taskLines.split("\\r?\\n?[*]");
		for (int i = 1; i < tasks.length; i++) {
			try {
				taskList.addTask(processTask(taskList, tasks[i]));
			} catch (IllegalArgumentException e) {
				continue;
			}
		}
		read.close();
		return taskList;
	}

	/**
	 * Process each task within a task list and adds it into the task list
	 * 
	 * @param tasklist where the task will be added
	 * @param info of the task i.e. name, description, etc.
	 * @return the task that will be added.
	 */
	private static Task processTask(AbstractTaskList tasklist, String info) {
		String taskName = "";
		String taskDescription = "";
		boolean recurring = false;
		boolean active = false;

		try {
			Scanner read = new Scanner(info);
			// Gets a string array of the task name
			String[] taskNameLine = read.nextLine().split(",");

			taskName = taskNameLine[0].trim();
			// If the String array is more than 0, then it contains more info such as if the task is active or recurring
			try {
				if (taskNameLine.length != 0) {
					for (int i = 1; i < taskNameLine.length; i++) {
						if ("active".equals(taskNameLine[i])) {
							active = true;
						}
						if ("recurring".equals(taskNameLine[i])) {
							recurring = true;
						}
					}
				}
			} catch (IndexOutOfBoundsException e) {
				// Do Nothing
			}

			// Everything else from the task will be its description
			while (read.hasNextLine()) {
				taskDescription += read.nextLine().trim() + "\n";
			}
			taskDescription = taskDescription.trim();
			read.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("It was here.");
		}

		Task task = new Task(taskName, taskDescription, recurring, active);
		task.addTaskList(tasklist);

		return task;
	}
}
