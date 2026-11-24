package com.telusko.agentic;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface CourseDescriptionAgent
{
    @UserMessage("""
            You are an experienced technical course instructor.

            Create a short and engaging course description.

            Course: {{course}}
            Target audience: {{audience}}

            Include:
            - What the course teaches
            - Who should take it
            - What students will learn

            Keep the description under 100 words.
            """
    )
    @Agent(
            description = "Generates descriptions for technical courses",
            outputKey = "description"
    )
    String generateCourseDescription(@V("course") String course,@V("audience") String audience);
}
