package com.globalchat.chatapp.services.impl;

import com.globalchat.chatapp.domain.entity.ChatMessage;
import com.globalchat.chatapp.repositories.ChatMessageRepository;
import com.globalchat.chatapp.repositories.RoomRepository;
import com.globalchat.chatapp.services.ChatMessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final RoomRepository roomRepository;

    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository, RoomRepository roomRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void saveMessage(ChatMessage message) {
        chatMessageRepository.save(message);
    }

    @Override
    public Optional<List<ChatMessage>> getHistory() {
        return Optional.of(chatMessageRepository.findAllByOrderByTimestampDesc());
    }

    @Override
    public Optional<List<ChatMessage>> getHistoryByRoomId(Long roomId) {
        return Optional.of(chatMessageRepository.findByRoomIdOrderByTimestampDesc(roomId));
    }
}
