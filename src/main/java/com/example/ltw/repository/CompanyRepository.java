package com.example.ltw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ltw.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	List<Company> findByNameContaining(String name);
}
