package com.example.ltw.service;

import java.util.List;
import java.util.Optional;

import com.example.ltw.entity.Staff;

public interface StaffService {

	void deleteById(Long id);

	Optional<Staff> findById(Long id);

	List<Staff> findAll();

	<S extends Staff> S save(S entity);
	<S extends Staff> S saveNew(S entity);
	void delete(Staff entity);
	public List<Staff> findStaffByName(String name);
}
