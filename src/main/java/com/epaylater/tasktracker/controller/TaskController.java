package com.epaylater.tasktracker.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epaylater.tasktracker.dto.TaskRequest;
import com.epaylater.tasktracker.entity.Task;
import com.epaylater.tasktracker.exception.TaskIdExistAlreadyException;
import com.epaylater.tasktracker.exception.TaskNotFoundException;
import com.epaylater.tasktracker.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	
	@PostMapping()
	public ResponseEntity<Task> saveTask(@Valid @RequestBody TaskRequest taskRequest) throws TaskIdExistAlreadyException
	{
		logger.info("POST request received to create task: {}", taskRequest);
		return new ResponseEntity<>(taskService.saveTask(taskRequest),HttpStatus.CREATED);
	}


	@GetMapping()
	public  ResponseEntity<List<Task>>  getAllTAsk()
	{
		logger.info("GET request received for tasks");
		return ResponseEntity.ok(taskService.getAllTAsk());
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Optional<Task>>  getById(@PathVariable String id) throws TaskNotFoundException
	{
		logger.info("GET request received for task with ID: {}", id);
		return ResponseEntity.ok(taskService.getById(id));
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@Valid @RequestBody TaskRequest taskRequest,@PathVariable String id) throws TaskNotFoundException
	{
	
		   logger.info("PUT request received to update task with ID: {}", id);
		return ResponseEntity.ok(taskService.updateTask(taskRequest,id));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable String id) throws TaskNotFoundException
	{
		 logger.info("DELETE request received to delete task with ID: {}", id);
		return ResponseEntity.ok(taskService.deleteById(id));
	}
	
}
