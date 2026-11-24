package com.telusko.agentic;

import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class Main {
    public static void main(String[] args)
    {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        CourseDescriptionAgent agent = AgenticServices.agentBuilder(CourseDescriptionAgent.class)
                .chatModel(model)
                .build();


        String description = agent.generateCourseDescription(
                "Java Programming",
                "Completely Beginners"
        );
        System.out.println("Course Description: " + description);
    }
}
