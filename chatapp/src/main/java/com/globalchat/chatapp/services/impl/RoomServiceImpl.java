package com.globalchat.chatapp.services.impl;

import com.globalchat.chatapp.domain.entity.Room;
import com.globalchat.chatapp.repositories.RoomRepository;
import com.globalchat.chatapp.services.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room findRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    @Override
    public void createRoom(String name, String description) {
        Room room = Room.builder()
                .name(name)
                .description(description)
                .build();
        roomRepository.save(room);
    }
}
