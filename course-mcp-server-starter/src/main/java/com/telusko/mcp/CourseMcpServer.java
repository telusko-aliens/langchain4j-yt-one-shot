package com.telusko.mcp;

import com.telusko.mcp.services.CourseServices;
import io.modelcontextprotocol.json.McpJsonDefaults;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static io.modelcontextprotocol.spec.McpSchema.JSONRPCResponse.result;

public class CourseMcpServer {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Course MCP Server Starter");
        CourseServices courseServices= new CourseServices();
        StdioServerTransportProvider transport =
                new StdioServerTransportProvider(
                        McpJsonDefaults.getMapper()
                );
        McpSyncServer server = McpServer.sync(transport)
                .serverInfo("course-mcp-server", "1.0.0")
                .capabilities(
                        McpSchema.ServerCapabilities.builder()
                                .tools(true)
                                .build()
                )
                .build();

        Map<String, Object> emptySchema = Map.of(
                "type", "object",
                "properties", Map.of()
        );

        var listCoursesTool = McpServerFeatures.SyncToolSpecification.builder()
                .tool(McpSchema.Tool.builder(
                                "listCourses", emptySchema)
                        .description("List all available courses")
                        .build())
                .callHandler((exchange, request) -> {

                    // Execute our actual Java method.
                    String courses = courseServices.listCourses();

                    // Convert the String into an MCP tool response.
                    return result(courses);
                })
                .build();

        server.addTool(listCoursesTool);
        Map<String, Object> courseSchema = Map.of(
                "type", "object",
                "properties", Map.of(
                        "courseName", Map.of(
                                "type", "string",
                                "description", "Name of the course"
                        )
                ),
                "required", List.of("courseName")
        );
        var priceTool = SyncToolSpecification.builder()
                .tool(McpSchema.Tool.builder(
                                "getCoursePrice", courseSchema)
                        .description("Get the price of a course")
                        .build())
                .callHandler((exchange, request) -> {

                    // Read the course name from tool arguments.
                    String name = (String)
                            request.arguments().get("courseName");

                    // Execute the actual Java method.
                    String price =
                            courseServices.getCoursePrice(name);

                    // Convert Java String into MCP tool response.
                    return result(price);
                })
                .build();

        // Expose the tool to MCP clients.
        server.addTool(priceTool);
        var durationTool = SyncToolSpecification.builder()
                .tool(McpSchema.Tool.builder(
                                "getCourseDuration", courseSchema)
                        .description("Get the duration of a course")
                        .build())
                .callHandler((exchange, request) -> {

                    // Extract courseName from the request.
                    String name = (String)
                            request.arguments().get("courseName");

                    // Execute the actual Java method.
                    String duration =
                            courseServices.getCourseDuration(name);

                    // Return the result to the MCP client.
                    return result(duration);
                })
                .build();

        // Expose the tool to MCP clients.
        server.addTool(durationTool);

        System.err.println("Course MCP Server ready");

        new CountDownLatch(1).await();


    }

    // Converts the String returned by our Java method
// into a response that an MCP client can understand.
    private static McpSchema.CallToolResult result(String text) {

        return McpSchema.CallToolResult.builder()
                .content(List.of(
                        new McpSchema.TextContent(text)
                ))
                .build();
    }
}
