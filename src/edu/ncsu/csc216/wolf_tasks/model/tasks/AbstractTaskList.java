package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * AbstractTaskList is an abstract class that provides an implementation
 * of the TaskList interface. It contains common methods and fields for concrete
 * task list implementations.
 * 
 * @author Nirvan Reddy Anumandla
 * @author An Mai
 */
public abstract class AbstractTaskList {

	/** The list of tasks */
	private ISwapList<Task> tasks;
	
	/** The name of the task list */
	private String taskListName;
	
	/** The count of completed tasks */
	private int completedCount;
	
	/**
     * Constructs a new AbstractTaskList with the given name and completed count.
     *
     * @param taskListName   The name of the task list.
     * @param completedCount The number of completed tasks in the task list.
     * @throws IllegalArgumentException if completedCount is invalid or for invalid name
     */
	public AbstractTaskList(String taskListName, int completedCount) {
		if(taskListName == null || "".equals(taskListName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		if(completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		tasks = new SwapList<Task>();
		setTaskListName(taskListName);
		this.completedCount = completedCount;
	}
	
	/**
     * Retrieves the name of the task list.
     *
     * @return The name of the task list.
     */
	public String getTaskListName() {
		return taskListName;
	}
	
	/**
     * Sets the name of the task list.
     *
     * @param name The new name for the task list.
     * @throws IllegalArgumentException if the name is null or empty
     */
	public void setTaskListName(String name) {
		if(name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.taskListName = name;
	}
	
	/**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
	public ISwapList<Task> getTasks(){
		return tasks;
	}
	
	/**
     * Retrieves the count of completed tasks.
     *
     * @return The count of completed tasks.
     */
	public int getCompletedCount() {
		return completedCount;
	}
	
	/**
     * Adds a task to the task list.
     *
     * @param t The task to add.
     */
	public void addTask(Task t) {
		this.tasks.add(t);
		t.addTaskList(this);
	}
	
	/**
     * Removes a task from the task list at the specified index.
     *
     * @param idx The index of the task to remove.
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size - 1
     * @return The task that was removed.
     */
	public Task removeTask(int idx) {
		if(idx < 0 || idx > tasks.size() - 1) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return this.tasks.remove(idx);
	}
	
	/**
     * Retrieves the task at the specified index in the task list.
     *
     * @param idx The index of the task to retrieve.
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size - 1
     * @return The task at the specified index.
     */
	public Task getTask(int idx) {
		if(idx < 0 || idx > tasks.size() - 1) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return this.tasks.get(idx);
	}
	
	/**
     * Marks a task as completed in the task list.
     *
     * @param t The task to mark as completed.
     */
	public void completeTask(Task t) {
		for(int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i) == t) {
				tasks.remove(i);
				completedCount++;
				break;
			}
		}
	}
	
	/**
     * Abstract method to retrieve tasks as a two-dimensional array.
     *
     * @return A two-dimensional array representing tasks.
     *         Each row contains task details.
     */
	public abstract String[][] getTasksAsArray();
}
