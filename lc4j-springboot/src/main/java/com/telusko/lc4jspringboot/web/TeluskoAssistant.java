package com.telusko.lc4jspringboot.web;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface TeluskoAssistant
{
    @SystemMessage(
            "You are a friendly Java trainer. Keep answers short."
    )
    @UserMessage("Explain {{it}} in simple terms.")
    String explain(String topic);
}
