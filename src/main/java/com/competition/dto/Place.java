package com.competition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/** This is the DTO for showing top 3 */
@Data
@AllArgsConstructor
public class Place {

    private String name;
    private String successSolution;
    private String task;

}
