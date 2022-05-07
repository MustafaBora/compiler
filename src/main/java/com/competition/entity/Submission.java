package com.competition.entity;

import javax.persistence.*;

@Entity
@Table(name = "submission", schema = "competition")
public class Submission {

    @Id
    @Column(nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String script;

    private String result;

    @ManyToOne
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "fk_submission_task"))
    private Task task;


}
