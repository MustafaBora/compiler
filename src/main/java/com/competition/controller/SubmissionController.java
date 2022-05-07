package com.competition.controller;

import com.competition.entity.Submission;
import com.competition.service.SubmissionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("api/submissions")
public class SubmissionController {

    private final SubmissionService service;

    @Value("${jdoodle.compiler.api}")
    private String JDOODLE_API;

    public SubmissionController(SubmissionService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Submission> first3SortedByResult() {
        return  service.first3SortedByResult();
    }

    public void check() {
    }

    public static void main(String args[]) {

        String clientId = "cf4974ccb98c12d7d4fe93eb07d29f0b"; //Replace with your client ID
        String clientSecret = "fa50da80bc0c27e805ecabb6b759885b88dfe8b591ba55c02f4d4f28e94360c1"; //Replace with your client Secret
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

            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script2 +
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

}
