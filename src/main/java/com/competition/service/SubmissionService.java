package com.competition.service;

import com.competition.dto.Code;
import com.competition.dto.ResultFromCompiler;
import com.competition.entity.Submission;
import com.competition.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class SubmissionService {

    @Value("${jdoodle.compiler.api}")
    private String JDOODLE_API;

    @Value("${jdoodle.client.id}")
    private String CLIENT_ID = "";

    @Value("${jdoodle.client.secret}")
    private String CLIENT_SECRET;

    private final SubmissionRepository repository;

    public SubmissionService(SubmissionRepository repository) {
        this.repository = repository;
    }

    public List<Submission> first3SortedByResult() {
        Pageable sortedByName =
                PageRequest.of(0, 3, Sort.by("result").descending().and(Sort.by("name")));
        return repository.findAll(sortedByName).toList();
    }
/*
    public static void main(String args[]) {

        String script = "public class MyClass {" +
                "    public static void main(String args[]) {" +
                "      int x=10;" +
                "      int y=25;" +
                "      int z=x+y;" +
                "" +
                "      System.out.println(\"Sum of x+y = \" + z);" +
                "    }" +
                "}";
        String script2 = "public class MyClass {   public static void main(String args[]) {int x=10;  System.out.println(x);  }  }";
        String language = "java";
        String stdin = "";
        String versionIndex = "3";

        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + CLIENT_ID + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script2 +
                    "\",\"language\":\"" + language +
                    "\",\"stdin\":\"" + stdin +
                    "\",\"versionIndex\":\"" + versionIndex + "\"} ";

            System.out.println(input);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            System.out.println("Output from JDoodle .... \n");
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/


    public Long submitCode(Code code) {

        try {
        /*    URL url = new URL("https://api.jdoodle.com/v1/execute");



            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + CLIENT_ID + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script2 +
                    "\",\"language\":\"" + language +
                    "\",\"stdin\":\"" + stdin +
                    "\",\"versionIndex\":\"" + versionIndex + "\"} ";
*/
            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<Code> request = new HttpEntity<>(code);

            LinkedHashMap<String, Object> returnValue = restTemplate.postForObject(JDOODLE_API, request, LinkedHashMap.class);
            ResultFromCompiler resultFromCompiler = restTemplate.postForObject(JDOODLE_API, request, ResultFromCompiler.class);
            if(returnValue == null) return null;
            Submission submission = Submission.builder()
                    .output(resultFromCompiler.getOutput())
                    .statusCode(resultFromCompiler.getStatusCode())
                    .memory(resultFromCompiler.getMemory())
                    .cpuTime(resultFromCompiler.getCpuTime())
                    .name(code.getName())
                    .script(code.getScript())
                    .language(code.getLanguage())
                    .stdin(code.getStdin())
                    .versionIndex(code.getVersionIndex())
                    .task(null)
                    .result("not yet")

                    .build();
            /*Submission submission = new Submission(code, resultFromCompiler);
            submission.setName(code.getName());
            submission.setTask(null);
            submission.setScript(code.getScript());
            submission.setLanguage(code.getLanguage());
            submission.setOutput((String) returnValue.get("output"));
            submission.setStatusCode((Integer) returnValue.get("statusCode"));
            submission.setMemory((String) returnValue.get("memory"));
            submission.setCpuTime((String) returnValue.get("cpuTime"));
            submission.setResult("dunno yet");
            System.out.println(returnValue.get("output"));
            System.out.println(returnValue.get("statusCode"));
            System.out.println(returnValue.get("memory"));
            System.out.println(returnValue.get("cpuTime"));
            System.out.println(new Date());*/

            repository.save(submission);
            return submission.getId();
            /*System.out.println(input);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            System.out.println("Output from JDoodle .... \n");
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        } catch (RestClientException rce) {
            rce.printStackTrace();
            return null;
        }
    }

}
