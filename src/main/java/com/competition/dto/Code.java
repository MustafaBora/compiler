package com.competition.dto;

import lombok.Data;

@Data
public class Code {

    String clientId = "cf4974ccb98c12d7d4fe93eb07d29f0b"; //Replace with your client ID
    String clientSecret = "fa50da80bc0c27e805ecabb6b759885b88dfe8b591ba55c02f4d4f28e94360c1"; //Replace with your client Secret
    String script;
    String language = "java";
    String stdin = "";
    String versionIndex = "3";
}
