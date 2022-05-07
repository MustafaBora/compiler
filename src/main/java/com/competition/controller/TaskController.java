package com.competition.controller;

import com.competition.service.TaskService;
import com.competition.entity.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Returns tasks
 */

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Task> findAll() {
        return service.findAll();
    }

}
