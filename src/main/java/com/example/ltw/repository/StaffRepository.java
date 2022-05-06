package com.example.ltw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ltw.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long>{
	List<Staff> findByNameContaining(String name);
}
