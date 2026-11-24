package com.telusko.toolcalling;

import com.telusko.toolcalling.tools.CourseTools;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class CourseToolDemo {

    public static void main(String[] args) {
        // Step 1: Read API key from environment (never hardcode secrets).
        String apiKey = System.getenv("OPENAI_API_KEY");


        // Step 2: Configure the model.
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o-mini")
                .build();

        CourseTools tools=new CourseTools();

        CourseAssistant courseAssistant = AiServices.builder(CourseAssistant.class)
                .chatModel(model)
                .tools(tools)
                .build();

        String response = courseAssistant.chat(
                "What is the price and duration of the Python course?"
        );

        System.out.println("\nAI Response:");
        System.out.println(response);

    }
}
