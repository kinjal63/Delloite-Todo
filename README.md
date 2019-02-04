# Delloite ToDo App
Deloitte Todo web application

## Technology Stack Used
* Java 1.8 with Spring Boot and an embedded Tomcat
* Maven for dependency management
* Logback for logging
* React with webpack to develop UI
* semantic UI to build rich UI

## Overview
The purpose of this assignment is to create a Todo application to create, update and delete todo tasks managed by user.
It remains persistent with user account i.e. if user logins next time, he/she will be able to get all saved/modified tasks

The API exposes 4 endpoints :

POST /user/login

POST /todo/addTodo

POST /todo/updateTodo

POST /todo/removeTodo

GET /todo/getAll

Create a todo with request body including title and description per user

Get all saved todos created by user based on their login

How to run
```
$ git clone https://github.com/kinjal63/Delloite-Todo.git
$ cd Delloite-Todo
$ npm install
$ mvn clean install
$ npm run watch
```

if you are running from eclipse, foloow this steps.
```
$ npm install
```
Right click on ToDoApp and run as Java Application then hit below url to view it in browser.
http://localhost:8090/index.html

## Terminology
## Java
It uses JWT based Authorization token to be issued when user logs in the login page. It is then to be provided in each request header to verify user. If it expires, user won't be able to call any API until new token is issued.

Token is generated and verified using https://mvnrepository.com/artifact/com.auth0/java-jwt
Spring Data integration provides reduced code to store data in in-momory database h2. It can be accessed at http://localhost:8090/h2-console

Project lombok is injected in pom.xml to generate getters, setters and required constructors with annotations.

## UI
ReactJS is used to tune performance along with semantic UI for enriched UI.
Redux is integrated to manage todo list for an efficient binding to UI.
Webpack is configured in project to make bundle and define babel to convert ES6 in ES5 syntax supported by most of the browser.

By executing `npm run watch`, it enables live UI reloading in browser.

## Test Cases
Application tests are written which is helpful to detect API defects and loopholes. These test cases can be found in TodoApplicationTests.java under src/test/java

## Deployable war
War is commited inside Deployed_War directory and after deploying to tomcat, it can be run at http://localhost:8080/Todo/
User can login by below credentials stored in DB.
1)username: test, password: password
2)username: test12, password: password12
3)username: test13, password: password13

H2 console can be tracked at http://localhost:8080/Todo/h2-console



