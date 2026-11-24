package com.telusko.metadata;

import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.data.message.UserMessage;

public class Main
{

    public static void main(String[] args)
    {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        UserMessage userMessage =
                UserMessage.from("Explain Java in 3 sentences.");
        ChatRequest request = ChatRequest.builder()
                .messages(userMessage)
                .parameters(ChatRequestParameters.builder()
                        .maxOutputTokens(10)
                        .build())
                .build();
        ChatResponse response = model.chat(request);
        System.out.println(response.aiMessage().text());
        Integer tokenCount = response.tokenUsage().totalTokenCount();
        System.out.println("Token Count: " + tokenCount);
        Integer inputTokenCount = response.tokenUsage().inputTokenCount();
        System.out.println("Input Token Count: " + inputTokenCount);
        Integer outputTokenCount = response.tokenUsage().outputTokenCount();
        System.out.println("Output Token Count: " + outputTokenCount);

        System.out.println("Finish Reason: " + response.finishReason());
        System.out.println("Model Name: " + response.modelName());
        System.out.println("Response ID: " + response.id());
    }
}
