package tech.pesto.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tech.pesto.task.service.TaskService;
import tech.pesto.task.model.Task;
import tech.pesto.task.model.Status;
import tech.pesto.task.exception.TaskNotFoundException;
import tech.pesto.task.exception.TaskValidationException;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<List<Task>> findAllTasks() {
		List<Task> tasks = taskService.getAllTasks();
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) throws TaskNotFoundException {
		Task task = taskService.getTaskById(id);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam Status status) throws TaskValidationException {
	    List<Task> tasks = taskService.getTasksByStatus(status);
	    return ResponseEntity.ok(tasks);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String createTask(@RequestBody Task task) throws TaskValidationException {
		taskService.createTask(task);
		return "Task Created Successfully";
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String updateTask(@PathVariable Long id, @RequestBody Task task) throws TaskNotFoundException, TaskValidationException {
		taskService.updateTask(id, task);
		return "Task Updated Successfully";
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteTask(@PathVariable Long id) throws TaskNotFoundException {
		taskService.deleteTask(id);
		return "Task Deleted Successfully";
	}
	
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TaskValidationException.class)
	public ResponseEntity<String> handleTaskValidationException(TaskValidationException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}