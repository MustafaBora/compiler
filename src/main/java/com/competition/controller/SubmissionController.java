package com.competition.controller;

import com.competition.dto.Code;
import com.competition.entity.Submission;
import com.competition.service.SubmissionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


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
    @GetMapping
    public void submitCode() {
        service.submitCode(new Code());
    }

}
