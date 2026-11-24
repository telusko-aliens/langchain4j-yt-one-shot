package com.telusko.agentic;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface CourseAdvisorAgent
{
    @Agent(
            description = "Recommends the next technical course",
            outputKey = "recommendation"
    )
    @UserMessage("""
            You are a technical course advisor.

            Student background: {{background}}

            Recommend ONE suitable topic to learn next.
            Explain your recommendation in 2 sentences.
            """)
    String recommend(@V("background") String background);

}
