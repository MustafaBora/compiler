package com.competition.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "task", schema = "competition")
@Entity
public class Task {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "input_parameter")
    private String inputParameter;

}
