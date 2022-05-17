package com.competition.service;

import com.competition.dto.Code;
import com.competition.dto.CodeJDoodle;
import com.competition.dto.ResultFromCompiler;
import com.competition.entity.Submission;
import com.competition.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SubmissionService {

    @Value("${jdoodle.compiler.api}")
    private String JDOODLE_API;

    @Value("${jdoodle.client.id}")
    private String CLIENT_ID = "";

    @Value("${jdoodle.client.secret}")
    private String CLIENT_SECRET;

    private final SubmissionRepository repository;

    private final TaskService taskService;

    public SubmissionService(SubmissionRepository repository, TaskService taskService) {
        this.repository = repository;
        this.taskService = taskService;
    }

    public List<Submission> first3SortedByResult() {
        Pageable sortedByName =
                PageRequest.of(0, 3, Sort.by("result").descending().and(Sort.by("name")));
        return repository.findAll(sortedByName).toList();
    }

    public Long submitCode(Code code) {
        try {
            CodeJDoodle codeJDoodle = new CodeJDoodle(code);
            RestTemplate restTemplate = new RestTemplate();

            // Create the request body as a MultiValueMap
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.add("user-agent", "Application");

            HttpEntity<CodeJDoodle> request = new HttpEntity<>(codeJDoodle, headers);
            ResultFromCompiler resultFromCompiler = restTemplate.postForObject(JDOODLE_API, request, ResultFromCompiler.class);
            if(resultFromCompiler == null) return null;
            Submission submission = Submission.builder()
                .output(resultFromCompiler.getOutput())
                .statusCode(resultFromCompiler.getStatusCode())
                .memory(resultFromCompiler.getMemory())
                .cpuTime(resultFromCompiler.getCpuTime())
                .name(code.getName())
                .script(code.getScript())
                .language(code.getLanguage())
                .stdin(code.getStdin())
                .versionIndex(code.getVersionIndex())
                .task(taskService.findTaskByName(code.getTask()))
                .result("not yet decided")
                .build();

            repository.save(submission);

            return submission.getId();
        } catch (RestClientException rce) {
            rce.printStackTrace();
            return null;
        }
    }

}
