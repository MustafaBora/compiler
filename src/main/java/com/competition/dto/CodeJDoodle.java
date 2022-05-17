package com.competition.dto;

import lombok.Data;

/** Data that goes to JDoodle */
@Data
public class CodeJDoodle {

    final String clientId = "cf4974ccb98c12d7d4fe93eb07d29f0b"; //Replace with your client ID
    final String clientSecret = "fa50da80bc0c27e805ecabb6b759885b88dfe8b591ba55c02f4d4f28e94360c1"; //Replace with your client Secret

    final String name;
    final String script;
    final String language;
    final String stdin;
    final String versionIndex;

    public CodeJDoodle(Code code) {
        this.language = code.getLanguage();
        this.script = code.getScript();
        this.versionIndex = code.getVersionIndex();
        this.stdin = code.getStdin();
        this.name = code.getName();
    }

}
