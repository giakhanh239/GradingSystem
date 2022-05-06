package com.example.ltw.service;

import java.util.List;
import java.util.Optional;

import com.example.ltw.entity.Room;
import com.example.ltw.entity.Service;

public interface RoomService {

	Room getById(Long id);

	void delete(Room entity);

	void deleteById(Long id);

	Optional<Room> findById(Long id);

	List<Room> findAll();

	<S extends Room> S save(S entity);
	public List<Room> getRoomByName(String name);
	public List<Room> getRoomContainigByName(String name);
}
