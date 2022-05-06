package com.example.ltw.model;

import com.example.ltw.entity.StaffBill;
import com.example.ltw.entity.StaffBuilding;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class SalaryTotal {
	private Long id;
	private String codeStaff;
	private String name;
	private int level;
	private String position;
	private Long salaryTotal;
	private Long month;
	
	private StaffBuilding staffBuilding;
	private StaffBill staffBill;

	public SalaryTotal() {
		super();  
	}

	public SalaryTotal(StaffBill staffBill) {
		super();
		this.staffBuilding = staffBill.getStaffBuilding();
		this.month = staffBill.getMonth();
		this.id = staffBill.getId();
		this.codeStaff = staffBuilding.getCodeStaff();
		this.name = staffBuilding.getName();
		this.level = staffBuilding.getLevel();
		this.position = staffBuilding.getPosition();
		this.salaryTotal = staffBill.getDayWork()*staffBuilding.getEarnPerDay();
		this.staffBill = staffBill;
	}
}
