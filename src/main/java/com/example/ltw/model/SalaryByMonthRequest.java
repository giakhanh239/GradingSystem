package com.example.ltw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryByMonthRequest {
	private int month;
	private int year;
	private Long id;
	public SalaryByMonthRequest(int month, int year, Long id) {
		super();
		this.month = month;
		this.year = year;
		this.id = id;
	}
	public SalaryByMonthRequest() {
		super();
	}
	
}
