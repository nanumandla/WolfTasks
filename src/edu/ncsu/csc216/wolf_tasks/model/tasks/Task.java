package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * A Task object that contains information of a task to be done. Class contains
 * method to set tasks and modify them.
 * 
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
public class Task implements Cloneable {
	/** A swap list of abstract task list */
	private ISwapList<AbstractTaskList> taskLists;
	/** the name of the task */
	private String taskName;
	/** the description of the task */
	private String taskDescription;
	/** if the task is recurring then it is true and false otherwise */
	private boolean recurring;
	/** if a task is active then it is true and false otherwise */
	private boolean active;

	/**
	 * Constructor of the task that contains the name, description, recurring, and
	 * active
	 * 
	 * @param taskName        name of the task
	 * @param taskDescription details of the details
	 * @param recurring       recurring if the task occurs frequently
	 * @param active          active if the task is currently being worked on
	 */
	public Task(String taskName, String taskDescription, boolean recurring, boolean active) {
		setTaskName(taskName);
		setTaskDescription(taskDescription);
		setRecurring(recurring);
		setActive(active);
		taskLists = new SwapList<AbstractTaskList>();

	}

	/**
	 * Retrieves the name of the task
	 * 
	 * @return task name
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * Sets the name of the task
	 * 
	 * @param taskName of the task
	 * @throws IllegalArgumentException if the taskName is null or empty
	 */
	public void setTaskName(String taskName) {
		if (taskName == null || "".equals(taskName)) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskName = taskName;
	}

	/**
	 * Retrieves the description of the task
	 * 
	 * @return the task description
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * Sets the description for the task
	 * 
	 * @param taskDescription description of the task
	 * @throws IllegalArgumentException if the taskDescription is null
	 */
	public void setTaskDescription(String taskDescription) {
		if (taskDescription == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskDescription = taskDescription;
	}

	/**
	 * Retrieves if the task is recurring
	 * 
	 * @return if the task is recurring or not
	 */
	public boolean isRecurring() {
		return recurring;
	}

	/**
	 * Sets if the task is recurring
	 * 
	 * @param recurring of the task
	 */
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	/**
	 * If the task is currently being worked on, then the return should be true and
	 * false otherwise.
	 * 
	 * @return if the task is active or not
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets if the task is active
	 * 
	 * @param active of the task
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Retrieves the task list name
	 * 
	 * @return the name of the task list
	 */
	public String getTaskListName() {
		if (taskLists == null || taskLists.size() == 0) {
			return "";
		} else {
			return taskLists.get(0).getTaskListName();
		}
	}

	/**
	 * Adds task list to an existing list of task list
	 * 
	 * @param taskList that will be added to a list
	 * @throws IllegalArgumentException if the taskList is null
	 */
	public void addTaskList(AbstractTaskList taskList) {
		if (taskList == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		taskLists.add(taskList);
	}

	/**
	 * If a task is completed, then the counter for completed task is increased.
	 * 
	 * @throws IllegalArgumentException if the task cannot be cloned
	 */
	public void completeTask() {
		for (int i = 0; i < taskLists.size(); i++) {
			taskLists.get(i).completeTask(this);
		}
		if (isRecurring()) {
			try {
				Task clone = (Task) this.clone();
				for (int i = 0; i < taskLists.size(); i++) {
					taskLists.get(i).addTask(clone);
				}

			} catch (CloneNotSupportedException e) {
				throw new IllegalArgumentException("Cannot clone.");

			}
		}
	}

	/**
	 * Clones an existing task.
	 * 
	 * @throws CloneNotSupportedException if the taskList size is 0
	 * @return copy of the existing task
	 */
	public Object clone() throws CloneNotSupportedException {
		if (taskLists.size() == 0) {
			throw new CloneNotSupportedException("Cannot clone.");
		}
		Task clone = (Task) super.clone();
		clone.taskLists = new SwapList<>();
		return clone;
	}

	/**
	 * Constructs a string of a task's information
	 * 
	 * @return a task information string
	 */
	public String toString() {
		String task = "* " + getTaskName();
		if (isRecurring()) {
			task += ",recurring";
		}
		if (isActive()) {
			task += ",active";
		}
		task += "\n";

		String description = getTaskDescription();

		if (!description.isEmpty() && description != null) {
			task += description;
		}

		return task;
	}
}
