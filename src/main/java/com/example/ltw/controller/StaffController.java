package com.example.ltw.controller;

import com.example.ltw.serviceImp.StaffServiceImpl;
import com.example.ltw.entity.Staff;
import com.example.ltw.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin (origins = "*")
@RestController
@RequestMapping("staffs")
public class StaffController {
	@Autowired
	private StaffServiceImpl staffServiceImpl;
	@GetMapping
	public List<Staff> getAllStaff() {
		return staffServiceImpl.findAll();
	}
	@PostMapping
	public Staff createStaff(@RequestBody Staff staff) {
		return staffServiceImpl.saveNew(staff);
	}

	// get staff by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
		Staff staff = staffServiceImpl.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Staff not exist with id :" + id));
		return ResponseEntity.ok(staff);
	}

	// update staff rest api

	@PutMapping("/{id}")
	public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff staffDetails){
		Staff staff = staffServiceImpl.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Staff not exist with id :" + id));
		staff.setCode(staffDetails.getCode());
		staff.setName(staffDetails.getName());
		staff.setDateOfBirth(staffDetails.getDateOfBirth());
		staff.setPhone(staffDetails.getPhone());
//
		Staff updatedStaff = staffServiceImpl.save(staff);
		return ResponseEntity.ok(updatedStaff);
	}

	// delete staff rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStaff(@PathVariable Long id){
		Staff staff = staffServiceImpl.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Staff not exist with id :" + id));

		staffServiceImpl.delete(staff);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	@RequestMapping(value = "/filter/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Staff>> findStaffByName(@PathVariable("name") String name){
		List<Staff> listStaff = staffServiceImpl.findStaffByName(name);
		return new ResponseEntity<List<Staff>>(listStaff,HttpStatus.OK);
	}
}


