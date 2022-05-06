package com.example.ltw.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltw.entity.Room;
import com.example.ltw.entity.Staff;
import com.example.ltw.repository.RoomRepository;
import com.example.ltw.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public <S extends Room> S save(S entity) {
		List<Room> listRoom = roomRepository.findAll();
		boolean value =true;
		for(Room room: listRoom)
		{
			if(entity.getRoomName().equals(room.getRoomName()) && entity.getLevel()==(room.getLevel()))
			{
				value =false;
				break;
			}
		}
		if(value)
		{
			return roomRepository.save(entity);	
		}else
			return null;
	}

	@Override
	public List<Room> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public Optional<Room> findById(Long id) {
		return roomRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		roomRepository.deleteById(id);
	}

	@Override
	public void delete(Room entity) {
		roomRepository.delete(entity);
	}
	@Override
	public Room getById(Long id) {
		return roomRepository.getById(id);
	}

	@Override
	public List<Room> getRoomByName(String name) {
		// TODO Auto-generated method stub
		return roomRepository.findByRoomName(name);
	}

	@Override
	public List<Room> getRoomContainigByName(String name) {
		List<Room> listRoom = roomRepository.findByRoomNameContaining(name);
		return listRoom;
	}
	
}
