package com.competition.controller;

import com.competition.dto.Code;
import com.competition.entity.Submission;
import com.competition.service.SubmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This controller serves the submit page of the coding challenge.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/submissions")
public class SubmissionController {

    private final SubmissionService service;


    public SubmissionController(SubmissionService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Submission> first3SortedByResult() {
        return service.first3SortedByResult();
    }

    @PostMapping
    public Long submitCode(@RequestBody Code code) {
        return service.submitCode(code);
    }

}
