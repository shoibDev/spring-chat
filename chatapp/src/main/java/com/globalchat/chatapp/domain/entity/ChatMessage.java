package com.globalchat.chatapp.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String message;
    private MessageType type;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
}
