package com.telusko.agentic;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface LearningPlanAgent
{
    @Agent(
            description = "Creates a learning plan",
            outputKey = "learningPlan"
    )
    @UserMessage("""
            You are a technical learning plan creator.

            Course recommendation:
            {{recommendation}}

            Create a simple 4-week learning plan.
            Include the main topic for each week.
            Keep the answer concise.
            """)
    String createPlan(
            @V("recommendation") String recommendation
    );
}
