# Task Management App

A simple web application for managing tasks and to-do lists.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [API Testing](#api-testing)
- [Usage](#usage)

## Introduction

Task Management App is a web application that helps you organize and track your tasks. It allows you to create tasks, set due dates, update task status, and delete tasks. The app provides an intuitive user interface for managing your to-do lists effectively.

## Features

- Create tasks with titles, descriptions, due dates, and status.
- View task details including title, description, status, and due date.
- Edit existing tasks to update their details.
- Delete tasks to remove them from the list.

## Technologies

The Task Management App is built using the following technologies:

- Front-end: HTML, CSS, JavaScript, Vue.js
- Backend: Java, Spring Boot
- Database: H2
- State Management: Vuex
- Styling: Tailwind CSS
- API Testing: JUnit, Mockito

## Installation

To run the Task Management App locally, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/task-management-app.git`
2. Navigate to the project directory: `cd task-management-app`
3. Set up the H2 database and provide the connection details in the backend application configuration.
4. Build the backend application using your preferred Java build tool (e.g., Maven or Gradle).
5. Start the backend server.
6. Navigate to the frontend directory: `cd frontend`
7. Install the dependencies: `npm install`
8. Start the frontend development server: `npm run serve`
9. Access the app in your browser at `http://localhost:8080`

## API Testing

The Task Management App API is tested using the JUnit and Mockito frameworks. The tests cover various API endpoints and scenarios to ensure the proper functioning of the backend.

To run the API tests, follow these steps:

1. Navigate to the backend directory: `cd backend`
2. Build and run the tests using your preferred Java build tool (e.g., Maven or Gradle).
3. The test results will be displayed in the console.

## Usage

1. Open the Task Management App in your browser.
2. Create a new task by clicking the "Add New Task" button and providing the required details.
3. Click on a task in the list to view its details.
4. To edit a task, click the "Edit" button and update the necessary information.
5. To delete a task, click the "Delete" button.
