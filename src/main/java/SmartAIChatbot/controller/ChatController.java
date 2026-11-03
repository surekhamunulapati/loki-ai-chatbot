package SmartAIChatbot.controller;

import SmartAIChatbot.entity.ChatMessage;
import SmartAIChatbot.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // For normal page load
    @GetMapping("/")
    public String showChat(Model model) {
        model.addAttribute("messages", chatService.getAllMessages());
        return "chat";
    }

    // For OLD form submit - keeps redirect
    @PostMapping("/send-old")
    public String sendMessageOld(@RequestParam("message") String message) {
        if (message != null && !message.isBlank()) {
            chatService.sendMessage(message.trim());
        }
        return "redirect:/";
    }

    // For NEW ChatGPT UI - returns JSON (REAL AI answer)
    @PostMapping("/send")
    @ResponseBody
    public ChatMessage sendMessageAjax(@RequestParam("message") String message) {
        if (message == null || message.isBlank()) {
            return null;
        }
        return chatService.sendMessage(message.trim());
    }

    // API to get all history for left sidebar
    @GetMapping("/api/history")
    @ResponseBody
    public List<ChatMessage> getHistory() {
        return chatService.getAllMessages();
    }
}