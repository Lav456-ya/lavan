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

import com.example.nxtStayz.model.Hotel;
import com.example.nxtStayz.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // API 1: GET /hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // API 2: GET /hotels/{hotelId}
    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getHotelById(@PathVariable Long hotelId) {
        Optional<Hotel> hotel = hotelService.getHotelById(hotelId);
        return hotel.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API 3: POST /hotels
    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        Hotel addedHotel = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedHotel);
    }

    // API 4: PUT /hotels/{hotelId}
    @PutMapping("/{hotelId}")
    public ResponseEntity<?> updateHotel(@PathVariable Long hotelId, @RequestBody Hotel updatedHotel) {
        Optional<Hotel> hotel = hotelService.updateHotel(hotelId, updatedHotel);
        return hotel.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API 5: DELETE /hotels/{hotelId}
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        boolean deleted = hotelService.deleteHotel(hotelId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
