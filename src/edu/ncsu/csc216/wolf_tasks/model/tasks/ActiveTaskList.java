package edu.ncsu.csc216.wolf_tasks.model.tasks;

/**
 * The class entails of active tasks that are currently being worked on.
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
public class ActiveTaskList extends AbstractTaskList {
	/** The name of currently worked on list of tasks */
	public static final String ACTIVE_TASKS_NAME = "Active Tasks";
	
	/**
	 * Constructor for active task
	 */
	public ActiveTaskList() {
		super(ACTIVE_TASKS_NAME, 0);
	}

	/**
	 * Add a task to the active task list
	 * @param t The task to be added
	 * @throws IllegalArgumentException if the Task is not active
	 */
	@Override
	public void addTask(Task t) {
		if(!t.isActive()) {
			throw new IllegalArgumentException("Cannot add task to Active Tasks.");
		} else {
			super.addTask(t);
		}
	}
	
	/**
	 * Sets the name of the task list
	 * @param taskListName The name of the task to be set
	 * @throws IllegalArgumentException if the taskListName does not equals "Active Tasks"
	 */
	@Override
	public void setTaskListName(String taskListName) {
		if(!taskListName.equals(ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		} else {
			super.setTaskListName(taskListName);
		}
	}
	
	/**
	 * Retrieves tasks as a String multi-dimensional array
	 * @return the tasks as an array
	 */
	public String[][] getTasksAsArray(){
		String[][] tasksArray = new String[this.getTasks().size()][2];
		for(int i = 0; i < this.getTasks().size(); i++) {
			Task task = this.getTask(i);

			tasksArray[i][0] = task.getTaskListName();
			tasksArray[i][1] = task.getTaskName();
			
		}
		return tasksArray;
	}
	
	/**
	 * Clears all tasks from a task list
	 */
	public void clearTasks() {
		while(this.getTasks().size() != 0) {
			this.getTasks().remove(0);
		}
	}
}
