package com.telusko;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        String apiKey=System.getenv("OPENAI_API_KEY");
//
//        String requestBody= """
//                {
//                    "model": "gpt-5.6-luna",
//                    "input": "Explain Java in one sentence"
//                }
//                """;
//
//        //HTTP Client
//        HttpClient httpClient = HttpClient.newHttpClient();
//        //Request Body
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://api.openai.com/v1/responses"))
//                .header("Authorization", "Bearer " + apiKey)
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        HttpResponse<String> response = httpClient.send(
//                request, HttpResponse.BodyHandlers.ofString()
//        );
//        System.out.println(response.body());


        OpenAIClient client = OpenAIOkHttpClient.fromEnv();


        ResponseCreateParams params =
                ResponseCreateParams.builder()
                        .input("Explain Java in one sentence")
                        .model("gpt-5.6-luna").build();

        Response response = client.responses().create(params);
        //System.out.println(response);

        response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .forEach(outputText -> System.out.println(outputText.text()));



    }
}