package com.telusko.toolcalling;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface CourseAssistant {

    @SystemMessage("""
        You are a helpful course assistant.
        Use available tools to answer course
        price and duration questions.
        Do not invent course details.
        """)
    @UserMessage("{{it}}")
    String chat(String message);
}
