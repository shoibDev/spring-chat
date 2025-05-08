package com.globalchat.chatapp.controller;

import com.globalchat.chatapp.domain.entity.ChatMessage;
import com.globalchat.chatapp.domain.entity.Room;
import com.globalchat.chatapp.services.ChatMessageService;
import com.globalchat.chatapp.services.RoomService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WebSocketController {

    private final ChatMessageService chatMessageService;
    private final RoomService roomService;

    public WebSocketController(
            ChatMessageService chatMessageService,
            RoomService roomService
    ) {
        this.chatMessageService = chatMessageService;
        this.roomService = roomService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        chatMessageService.saveMessage(message);
        return message;
    }

    @MessageMapping("/room/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public ChatMessage sendRoomMessage(@DestinationVariable Long roomId, ChatMessage message) {
        Room Room = roomService.findRoomById(roomId);
        message.setRoom(Room);
        message.setTimestamp(LocalDateTime.now());
        chatMessageService.saveMessage(message);
        return message;
    }
}
