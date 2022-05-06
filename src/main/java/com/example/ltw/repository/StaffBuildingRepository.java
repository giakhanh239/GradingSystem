package com.example.ltw.repository;

import com.example.ltw.entity.StaffBuilding;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffBuildingRepository extends JpaRepository<StaffBuilding, Long>{
	
	List<StaffBuilding> findByNameContaining(String name);
	
	@Query(value = "select * from staffbuilding where id = :id ", nativeQuery = true)
	StaffBuilding findOneById(@Param("id") long id);
}
