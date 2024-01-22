package tech.pesto.task.exception;

public class TaskNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public TaskNotFoundException(long id) {
		super("Task not found with id: " + id);
	}
}
