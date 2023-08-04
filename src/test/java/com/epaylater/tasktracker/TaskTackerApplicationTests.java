package com.epaylater.tasktracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.LocalDateAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.epaylater.tasktracker.dto.TaskRequest;
import com.epaylater.tasktracker.entity.Task;
import com.epaylater.tasktracker.exception.TaskIdExistAlreadyException;
import com.epaylater.tasktracker.repository.TaskRepository;
import com.epaylater.tasktracker.service.TaskService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class TaskTackerApplicationTests {

	
	@Autowired
	private TestH2Repository testH2Repository;
	
	@LocalServerPort
	private int port;
	
	private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;
	
	@BeforeAll
	public static void init() {
		restTemplate=new RestTemplate();
	}

	@BeforeEach
	public void setup() {
		baseUrl=baseUrl.concat(":").concat(port+"").concat("/tasks");
	}
	
	Date  date=new Date();

    @Test
    public void contextLoads() {
        // Your test logic here
    }
	
	@Test
	public void testAddTask() {
		TaskRequest taskRequest=TaskRequest.build("2", "task2", "task2 description", date);
		Task response = restTemplate.postForObject(baseUrl, taskRequest, Task.class);
		assertEquals("task2", response.getTitle());
		assertEquals(1,testH2Repository.findAll().size());
	}
	
	
	@Test
	public void testGetTask() {
		Task task=Task.build("3", "task3", "task3 description", date);
		testH2Repository.save(task);
		Task response = restTemplate.getForObject(baseUrl.concat("/3"), Task.class);
		assertEquals("task3", response.getTitle());
	}
	
	@Test
	public void testUpdateTask() {
		Task task=Task.build("4", "task5", "task4 description", date);
		testH2Repository.save(task);
		Task updateTask=Task.build("4", "task4", "task4 description", date);
		restTemplate.put(baseUrl.concat("/4"),updateTask);
		Optional<Task>  test= testH2Repository.findById("4");
		assertEquals("task4", test.get().getTitle());
		
	}
	
	@Test
	public void testDeleteTask() {
		restTemplate.delete(baseUrl.concat("/2"));
		restTemplate.delete(baseUrl.concat("/3"));
		restTemplate.delete(baseUrl.concat("/4"));
	
		assertEquals(0,testH2Repository.findAll().size());
		
	}
	
}
