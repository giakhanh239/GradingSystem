package com.example.ltw.entity;

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
import lombok.NoArgsConstructor;

@Entity
@Table(name ="staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name ="Code")
	private String code;
	@Column(name ="Name")
	private String name;
	@Column(name ="DateOfBirth")
	private String dateOfBirth;
	@Column(name ="Phone")
	private String phone;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
}
