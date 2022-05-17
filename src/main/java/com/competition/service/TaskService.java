package com.competition.service;

import com.competition.entity.Task;
import com.competition.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findTaskByName(String name) {
        List<Task> byName = repository.findByName(name);
        if(byName != null && !byName.isEmpty()) {
            return byName.get(0);
        }
        return null;
    }

}
