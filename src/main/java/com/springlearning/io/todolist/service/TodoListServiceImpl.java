package com.springlearning.io.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlearning.io.todolist.entity.Task;
import com.springlearning.io.todolist.repository.TodoRepository;

@Service
public class TodoListServiceImpl implements TodoListService{

    @Autowired
    private TodoRepository tRepository;
    
    @Override
    public Optional<Task> getTaskById(Long id) {
        return tRepository.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return tRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) {
        return tRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return tRepository.save(task);
    }

    @Override
    public String deleteTask(Long id) {
         tRepository.deleteById(id);
         return "Record Successfully deleted";
    }
    
}
