package com.competition.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "submission", schema = "competition")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String script;

    @Column(nullable = false)
    private String language;

    private String result;

    private String stdin;

    private String versionIndex;

    @ManyToOne
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "fk_submission_task"))
    private Task task;

    String output;

    Integer statusCode;

    String memory;

    String cpuTime;

}
