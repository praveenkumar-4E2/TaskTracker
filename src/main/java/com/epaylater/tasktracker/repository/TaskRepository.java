package com.epaylater.tasktracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epaylater.tasktracker.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

	Task save(Optional<Task> task);


}
