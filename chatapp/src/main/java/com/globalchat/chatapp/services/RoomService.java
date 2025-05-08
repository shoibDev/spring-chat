package com.globalchat.chatapp.services;

import com.globalchat.chatapp.domain.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();

    Room findRoomById(Long roomId);

    void createRoom(String name, String description);
}
