package com.globalchat.chatapp.services;

import com.globalchat.chatapp.domain.entity.ChatMessage;

import java.util.List;
import java.util.Optional;

public interface ChatMessageService {
    void saveMessage(ChatMessage message);

    Optional<List<ChatMessage>> getHistory();

    Optional<List<ChatMessage>> getHistoryByRoomId(Long roomId);
}
