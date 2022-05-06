package com.example.ltw.service;

import java.util.List;
import java.util.Optional;

import com.example.ltw.entity.Service;
import com.example.ltw.entity.StaffBuilding;

public interface StaffBuildingService {
	List<StaffBuilding> getAllStaffBuilding();
    void updateStaffBuilding(StaffBuilding staffBuilding);
    StaffBuilding createStaffBuilding(StaffBuilding staffBuilding);
    void deleteStaffBuilding(long staffBuildingId);
    Optional<StaffBuilding> getStaffBuildingById(long staffBuildingId);
    List<StaffBuilding> getStaffBuildingByName(String name);
}
