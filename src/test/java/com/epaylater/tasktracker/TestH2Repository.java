package com.epaylater.tasktracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epaylater.tasktracker.entity.Task;

@Repository
public interface TestH2Repository extends JpaRepository<Task, String> {

}
