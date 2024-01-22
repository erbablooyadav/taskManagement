package tech.pesto.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import tech.pesto.task.model.Task;
import tech.pesto.task.model.Status;
import tech.pesto.task.repository.TaskRepository;
import tech.pesto.task.exception.TaskNotFoundException;
import tech.pesto.task.exception.TaskValidationException;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Validated
@Data
@Slf4j
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Transactional
	public List<Task> getAllTasks() {
		log.info("Getting all tasks");
		return taskRepo.findAll();
	}
	
	@Transactional
	public Task getTaskById(Long id) throws TaskNotFoundException {
		log.info("Getting task by id: {}", id);
		return taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
	}
	
	@Transactional
	public Task createTask(@Validated Task task) throws TaskValidationException {
		log.info("Creating task: {}", task);
		validateTask(task);
		return taskRepo.save(task);
	}
	
	@Transactional
	public Task updateTask(Long id, @Validated Task task) throws TaskNotFoundException, TaskValidationException {
		log.info("Updating task with id: {} and data: {}", id, task);
		validateTask(task);
		if (!taskRepo.existsById(id)) {
			throw new TaskNotFoundException(id);
		}
		task.setId(id);
		return taskRepo.save(task);
	}
	
	@Transactional
	public void deleteTask(Long id) throws TaskNotFoundException {
		log.info("Deleting task by id: {}", id);
		if (!taskRepo.existsById(id)) {
			throw new TaskNotFoundException(id);
		}
		taskRepo.deleteById(id);
	}
	
	@Transactional
	public List<Task> getTasksByStatus(Status status) throws TaskValidationException {
		log.info("Getting tasks by status: {}", status);
		validateStatus(status);
		return taskRepo.findByStatus(status);
	}
	
	private void validateTask(Task task) throws TaskValidationException {
		validateTitle(task.getTitle());
		validateStatus(task.getStatus());
	}
	
	private void validateTitle(String title) throws TaskValidationException {
		if (title == null || title.isEmpty()) {
			throw new TaskValidationException("Title cannot be null or empty");
		}
	}
	
	private void validateStatus(Status status) throws TaskValidationException {
		if (status == null) {
			throw new TaskValidationException("Status cannot be null");
		}
	}
}
