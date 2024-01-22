package tech.pesto.task.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.validation.annotation.Validated;

import tech.pesto.task.model.Status;
import tech.pesto.task.model.Task;

@Validated
public interface TaskRepository extends MongoRepository<Task, Long> {

	public List<Task> findByStatus(Status status);

}
