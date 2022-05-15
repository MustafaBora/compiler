package com.competition.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResultFromCompiler {

    String output;
    Integer statusCode;
    String memory;
    String cpuTime;
}
