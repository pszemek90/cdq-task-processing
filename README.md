# Task Processing Asynchronous API

## How to start the application
### Tools needed:
- Maven (to run mvn command)
- Docker (to build images)
- Docker compose (to run attached example stack)
### 1. Using provided start.sh script
You can use start.sh script (make sure it's executable), running it with `./start.sh` command.
After that, just run `docker compose up -d` to run the containers.

NOTE: Be sure to not have the `cdq-task-processing_postgres_data` in your volumes already (most unlikely but who knows).
Otherwise, init scripts for postgres database will not run. If you do have one, delete it.

### 2. Manual build
In case you can't (or don't want to) use the script, you can always build the services manually, using `mvn clean install`
in suitable directories. Since two 'worker' services are dependent on 'library' ones the order matters in that case:
1. `task-processing-model`
2. `task-processing-database`
3. `intake-service` and `processor-service` (order doesn't matter here, just please build both)

### Create docker images for 'worker' services:
- Run `docker build -t taskprocessing-processor-service:0.0.1 .` (there's a dot in the end)
in `processor-service` parent directory
- Run `docker build -t taskprocessing-intake-service:0.0.1 .` in `intake-service` parent directory

## How to use the API
### There are three endpoints exposed by the API:
1. http://localhost:8080/tasks/create - to create a task (POST)
2. http://localhost:8080/tasks - to list all the tasks (with progress and results) (GET)
3. http://localhost:8080/tasks/{uuid} - to get the current status of desired task (GET)

### Create task
To create a task, you need to send a payload in following format:
```json
{
   "input": "ABCABC",
   "pattern": "ABC"
}
```
As a result you will receive your newly created task with its location in Location header 
(with the relative URL to invoke). For example - Location: /tasks/505cf6b6-0fa2-4297-8187-f6fcd4d6dcf9.
This can be used in the third endpoint directly.