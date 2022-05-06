package com.example.ltw.entity;

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
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.springframework.web.bind.annotation.Mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "staffbill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaffBill {
	@Column(name ="Id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "dayWork")
	private long dayWork;
	@Column(name = "totalSalary")
	private long totalSalary;
	@Column(name = "month")
	private long month;
	@OneToMany(mappedBy = "staffBill")
	List<StaffDayWork> listDayWorks;
	@ManyToOne
	@JsonIgnore
	private StaffBuilding staffBuilding;
	public StaffBill(long dayWork, long totalSalary, long month, List<StaffDayWork> listDayWorks,
			StaffBuilding staffBuilding) {
		super();
		this.dayWork = dayWork;
		this.totalSalary = totalSalary;
		this.month = month;
		this.listDayWorks = listDayWorks;
		this.staffBuilding = staffBuilding;
	}
	
	
}



