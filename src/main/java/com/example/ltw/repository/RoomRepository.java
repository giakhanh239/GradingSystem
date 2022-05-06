package com.example.ltw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ltw.entity.Room;
import com.example.ltw.entity.Staff;

public interface RoomRepository extends JpaRepository<Room, Long>{
	List<Room> findByRoomName(String name);
	List<Room> findByRoomNameContaining(String name);
}
