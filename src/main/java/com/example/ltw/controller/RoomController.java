package com.example.ltw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ltw.serviceImp.RoomServiceImpl;
import com.example.ltw.entity.Company;
import com.example.ltw.entity.Room;
import com.example.ltw.entity.Service;
import com.example.ltw.exception.ResourceNotFoundException;
import com.example.ltw.repository.CompanyRepository;
@RestController
@RequestMapping("room")
@CrossOrigin("*")
public class RoomController {
	@Autowired
	private RoomServiceImpl roomServiceImpl;
	@GetMapping
	public List<Room> getAllRoom() {
		return roomServiceImpl.findAll();
	}
	@PostMapping
	public Room createRoom(@RequestBody Room room) {
		return roomServiceImpl.save(room);
	}
	
	// get room by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
		Room room = roomServiceImpl.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("room not exist with id :" + id));
		return ResponseEntity.ok(room);
	}
	
	// update room rest api
	
	@PutMapping("/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails){
		Room room = roomServiceImpl.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("room not exist with id :" + id));
		room.setRoomName(roomDetails.getRoomName());
		room.setSquare(roomDetails.getSquare());
		room.setFloor(roomDetails.getFloor());
		room.setDescription(roomDetails.getDescription());
		room.setFloor(roomDetails.getFloor());
		room.setLevel(roomDetails.getLevel());
//		
		Room updatedRoom = roomServiceImpl.save(room);
		return ResponseEntity.ok(updatedRoom);
	}
	
	// delete Room rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteRoom(@PathVariable Long id){
		Room room = roomServiceImpl.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("room not exist with id :" + id));
		
		roomServiceImpl.delete(room);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	@RequestMapping(value = "/filter/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> getRoomyName(@PathVariable("name") String name){
        List<Room> listRoom = roomServiceImpl.getRoomContainigByName(name);
        return new ResponseEntity<List<Room>>(listRoom,HttpStatus.OK);
    }
}
