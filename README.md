# Flexisaf Backend Tasks

## Task 6: Implementing Service Classes
### Duration: 1 Week
### Submission Date: Monday, October 20, 2025
### Submission Due Date: Monday, October 20, 2025

### Task Details: 
Dependency Injection, Autowiring, best practices for business logic implementations.

### Dependencies:

- [ ] Build Tool: Maven
- [ ] DBMS: PostgresSQL
- [ ] DB_NAME: flexisaf
- [ ] DB_USER_NAME: root
- [ ] DB_PASSWORD: root
- [ ] Postgresql Driver
- [ ] Spring Data JPA
- [ ] Flyway

# People API

A simple REST API for managing people profiles built with Spring Boot.

## Base URL: /api/people

## Person Schema
- *id:* UUID
- *age:* Integer [Validations: minimum value is 18]
- *name:* String [not blank, not null, minimum of 3 characters]


## Endpoints

### Get All People
- *Method:* GET
- *URL:* /api/people
- *Description:* Retrieves a list of all people
- *Response:* Array of Person objects

*Example:* 
curl -X GET http://localhost:8080/api/people


### Get Person by ID
- *Method:* GET
- *URL:* /api/people/{id}
- *Description:* Retrieves a specific person by their UUID
- *Parameters:*
    - id (path parameter) - UUID of the person
- *Response:* Person object or 404 if not found

*Example:*
curl -X GET http://localhost:8080/api/people/7c02e7a8-a4f3-4fb4-a709-1c53482f167e


### Create New Person
- *Method:* POST
- *URL:* /api/people
- *Description:* Creates a new person profile
- *Request Body:* Person object (JSON)
- *Response:* Success message with 201 status or error message with 400 status

*Example:*
curl -X POST http://localhost:8080/api/people \
-H "Content-Type: application/json" \
-d '{"name": "Ibrahim Akinyele", "age": 19}'


### Update Person
- *Method:* PUT
- *URL:* /api/people/{id}
- *Description:* Updates an existing person's profile
- *Parameters:*
    - id (path parameter) - UUID of the person to update
- *Request Body:* Person object with updated information (JSON)
- *Response:* Success message or 404 if person not found

*Example:*
curl -X PUT http://localhost:8080/api/people/7c02e7a8-a4f3-4fb4-a709-1c53482f167e \
-H "Content-Type: application/json" \
-d '{"name": "Flexisaf Intern", "age": 34}'


### Delete Person
- *Method:* DELETE
- *URL:* /api/people/{id}
- *Description:* Deletes a person's profile
- *Parameters:*
    - id (path parameter) - UUID of the person to delete
- *Response:* Success message or 404 if person not found

*Example:*
curl -X DELETE http://localhost:8080/api/people/7c02e7a8-a4f3-4fb4-a709-1c53482f167e


## Response Status Codes

- 200 OK - Successful GET, PUT, or DELETE operation
- 201 Created - Successful POST operation
- 400 Bad Request - Invalid request data (POST validation failed)
- 404 Not Found - Person with specified ID not found
- 500 Internal Server Error - Error during DELETE operation

## Notes

- All person IDs are automatically generated UUIDs
- Person validation is performed during creation (POST)
- Person objects must pass validation before being created

## Running the Application

# Compile and run
mvn spring-boot:run

# Or run the JAR file
java -jar target/your-app-name.jar


The application will start on http://localhost:8080
