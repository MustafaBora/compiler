package com.competition.repository;

import com.competition.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
