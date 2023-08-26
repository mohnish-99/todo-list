package com.springlearning.io.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.springlearning.io.todolist.entity.Task;
import com.springlearning.io.todolist.exception.MethodArgumentNotValidException;
import com.springlearning.io.todolist.exception.ResourceAlreadyPresentException;
import com.springlearning.io.todolist.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
        if(ObjectUtils.isEmpty(task.getName())|| ObjectUtils.isEmpty(task.getDescription())){
            throw new MethodArgumentNotValidException("Name or Description cannot be null or Empty");
        }
        boolean iFTaskExists = checkIfATaskExists(task);
        if(iFTaskExists){
            log.info("Task already exists with the given name");
            throw new ResourceAlreadyPresentException("Task already exists with the given name");
        }
        return tRepository.save(task);
    }

    private boolean checkIfATaskExists(Task task) {
        String taskName = task.getName();
        tRepository.findByName(taskName);
        return tRepository.findByName(taskName).isPresent();
    }

    @Override
    public Task updateTask(Task task) {
        if(ObjectUtils.isEmpty(task.getName())|| ObjectUtils.isEmpty(task.getDescription())){
            throw new MethodArgumentNotValidException("Name or Description cannot be null or Empty");
        }
        String taskName = task.getName();
        Task newTask =  new Task();
        Optional<Task> optionalExistingTask =  tRepository.findByName(taskName);
        if(!optionalExistingTask.isPresent()){
            return tRepository.save(task);
        }
        Task existingTask = optionalExistingTask.get();
            existingTask.setDescription(task.getDescription());
        
        return tRepository.save(existingTask);
    }

    @Override
    public String deleteTask(Long id) {
         tRepository.deleteById(id);
         return "Record Successfully deleted";
    }
    
}
