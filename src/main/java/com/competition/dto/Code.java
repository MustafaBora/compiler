package com.competition.dto;

import lombok.Data;

/**  DTO that comes from frontend */
@Data
public class Code {
    String name;
    String script;
    String language;
    String stdin;
    String versionIndex;
    String task;
}
