package com.telusko.agentic;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;

public interface CourseSupportAdvisor
{
    @Agent
    String handle(@V("request") String request);
}
