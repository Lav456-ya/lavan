/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.nxtStayz.service;

import com.example.nxtStayz.model.Room;
import com.example.nxtStayz.repository.RoomJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoomJpaService {

    @Autowired
    private RoomJpaRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            return optionalRoom.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        }
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long roomId, Room room) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            room.setId(roomId); // Ensure the correct ID is set for update
            return roomRepository.save(room);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        }
    }

    public void deleteRoom(Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            roomRepository.deleteById(roomId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        }
    }
}
