package com.example.ltw.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ltw.entity.StaffBill;
import com.example.ltw.entity.StaffBuilding;
import com.example.ltw.entity.StaffDayWork;
import com.example.ltw.model.SalaryByMonthRequest;
import com.example.ltw.model.SalaryTotal;
import com.example.ltw.service.SalaryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("salary")
public class SalaryController {
	@Autowired
	SalaryService salaryService;

	@GetMapping("/all")
	public ResponseEntity<List<SalaryTotal>> getAllSalaryTotal() {
		return ResponseEntity.ok(salaryService.getAllSalaryTotal());
	}

	@RequestMapping(value = "/filter/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<SalaryTotal>> getSalaryTotalByName(@PathVariable("name") String name) {
		return ResponseEntity.ok(salaryService.getSalaryTotalByName(name));
	}

	@GetMapping("/daywork/{id}")
	public ResponseEntity<Optional<StaffBill>> getStaffBillById(@PathVariable Long id) {
		return ResponseEntity.ok(salaryService.getStaffBillId(id));
	}
	@CrossOrigin(origins = "*")	
	@PostMapping("/salaryByMonth")
	public ResponseEntity<SalaryTotal> getSalaryByMonthYearAndID(
			@RequestBody SalaryByMonthRequest salaryByMonthRequest) {
		return ResponseEntity.ok(salaryService.getStaffBillByMonthAndYear(salaryByMonthRequest.getMonth(),
				salaryByMonthRequest.getYear(), salaryByMonthRequest.getId()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<SalaryTotal>> getSalaryByStaffBuildingID(@PathVariable Long id) {
		return ResponseEntity.ok(salaryService.getSalaryById(id));
	}

}
