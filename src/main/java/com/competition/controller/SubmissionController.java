package com.competition.controller;

import com.competition.dto.Code;
import com.competition.service.SubmissionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * This controller serves the submit page of the coding challenge.
 */
@RestController
@RequestMapping("api/submissions")
public class SubmissionController {

    private final SubmissionService service;


    public SubmissionController(SubmissionService service) {
        this.service = service;
    }

    /*    @GetMapping()
        public List<Submission> first3SortedByResult() {
            return service.first3SortedByResult();
        }
    */

/*    @PostMapping
    public void submitCode(@RequestBody String script, @RequestBody String language, @RequestBody String stdin, @RequestBody String versionIndex) {
        service.submitCode(new Code(script, language, stdin, versionIndex));
    }
    */
    @PostMapping
    public Long submitCode(@RequestBody Code code) {
        return service.submitCode(code);
    }

}
