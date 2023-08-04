# TaskTracker
Task Tracker Application
The Task Tracker Application is a RESTful API built using Spring Boot that allows users to track their tasks. The application provides endpoints to perform CRUD (Create, Read, Update, Delete) operations on tasks, along with comprehensive error handling and data validation. The data is stored in a PostgreSQL database for production, and an H2 in-memory database is used for testing purposes.

Technologies Used

The following technologies have been used to develop the Task Tracker Application:
Java: The primary programming language for developing the application logic.
Spring Boot: A powerful framework for building Java-based web applications with ease.
PostgreSQL: A relational database management system used for storing task data in production.
H2 Database: An in-memory database used for testing purposes to avoid interfering with the production database.
Swagger: An API documentation tool that generates interactive API documentation to make it easy for developers to explore and understand the available endpoints.
"http://localhost:8080/swagger-ui/index.html".
JUnit and Mockito: Used for writing unit tests and integration tests to ensure the correctness of the application's behavior.
Lombok: A library that reduces boilerplate code by automatically generating getters, setters, constructors, etc.


Setup
To set up and run the Task Tracker Application, follow these steps:

Clone the repository: Clone the project repository from the source.

Install Java and Maven: Ensure that you have Java and Maven installed on your system.

Database Configuration: Create a PostgreSQL database for production and update the database configuration in src/main/resources/application.properties.
For testing, update the test database configuration in src/test/resources/application.properties.

Build and Run: Build the project using Maven by running the following command:

go
Copy code
mvn clean package
Then, run the application:

bash
Copy code
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar
API Endpoints
The Task Tracker Application provides the following API endpoints:

POST /tasks: Create a new task.
GET /tasks/{id}: Retrieve a task by its ID.
GET /tasks: Retrieve a list of all tasks.
PUT /tasks/{id}: Update an existing task by its ID.
DELETE /tasks/{id}: Delete a task by its ID.
These endpoints allow users to manage their tasks efficiently.

Usage
Developers can use the Task Tracker Application's RESTful API to interact with the task list programmatically. You can use tools like curl or Postman to send HTTP requests to the API endpoints and manage tasks.

For example, to create a new task, send a POST request with the task details to http://localhost:8080/tasks.

Testing
The Task Tracker Application includes unit tests and integration tests to ensure the correctness of the application's behavior. The tests are written using JUnit and Mockito and can be executed with the following command:

bash
Copy code
mvn test
The tests help to identify and fix any issues in the application and ensure that it performs as expected.
