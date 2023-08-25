package com.springlearning.io.todolist.service;

import java.util.List;
import java.util.Optional;

import com.springlearning.io.todolist.entity.Task;


public interface TodoListService {
    Optional<Task> getTaskById(Long id);
    List<Task> getAllTasks();
    Task saveTask(Task task);
    Task updateTask(Task task);
    String deleteTask(Long id);
}
