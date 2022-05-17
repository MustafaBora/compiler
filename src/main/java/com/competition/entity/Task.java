package com.competition.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(schema = "competition")
@Entity
@Getter
public class Task {

    @Id
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column()
    private String description;

}
