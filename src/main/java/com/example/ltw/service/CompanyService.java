package com.example.ltw.service;

import java.util.List;
import java.util.Optional;

import com.example.ltw.entity.Company;
import com.example.ltw.model.SalaryTotal;

public interface CompanyService {

	void deleteById(Long id);

	Optional<Company> findById(Long id);

	List<Company> findAll();

	<S extends Company> S save(S entity);

	void delete(Company entity);
	List<Company> findByNameContaining(String name);

}
