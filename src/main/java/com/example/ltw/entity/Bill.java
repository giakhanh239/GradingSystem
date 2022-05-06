package com.example.ltw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float totalPrice;
	private String date;
	private boolean status;
	@ManyToOne
	@JoinColumn(name = "service_order_id")
	private ServiceOrder serviceOrder;
	@ManyToOne
	@JoinColumn(name = " company_id")
	private Company company;
}
