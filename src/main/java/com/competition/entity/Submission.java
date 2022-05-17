package com.competition.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = "competition")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column( nullable = false, unique = true)
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
