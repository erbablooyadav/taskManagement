package tech.pesto.task.exception;

public class TaskValidationException extends Exception {
	private static final long serialVersionUID = 1L;

	public TaskValidationException(String message) {
		super("Invalid task data: " + message);
	}
}
