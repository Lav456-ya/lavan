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

import com.example.nxtStayz.model.Hotel;
import com.example.nxtStayz.repository.HotelJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HotelJpaService {

    @Autowired
    private HotelJpaRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long hotelId) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            return optionalHotel.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
        }
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long hotelId, Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            hotel.setId(hotelId); // Ensure the correct ID is set for update
            return hotelRepository.save(hotel);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
        }
    }

    public void deleteHotel(Long hotelId) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            hotelRepository.deleteById(hotelId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
        }
    }
}
