package com.telusko.lc4jspringboot.web;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController
{
    private ChatModel chatModel;
    private TeluskoAssistant teluskoAssistant;
    public ChatController(ChatModel chatModel, TeluskoAssistant teluskoAssistant) {
        this.chatModel = chatModel;
        this.teluskoAssistant=teluskoAssistant;
    }
    @GetMapping("/chat")
    public String chat(@RequestParam String message)
    {
        return chatModel.chat(message);
    }
    @GetMapping("/explain")
    public String explain(@RequestParam String message)
    {
        return teluskoAssistant.explain(message);
    }


}
