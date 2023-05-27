package com.taskmanagement.service;

import com.taskmanagement.model.Task;
import com.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {
    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks(){
        Task task1 = new Task("Title1", "Description1", LocalDateTime.now(), Task.TaskStatus.TODO);
        Task task2 = new Task("Title2", "Description2", LocalDateTime.now(), Task.TaskStatus.TODO);
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(tasks);
//        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));
        TaskService taskService = new TaskService(taskRepository);
        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        assertTrue(result.contains(task1));
        assertTrue(result.contains(task2));
    }

    @Test
    public void testCreateTask(){
        Task task = new Task("Title", "Description", LocalDateTime.now(), Task.TaskStatus.TODO);

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskService taskService = new TaskService(taskRepository);
        Task result = taskService.createTask(task);

        assertEquals(task.getTitle(), result.getTitle());
        assertEquals(task.getDescription(), result.getDescription());
        assertEquals(task.getDueDate(), result.getDueDate());
        assertEquals(task.getStatus(), result.getStatus());
    }

    @Test
    public void testUpdateTask() {
        Task oldTask = new Task("Old Title", "Old Description", LocalDateTime.now(), Task.TaskStatus.TODO);
        Task newTask = new Task("New Title", "New Description", LocalDateTime.now().plusDays(1), Task.TaskStatus.IN_PROGRESS);

        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.of(oldTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArguments()[0]);

        TaskService taskService = new TaskService(taskRepository);
        Optional<Task> result = taskService.updateTask(1L, newTask);

        assertTrue(result.isPresent());

    }
    @Test
    public void testDeleteTask(){
        Task task = new Task("Title", "Description", LocalDateTime.now(), Task.TaskStatus.TODO);


        when(taskRepository.existsById(any(Long.class))).thenReturn(true);
        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.of(task));

        TaskService taskService = new TaskService(taskRepository);
        assertTrue(taskService.deleteTask(1L));
    }
}
