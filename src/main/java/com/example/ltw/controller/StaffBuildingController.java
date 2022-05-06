package com.example.ltw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ltw.entity.Service;
import com.example.ltw.entity.StaffBuilding;
import com.example.ltw.repository.StaffBuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@CrossOrigin (origins = "*")
@RestController
@RequestMapping("staffsbuilding")
public class StaffBuildingController {
	@Autowired
	private StaffBuildingRepository staffBuildingRepository;
	@CrossOrigin (origins = "*")
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public List<StaffBuilding> getAllStaffBuilding() {
		return staffBuildingRepository.findAll();
	}
	@PostMapping("/create")
	public StaffBuilding createStaffBuilding(@RequestBody StaffBuilding staffBuilding) {
		return staffBuildingRepository.save(staffBuilding);
	}
	
	// get staffBuilding by id rest api
	@CrossOrigin (origins = "*")
	@GetMapping("/{id}")
	public ResponseEntity<StaffBuilding> getStaffBuildingById(@PathVariable Long id) {
		StaffBuilding staffBuilding = staffBuildingRepository.findById(id).get();
		return ResponseEntity.ok(staffBuilding);
	}
	
	// update staffBuilding rest api
	@CrossOrigin (origins = "*")
	@PostMapping("/update/{id}")
	public ResponseEntity<StaffBuilding> updateStaffBuilding(@PathVariable Long id, @RequestBody StaffBuilding staffBuildingDetails){
		StaffBuilding staffBuilding = staffBuildingRepository.findById(id).get();
		staffBuilding.setCodeStaff(staffBuildingDetails.getCodeStaff());
		staffBuilding.setName(staffBuildingDetails.getName());
		staffBuilding.setDateOfBirth(staffBuildingDetails.getDateOfBirth());
		staffBuilding.setAddress(staffBuildingDetails.getAddress());
		staffBuilding.setPhone(staffBuildingDetails.getPhone());
		staffBuilding.setLevel(staffBuildingDetails.getLevel());
		staffBuilding.setPosition(staffBuildingDetails.getPosition());
//		
		StaffBuilding updatedStaffBuilding = staffBuildingRepository.save(staffBuilding);
		return ResponseEntity.ok(updatedStaffBuilding);
	}
	
	
	// delete staffBuilding rest api
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStaffBuilding(@PathVariable Long id){
		StaffBuilding staffBuilding = staffBuildingRepository.findById(id).get();
		
		staffBuildingRepository.delete(staffBuilding);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	@RequestMapping(value = "/filter/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<StaffBuilding>> getStaffBuildingByName(@PathVariable("name") String name){
		List<StaffBuilding> staffBuildings = staffBuildingRepository.findByNameContaining(name);
		return new ResponseEntity<List<StaffBuilding>>(staffBuildings,HttpStatus.OK);
	}

}


