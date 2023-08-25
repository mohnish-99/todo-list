package com.springlearning.io.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springlearning.io.todolist.entity.Task;
import com.springlearning.io.todolist.exception.ResourceNotFoundException;
import com.springlearning.io.todolist.service.TodoListService;

@RestController
@RequestMapping("/tasks")
public class TodoListController {
    @Autowired
    private TodoListService todoListService;

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
       return todoListService.getTaskById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return todoListService.getAllTasks();
    }

    @PostMapping
    public Task saveTask(@RequestBody Task task){
        return todoListService.saveTask(task);
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task){
        return todoListService.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public String  deleteTaskById(@PathVariable Long id){
       return todoListService.deleteTask(id);
    }
}
