package com.epaylater.tasktracker.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
 @AllArgsConstructor(staticName = "build")
public class TaskRequest {
	
	@NotNull(message="id cannot be null")
	@NotBlank(message = "id cannot be blank")
	private String id;
	
	@NotNull(message ="title cannot be null")
	@NotBlank(message = "title cannot be blank")
	private String title;
	
	@NotNull(message = "description cannpt be null")
	@NotBlank(message = "description cannot be blank")
	private String description;
	
	@NotNull(message = "due date cannot be null")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
}
