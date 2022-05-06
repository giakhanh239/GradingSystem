package com.example.ltw.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
	@Column(name ="Id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column (name ="TaxCode")
	private String taxCode;
	@NotEmpty(message = "Tên không được trống")
	@Column (name ="Name")
	private String name;
	@NotEmpty(message = "Vốn không được trống")
	@Column (name ="CharterCapital")
	private Long charterCapital;
	@NotEmpty(message = "Lĩnh vực kinh doanh không được trống")
	@Column (name ="BusinessAreas")
	private String businessAreas;
	@NotEmpty(message = "Số nhân viên không được trống")
	@Column (name ="NumberStaff")
	private int numberStaff;
	@Column (name ="RoomNumber")
	private String roomNumber;
	@NotEmpty(message = "Số điện thoại không được trống")
	@Column (name ="Phone")
	private String phone;
//	@NotEmpty(message = "Mã số thuế không được trống")
	@Column (name ="Area")
	private Long area;
	@OneToMany(mappedBy = "company")
	private List<Staff> staff;
	@JsonIgnore
	@OneToMany(mappedBy = "company")
	private List<Contract> listContract;
}
