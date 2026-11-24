package com.telusko;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        String apiKey = System.getenv("OPENAI_API_KEY");

//        SystemMessage systemMessage = SystemMessage.systemMessage(
//                "You are an experienced Java instructor. " +
//                        "Explain concepts in simple words with examples in short."
//        );
//
//        UserMessage userMessage = UserMessage.userMessage("Explain dependency injection");

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o-mini")
                .temperature(0.4)
                .maxCompletionTokens(400)
                .build();

      //  String answer=model.chat("Suggest me course name for Java in one sentence and one option ");
//        ChatResponse chatResponse = model.chat(systemMessage, userMessage);
//        String answer=chatResponse.aiMessage().text();

        JavaAssistant assistant = AiServices.builder(JavaAssistant.class)
                .chatModel(model)
                .build();
        String answer=assistant.explain("Dependency injection");
        System.out.println(answer);


    }
}