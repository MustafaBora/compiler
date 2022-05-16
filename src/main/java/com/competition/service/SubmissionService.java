package com.competition.service;

import com.competition.dto.Code;
import com.competition.dto.ResultFromCompiler;
import com.competition.entity.Submission;
import com.competition.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
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

    public SubmissionService(SubmissionRepository repository) {
        this.repository = repository;
    }

    public List<Submission> first3SortedByResult() {
        Pageable sortedByName =
                PageRequest.of(0, 3, Sort.by("result").descending().and(Sort.by("name")));
        return repository.findAll(sortedByName).toList();
    }

    public Long submitCode(Code code) {
        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<Code> request = new HttpEntity<>(code);

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
                    .task(null)
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
