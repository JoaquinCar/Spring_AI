package com.example.demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    private final ChatClient chatClient;

    public AIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String chat(String prompt) {
        /*
        return chatClient.prompt(prompt)
                         .call()
                         .content();
        */

        // Simulación de análisis de IA
        return """
            [SIMULATED AI RESPONSE]
            Analysis for prompt:
            "%s"

            Strengths: solid experience, adaptability, continuous learning.
            Weaknesses: explore emerging technologies, increase collaboration.

            Suggestion: dive into system design, mentoring, and community engagement.
            """.formatted(prompt);
    }
}

