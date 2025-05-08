package com.globalchat.chatapp.repositories;

import com.globalchat.chatapp.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
