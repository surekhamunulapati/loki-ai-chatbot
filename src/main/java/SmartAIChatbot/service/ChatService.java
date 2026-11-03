package SmartAIChatbot.service;

import SmartAIChatbot.entity.ChatMessage;
import SmartAIChatbot.repo.ChatRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    // --- CHANGE HERE FOR GROQ (FREE) ---
    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.url:https://api.groq.com/openai/v1/chat/completions}")
    private String apiUrl;

    @Value("${groq.model:llama-3.1-8b-instant}")
    private String model;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public ChatMessage sendMessage(String message) {
        ChatMessage chat = new ChatMessage();
        chat.setUserMessage(message);

        // Get REAL AI answer
        String aiResponse = getAIResponse(message);
        chat.setBotResponse(aiResponse);

        // Save to DB
        return chatRepository.save(chat);
    }

    private String getAIResponse(String userMessage) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            Map<String, Object> requestBody = Map.of(
                    "model", model,
                    "messages", List.of(
                            Map.of("role", "system", "content", "You are Smart AI Chatbot. Answer like ChatGPT - helpful, concise, clear."),
                            Map.of("role", "user", "content", userMessage)
                    ),
                    "max_tokens", 800,
                    "temperature", 0.7
            );

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

            JsonNode root = mapper.readTree(response.getBody());
            return root.path("choices").get(0).path("message").path("content").asText().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI Error: " + e.getMessage() + ". Please check your Groq API key in application.properties";
        }
    }

    public List<ChatMessage> getAllMessages() {
        return chatRepository.findAll();
    }
}