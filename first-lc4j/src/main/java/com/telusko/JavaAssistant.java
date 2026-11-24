package com.telusko;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface JavaAssistant
{
    @SystemMessage(
            """
            You are an experienced Java instructor.
            Explain concepts in simple words.
            Always provide a small example.
            """
    )
    @UserMessage("Explain {{topic}} with a simple example")
    String explain(@V("topic") String topic);
}
