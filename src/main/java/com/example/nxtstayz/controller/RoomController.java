/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.nxtStayz.controller;

import com.example.nxtStayz.model.Room;
import com.example.nxtStayz.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // API 1: GET /hotels/rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // API 2: GET /hotels/rooms/{roomId}
    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable Long roomId) {
        Optional<Room> room = roomService.getRoomById(roomId);
        return room.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API 3: POST /hotels/rooms
    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room addedRoom = roomService.addRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRoom);
    }

    // API 4: PUT /hotels/rooms/{roomId}
    @PutMapping("/{roomId}")
    public ResponseEntity<?> updateRoom(@PathVariable Long roomId, @RequestBody Room updatedRoom) {
        Optional<Room> room = roomService.updateRoom(roomId, updatedRoom);
        return room.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API 5: DELETE /hotels/rooms/{roomId}
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        boolean deleted = roomService.deleteRoom(roomId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
