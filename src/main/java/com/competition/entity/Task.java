package com.competition.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "task", schema = "competition")
@Entity
@Data
public class Task {

    @Id
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column()
    private String description;

}
