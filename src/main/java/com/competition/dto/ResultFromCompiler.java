package com.competition.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResultFromCompiler {

    String output;
    HttpStatus statusCode;
    String memory;
    Long cpuTime;
}
