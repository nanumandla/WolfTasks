package edu.ncsu.csc216.wolf_tasks.model.notebook;

import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.io.NotebookWriter;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Represents a notebook that contains a collection of task lists. The notebook
 * can be saved to a file and tracks its current state for changes. It provides
 * methods to manage task lists and tasks within those lists.
 * 
 * @author An Mai, Nirvan Reddy Anumandla
 */
public class Notebook {

	/** The current task list */
	private AbstractTaskList currentTaskList;
	/** The active task list */
	private ActiveTaskList activeTaskList;
	/** The list of task lists */
	private ISortedList<TaskList> taskLists;
	/** The name of the notebook */
	private String notebookName;
	/** Boolean indicating whether changes have been made to the notebook or not */
	private boolean isChanged;

	/**
	 * Constructs a new Notebook with the given name.
	 * 
	 * @param name the name of the notebook
	 */
	public Notebook(String name) {
		this.taskLists = new SortedList<TaskList>();
		this.activeTaskList = new ActiveTaskList();
		this.currentTaskList = activeTaskList;
		this.isChanged = true;
		setNotebookName(name);
	}

	/**
	 * Saves the notebook to the specified file.
	 * 
	 * @param notebookFile the file name to save the notebook to
	 */
	public void saveNotebook(File notebookFile) {
		NotebookWriter.writeNotebookFile(notebookFile, getNotebookName(), taskLists);
		setChanged(false);
	}

	/**
	 * Retrieves the name of the notebook.
	 * 
	 * @return the name of the notebook
	 */
	public String getNotebookName() {
		return this.notebookName;
	}

	/**
	 * Sets the name of the notebook.
	 * 
	 * @param name the new name for the notebook
	 * @throws IllegalArgumentException if the name is invalid
	 */
	private void setNotebookName(String name) {
		if (name == null || "Active Tasks".equals(name) || "".equals(name) || "null".equals(name)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.notebookName = name;
	}

	/**
	 * Checks if the notebook has been changed.
	 * 
	 * @return true if changes have been made or else returns false
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Sets the changed status of the notebook.
	 * 
	 * @param changed true to indicate changes or else returns false
	 */
	public void setChanged(boolean changed) {
		this.isChanged = changed;
	}

	/**
	 * Adds a task list to the notebook.
	 * 
	 * @param taskList the task list to add
	 * @throws IllegalArgumentException for invalid names
	 */
	public void addTaskList(TaskList taskList) {
		if ("Active Tasks".equals(taskList.getTaskListName())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).getTaskListName().equals(taskList.getTaskListName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		taskLists.add(taskList);
		getActiveTaskList();
		currentTaskList = taskList;
		setChanged(true);
	}

	/**
	 * Retrieves the names of all task lists in the notebook.
	 * 
	 * @return an array of task list names
	 */
	public String[] getTaskListsNames() {
		String[] taskListsNames = new String[taskLists.size() + 1];
		if (taskListsNames.length != 0) {
			taskListsNames[0] = activeTaskList.getTaskListName();
			for (int i = 0; i < taskLists.size(); i++) {
				taskListsNames[i + 1] = taskLists.get(i).getTaskListName();
			}
		}
		return taskListsNames;
	}

	/**
	 * The Active Task List gets reconstructed each time a task is add, edited, or
	 * remove and will add any active tasks to the active task list.
	 */
	private void getActiveTaskList() {
		activeTaskList = new ActiveTaskList();
		for (int i = 0; i < taskLists.size(); i++) {
			TaskList taskList = taskLists.get(i);
			for (int j = 0; j < taskList.getTasks().size(); j++) {
				if (taskList.getTask(j).isActive()) {
					activeTaskList.addTask(taskList.getTask(j));
				}
			}
		}
	}

	/**
	 * Sets the current task list by name.
	 * 
	 * @param taskListName the name of the task list to set as current
	 */
	public void setCurrentTaskList(String taskListName) {

		currentTaskList = activeTaskList;

		for (int i = 0; i < taskLists.size(); i++) {
			TaskList taskList = taskLists.get(i);
			if (taskList.getTaskListName().equals(taskListName)) {
				currentTaskList = taskList;
				break;
			}
		}
	}

	/**
	 * Retrieves the current task list.
	 * 
	 * @return the current task list
	 */
	public AbstractTaskList getCurrentTaskList() {
		return currentTaskList;
	}

	/**
	 * Edits a task list with the given name.
	 * 
	 * @param taskListName the name of the task list to edit
	 * @throws IllegalArgumentException for an invalid taskListName string
	 */
	public void editTaskList(String taskListName) {
		if ("Active Tasks".equals(currentTaskList.getTaskListName())
				|| currentTaskList.getClass() == new ActiveTaskList().getClass()) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		if ("Active Tasks".equals(taskListName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for (int i = 0; i < taskLists.size(); i++) {
			TaskList taskList = taskLists.get(i);
			if (taskList.getTaskListName().equals(taskListName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}

		TaskList current = null;
		for (int i = 0; i < taskLists.size(); i++) {
			TaskList taskList = taskLists.get(i);
			if (taskList.getTaskListName().equals(currentTaskList.getTaskListName())) {
				current = taskLists.remove(i);
				break;
			}
		}

		if (current != null) {
			current.setTaskListName(taskListName);
			taskLists.add(current);
			setChanged(true);
		}

	}

	/**
	 * Removes the current task list from the notebook.
	 * 
	 * @throws IllegalArgumentException if the currentTaskList is an ActiveTaskList
	 */
	public void removeTaskList() {
		if (currentTaskList.getClass() == new ActiveTaskList().getClass()) {
			throw new IllegalArgumentException("The Active Tasks list may not be deleted.");
		}

		for (int i = 0; i < taskLists.size(); i++) {
			if (currentTaskList.getTaskListName().equals(taskLists.get(i).getTaskListName())) {
				taskLists.remove(i);
				break;
			}
		}
		currentTaskList = activeTaskList;
		setChanged(true);
	}

	/**
	 * Adds a task to the current task list.
	 * 
	 * @param t the task to add
	 */
	public void addTask(Task t) {
		if (currentTaskList.getClass() == new TaskList("Hi", 0).getClass()) {
			currentTaskList.addTask(t);
			if (t.isActive()) {
				getActiveTaskList();
			}
			setChanged(true);
		}
	}

	/**
	 * Edits a task within the current task list.
	 * 
	 * @param idx             the index of the task
	 * @param taskName        the name of task
	 * @param taskDescription the new description for the task
	 * @param recurring       true if the task is recurring or else returns false
	 * @param active          true if the task is active or else returns false
	 */
	public void editTask(int idx, String taskName, String taskDescription, boolean recurring, boolean active) {
		if (currentTaskList.getClass() == new TaskList("Hi", 0).getClass()) {
			currentTaskList.getTask(idx).setTaskName(taskName);
			currentTaskList.getTask(idx).setTaskDescription(taskDescription);
			currentTaskList.getTask(idx).setRecurring(recurring);
			currentTaskList.getTask(idx).setActive(active);
			getActiveTaskList();
			setChanged(true);
		}
	}
}
