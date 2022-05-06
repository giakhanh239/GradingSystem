package com.example.ltw.request;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import com.example.ltw.entity.Contract;
import com.example.ltw.entity.Staff;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractRequest {
	private String name;
	private String description;
	private Date startDate;
	private float costPerSquare;
	private float square;
	private float totalCost;
	private Date endDate;
	private Long companyId;
	private Long roomId;
}
