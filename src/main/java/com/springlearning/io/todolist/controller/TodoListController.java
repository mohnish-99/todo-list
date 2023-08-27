package com.springlearning.io.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TodoListController {
    
    private TodoListService todoListService;
    
    TodoListController(TodoListService todoListService){
        this.todoListService = todoListService;
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
       return todoListService.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id : "+id + " not found"));
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return todoListService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<Object> saveTask(@RequestBody @Valid Task task){
        return ResponseEntity.ok().body(todoListService.saveTask(task));
        
    }

    @PutMapping
   public ResponseEntity<Object> updateTask(@RequestBody @Valid Task task){
        return ResponseEntity.ok().body(todoListService.updateTask(task));
    }

    @DeleteMapping("/{id}")
    public String  deleteTaskById(@PathVariable Long id){
       return todoListService.deleteTask(id);
    }
}
