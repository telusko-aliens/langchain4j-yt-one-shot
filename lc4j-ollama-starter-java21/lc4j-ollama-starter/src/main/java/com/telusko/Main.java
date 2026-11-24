package com.telusko;

import dev.langchain4j.model.ollama.OllamaChatModel;

public class Main {
    public static void main(String[] args)
    {
        OllamaChatModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("mistral:latest")
                .temperature(0.7)
                .build();

       String response= model.chat("Tell me about Java and AI");
        System.out.println("Response from Ollama Model: "+response);

    }
}
