package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;

/**
 * Class reads information from multiple task lists and records it to desinated
 * location
 * 
 * @author An Mai, Nirvan Reddy Anumandla
 */
public class NotebookWriter {

	/**
	 * Records the notebook information (e.i task lists and tasks) to the specified
	 * location
	 * 
	 * @param file         the location the notebook will be saved to
	 * @param notebookName the notebook to be recorded
	 * @param tasklist     the list of tasks to be recorded.
	 */
	public static void writeNotebookFile(File file, String notebookName, ISortedList<TaskList> tasklist) {
		try {
			PrintWriter write = new PrintWriter(file);
			write.println("! " + notebookName);
			for (int i = 0; i < tasklist.size(); i++) {
				write.println("# " + tasklist.get(i).getTaskListName() + "," + tasklist.get(i).getCompletedCount());
				for (int j = 0; j < tasklist.get(i).getTasks().size(); j++) {
					write.println(tasklist.get(i).getTasks().get(j).toString());
				}
			}
			write.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save to file.");
		}

	}
}
