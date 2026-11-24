package com.telusko.toolcalling.tools;

import dev.langchain4j.agent.tool.Tool;

public class CourseTools {

    @Tool("Get the price of a training course")
    public String getCoursePrice(String courseName) {

        System.out.println("Tool called: getCoursePrice");

        if (courseName.equalsIgnoreCase("Java")) {
            return "3000 INR";
        }

        if (courseName.equalsIgnoreCase("Python")) {
            return "2500 INR";
        }

        return "Course price not available";
    }

    @Tool("Get the duration of a training course")
    public String getCourseDuration(String courseName) {

        System.out.println("Tool called: getCourseDuration");

        if (courseName.equalsIgnoreCase("Java")) {
            return "3 months";
        }

        if (courseName.equalsIgnoreCase("Python")) {
            return "2 months";
        }

        return "Course duration not available";
    }
}
