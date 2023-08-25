package com.springlearning.io.todolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springlearning.io.todolist.entity.Task;

public interface TodoRepository extends JpaRepository<Task,Long>{
    Optional<Task> findByName(String name);
}
