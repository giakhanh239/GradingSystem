package com.example.ltw.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "staffDayWork")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaffDayWork {
	@Column(name ="id")
	@Id
	private long id;
	@Column(name = "date")
	private long date;
	@ManyToOne
	@JsonIgnore
	StaffBill staffBill;
	@ManyToOne
	@JsonIgnore
	StaffBuilding staffBuilding ;
}
