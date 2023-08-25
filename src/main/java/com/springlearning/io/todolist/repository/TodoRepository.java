package com.springlearning.io.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springlearning.io.todolist.entity.Task;

@Repository
public interface TodoRepository extends JpaRepository<Task,Long>{
    
}
