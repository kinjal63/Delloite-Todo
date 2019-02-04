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
$ git clone https://github.com/kinjal63/N26Test.git
$ cd N26Test
$ npm install
$ mvn clean install
```
