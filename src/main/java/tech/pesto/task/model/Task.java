package tech.pesto.task.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;

@Document(collection = "tasks")
@Data
public class Task {

	@Id
	private Long id;
	@Field(name = "title")
	private String title;
	@Field(name = "description")
	private String description;

	@Indexed
	@Field(name = "status")
	private Status status;

}