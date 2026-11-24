package com.telusko.agentic;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface DirectLearningPlanAgent
{

        @Agent(
                description = "Creates a four-week learning plan for a requested technical topic",
                outputKey = "directPlan"
        )
        @UserMessage("""
            You are a technical learning plan creator.

            Student request: {{request}}

            Identify the topic and create a simple four-week
            learning plan with one practical task each week.
            """)
        String createPlan(@V("request") String request);

}
