package com.telusko.agentic;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;

public interface CourseWorkflows
{
    @Agent
    String createLearningPlan(
            @V("background") String background
    );
}
