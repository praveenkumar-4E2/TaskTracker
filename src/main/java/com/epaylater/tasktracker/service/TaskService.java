package com.epaylater.tasktracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epaylater.tasktracker.dto.TaskRequest;
import com.epaylater.tasktracker.entity.Task;
import com.epaylater.tasktracker.exception.TaskIdExistAlreadyException;
import com.epaylater.tasktracker.exception.TaskNotFoundException;
import com.epaylater.tasktracker.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

	//method to save task to database
	public Task saveTask(TaskRequest taskRequest) throws TaskIdExistAlreadyException {
		logger.info("Creating task: {}", taskRequest);
		Optional<Task> optionalTask = taskRepository.findById(taskRequest.getId());
		if (optionalTask.isEmpty()) {
			Task task = Task.build(taskRequest.getId(), taskRequest.getTitle(), taskRequest.getDescription(),
					taskRequest.getDueDate());
			taskRepository.save(task);
			logger.debug("Task created: {}", task);
			return task;

		} else {
			logger.warn("Task with ID {} already existed.", taskRequest.getId());
			throw new TaskIdExistAlreadyException("Task with id:" + taskRequest.getId() + " already exists");
		}

	}

	//method to get all tasks
	public List<Task> getAllTAsk() {
		return taskRepository.findAll();
	}
	

	//method to get tasks by id
	public Optional<Task> getById(String id) throws TaskNotFoundException {
		logger.info("Fetching task with ID: {}", id);
		Optional<Task> task = taskRepository.findById(id);
		if (task.isEmpty()) {
			logger.warn("Task with ID {} not found.", id);
			throw new TaskNotFoundException("Task not found with id " + id);
		}
		logger.debug("Found task: {}", task);
		return task;
	}

	//method to update task
	public Task updateTask(TaskRequest taskRequest, String id) throws TaskNotFoundException {
		logger.info("Updating task with ID {}",id);
		Optional<Task> taskOptional = taskRepository.findById(id);

		if(taskOptional.isEmpty())
		{
			logger.warn("Task with ID {} not found.", id);
			
			throw new TaskNotFoundException("Task with ID: {} not found");
		}
		Task task = taskOptional.get();
		task = (Task.build(taskRequest.getId(), taskRequest.getTitle(), taskRequest.getDescription(),
				taskRequest.getDueDate()));

		taskRepository.save(task);
		logger.debug("Task with ID:{} updated",id);
		return task;
	}

	//method to delete task by id
	public String deleteById(String id) throws TaskNotFoundException {
		logger.info("Deleting task with ID: {}", id);
		Optional<Task> task = taskRepository.findById(id);
		if (task.isEmpty()) {
			logger.warn("Task with ID {} not found.", id);
			throw new TaskNotFoundException("Task with id:" + id + " already deleted or not existed");
		} else {

			taskRepository.deleteById(id);
			logger.debug("Task with ID {} deleted successfully.", id);
			return "task with id:" + id + " is deleted ";
		}

	}

}
