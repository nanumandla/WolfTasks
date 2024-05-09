package edu.ncsu.csc216.wolf_tasks.model.tasks;

/**
 * TaskList represents a list of tasks and extends AbstractTaskList.
 * It implements Comparable interface to compare between TaskList objects.
 * 
 * @author Nirvan Reddy Anumandla
 * @author An Mai
 */
public class TaskList extends AbstractTaskList implements Comparable<TaskList> {

	/**
     * Constructs a new TaskList with the given name and completed count.
     *
     * @param taskListName   The name of the task list.
     * @param completedCount The number of completed tasks in the task list.
     */
	public TaskList(String taskListName, int completedCount) {
		super(taskListName, completedCount);
	}
	
	/**
     * Retrieves tasks in the form of a two-dimensional array.
     * Each row represents a task with its details.
     *
     * @return A two-dimensional array representing tasks.
     *         Each row contains task details.
     */
	public String[][] getTasksAsArray() {
		String[][] tasksArray = new String[this.getTasks().size()][2];
		for(int i = 0; i < this.getTasks().size(); i++) {
			Task task = this.getTask(i);
			tasksArray[i][0] = Integer.toString(i + 1);
			tasksArray[i][1] = task.getTaskName();
			
		}
		return tasksArray;
	}
	
	/**
     * Compares this TaskList with the specified TaskList for order.
     * This comparison is based on some property of the TaskList.
     *
     * @param taskList The TaskList to be compared.
     * @return A negative integer, zero, or a positive integer as this TaskList
     *         is less than, equal to, or greater than the specified TaskList.
     */
	public int compareTo(TaskList taskList) {
		return this.getTaskListName().compareTo(taskList.getTaskListName());
	}
}
