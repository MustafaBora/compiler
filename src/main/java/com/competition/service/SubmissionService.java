package com.competition.service;

import com.competition.entity.Submission;
import com.competition.repository.SubmissionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository repository;

    public SubmissionService(SubmissionRepository repository) {
        this.repository = repository;
    }

    public List<Submission> first3SortedByResult() {
        Pageable sortedByName =
                PageRequest.of(0, 3, Sort.by("result").descending().and(Sort.by("name")));
        return repository.findAll(sortedByName).toList();
    }

    public List<Submission> findAll() {
        return repository.findAll();
    }
}
