package com.telusko.agentic;

import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class Main {
    public static void main(String[] args)
    {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        CourseAdvisorAgent advisorAgent = AgenticServices.agentBuilder(CourseAdvisorAgent.class)
                .chatModel(model)
                .build();

        LearningPlanAgent planAgent = AgenticServices.agentBuilder(LearningPlanAgent.class)
                .chatModel(model)
                .build();

        DirectLearningPlanAgent directPlanAgent = AgenticServices.agentBuilder(DirectLearningPlanAgent.class)
                .chatModel(model)
                .build();

        CourseSupportAdvisor supervisor = AgenticServices.supervisorBuilder(CourseSupportAdvisor.class)
                .chatModel(model)
                .subAgents(advisorAgent, directPlanAgent)
                .build();

        String result = supervisor.handle("I know Java. What should I learn next?");
        System.out.println("Learning plan: " + result);


//        String recommendation = advisorAgent.recommend("I know Java basics and OOPs and i m a beginner");
//        System.out.println("Course recommendation: " + recommendation);
//        String plan = planAgent.createPlan(recommendation);
//        System.out.println("Learning plan: " + plan);
//        CourseWorkflows sequenceWorkflow = AgenticServices.sequenceBuilder(CourseWorkflows.class)
//                .subAgents(advisorAgent, planAgent)
//                .outputKey("learningPlan")
//                .build();
//        String result=sequenceWorkflow.createLearningPlan("I know Java basics and OOPs and I am a beginner");
//        System.out.println("Learning plan: " + result);

    }
}
