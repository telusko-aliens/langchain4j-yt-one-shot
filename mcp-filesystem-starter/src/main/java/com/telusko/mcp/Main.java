package com.telusko.mcp;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("MCP Filesystem Demo");
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

//        McpTransport transport = StdioMcpTransport.builder()
//                .command(List.of(
//                        "cmd", "/c",
//                        "npx", "-y",
//                        "@modelcontextprotocol/server-filesystem",
//                        "--read-only",
//                        "D:\\mcp-demo-files"
//                ))
//                .logEvents(true)
//                .build();

        McpTransport transport = StdioMcpTransport.builder()
                .command(List.of(
                        "java",
                        "-jar",
                        "D:\\AI Basics\\course-mcp-server-starter-java21\\course-mcp-server-starter\\target\\course-mcp-server-1.0-SNAPSHOT.jar"
                ))
                .logEvents(true)
                .build();


        McpClient mcpClient = DefaultMcpClient.builder()
                .key("course-server")
                .transport(transport)
                .build();
        try
        {
            McpToolProvider toolProvider = McpToolProvider.builder()
                    .mcpClients(mcpClient)
                    .build();

//            FileAssistant assistant = AiServices.builder(FileAssistant.class)
//                    .chatModel(model)
//                    .toolProvider(toolProvider)
//                    .build();

//            String answer = assistant.chat("Read notes.txt and summarize it in three points.");
//            System.out.println(answer);

            CourseAssistant courseAssistant = AiServices.builder(CourseAssistant.class)
                    .chatModel(model)
                    .toolProvider(toolProvider)
                    .build();
            String answer = courseAssistant.chat(
                    "What is the price and duration of the Java course?"
            );

            System.out.println("\nAI Response:");
            System.out.println(answer);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            mcpClient.close();
        }

    }
}
