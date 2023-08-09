package com.competition.controller;

import com.competition.dto.Code;
import com.competition.dto.CodeJDoodle;
import com.competition.dto.Place;
import com.competition.service.SubmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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
    public List<Place> first3SortedByResult() {
        return service.first3SortedByResult().stream().map((submission) ->
                new Place(submission.getName(),
                        submission.getScript().substring(0, submission.getScript().length() < 100 ? submission.getScript().length() : 100),
                        submission.getTask().getName()))
            .collect(Collectors.toList());
    }

    @PostMapping
    public Long submitCode(@RequestBody Code code) {
        return service.submitCode(code);
    }

}
