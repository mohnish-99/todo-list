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

    private TodoRepository taskRepository;

    TodoListServiceImpl(TodoRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    
    @Override
    public Optional<Task> getTaskById(Long id) {
        log.info("Finding task by given id:"+id);
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        log.info("Finding all tasks");
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) {
        if(ObjectUtils.isEmpty(task.getName())|| ObjectUtils.isEmpty(task.getDescription())){
           throw new MethodArgumentNotValidException("Name or Description cannot be null or Empty");
        }
        if(isTaskExists(task)){
            log.info("Task already exists with the given name");
            throw new ResourceAlreadyPresentException("Task already exists with the given name");
        }
        return taskRepository.save(task);
    }

    private boolean isTaskExists(Task task) {
        String taskName = task.getName();
        return taskRepository.findByName(taskName).isPresent();
    }

    @Override
    public Task updateTask(Task task) {
        if(ObjectUtils.isEmpty(task.getName()) || ObjectUtils.isEmpty(task.getDescription())){
            throw new MethodArgumentNotValidException("Name or Description cannot be null or Empty");
        }
        String taskName = task.getName();
        Optional<Task> optionalExistingTask =  taskRepository.findByName(taskName);
        if(!optionalExistingTask.isPresent()){
            log.info("Task not found with given details, saving the new task");
            return taskRepository.save(task);
        }
        Task existingTask = optionalExistingTask.get();
        if(taskName.equals(existingTask.getName())){
            log.info("Task found with given name :" +existingTask.getName() + " saving the new task");
            existingTask.setDescription(task.getDescription());
        }
        return taskRepository.save(existingTask);
    }

    @Override
    public String deleteTask(Long id) {
         taskRepository.deleteById(id);
         return "Record Successfully deleted";
    }
    
}
