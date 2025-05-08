package com.globalchat.chatapp.controller;

import com.globalchat.chatapp.domain.entity.ChatMessage;
import com.globalchat.chatapp.services.ChatMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/room/{roomId}/history")
    public List<ChatMessage> getHistory(@PathVariable Long roomId) {
        return chatMessageService.getHistoryByRoomId(roomId).orElse(null);

    }
}
