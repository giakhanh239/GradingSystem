package com.example.ltw.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ltw.entity.StaffBuilding;
import com.example.ltw.repository.StaffBuildingRepository;
import com.example.ltw.service.StaffBuildingService;

public class StaffBuildingServiceImp implements StaffBuildingService {
	@Autowired
	StaffBuildingRepository staffBuildingRepository;

	@Override
	public List<StaffBuilding> getAllStaffBuilding() {
		// TODO Auto-generated method stub
		return staffBuildingRepository.findAll();
	}

	@Override
	public void updateStaffBuilding(StaffBuilding staffBuilding) {
		// TODO Auto-generated method stub
		staffBuildingRepository.save(staffBuilding);
		
	}

	@Override
	public StaffBuilding createStaffBuilding(StaffBuilding staffBuilding) {
		// TODO Auto-generated method stub
		return staffBuildingRepository.save(staffBuilding);
	}

	@Override
	public void deleteStaffBuilding(long staffBuildingId) {
		// TODO Auto-generated method stub
		staffBuildingRepository.deleteById(staffBuildingId);
		
	}

	@Override
	public Optional<StaffBuilding> getStaffBuildingById(long staffBuildingId) {
		// TODO Auto-generated method stub
		return staffBuildingRepository.findById(staffBuildingId);
	}

	@Override
	public List<StaffBuilding> getStaffBuildingByName(String name) {
		// TODO Auto-generated method stub
		return staffBuildingRepository.findByNameContaining(name);
	}

}
