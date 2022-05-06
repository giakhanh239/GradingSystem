package com.example.ltw.entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Controller;


import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "staffbuilding")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffBuilding {
	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "CodeStaff")
	private String codeStaff;
	@Column(name = "Name")
	private String name;
	@Column(name = "DateOfBirth")
	private String dateOfBirth;
	@Column(name = "Address")
	private String address;
	@Column(name = "Phone")
	private String phone;
	@Column(name = "Level")
	private int level;
	@Column(name = "Position")
	private String position;
	@Column(name = "earnPerDay")
	private long earnPerDay;
	@OneToMany(mappedBy = "staffBuilding")
	List<StaffBill> staffBill;
	
}

