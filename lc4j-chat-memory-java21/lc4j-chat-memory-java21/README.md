# LangChain4j Chat Memory — Java 21 Console Demo

A small **Core Java 21 + Maven** IntelliJ IDEA project. No Spring Boot.

## Requirements
- JDK 21
- IntelliJ IDEA with Maven support
- Internet access and an OpenAI API key with available credits

## Open in IntelliJ IDEA
1. Extract the ZIP.
2. IntelliJ IDEA → **File → Open** → choose the extracted `lc4j-chat-memory-java21` folder (or `pom.xml`).
3. Allow IntelliJ to import/reload Maven dependencies.
4. **File → Project Structure → Project SDK** → choose JDK 21.
5. Open `src/main/java/com/telusko/chatmemory/ChatMemoryApp.java`.
6. **Run → Edit Configurations** → choose the `ChatMemoryApp` application → **Environment variables** → add `OPENAI_API_KEY=your_actual_key`.
7. Run `ChatMemoryApp.main()` and type messages in the Run console.

## Try it
```
You: My name is Rahul.
AI: Nice to meet you, Rahul!
You: What is my name?
AI: Your name is Rahul.
You: exit
```
Responses vary. The memory is **in-process only**: it resets when you restart the program.

## How it works
- `Assistant.java` defines an AI Service; `{{it}}` inserts the input text.
- `OpenAiChatModel` calls the model.
- `MessageWindowChatMemory.withMaxMessages(10)` retains up to ten recent messages.
- `AiServices.builder(...).chatLanguageModel(...).chatMemory(...)` connects the pieces.

## LangChain4j version
This project uses LangChain4j **1.18.1** and the `ChatModel` API (`.chatModel(model)`).

## Optional command-line run
```bash
mvn compile exec:java
```

If Maven reports dependency-resolution issues, ensure Maven is online and reload the project. No API key is included in this ZIP.
