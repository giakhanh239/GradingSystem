package com.example.ltw.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ltw.entity.StaffBuilding;
import com.example.ltw.entity.StaffDayWork;

public interface StaffDayWorkRepository extends JpaRepository<StaffDayWork, Long> {
	@Query(value = "select * from staffbuilding where date >= :startDate AND date <= :endDate ", nativeQuery = true)
	List<StaffDayWork> findByMonth(@Param("startDate")Long startDate,@Param("endDate")Long endDate);
	List<StaffDayWork> findStaffDayWorkByDateBetweenAndStaffBuildingId(long startDate, long endDate, long id);
	
}
