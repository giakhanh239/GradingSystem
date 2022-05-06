package com.example.ltw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ltw.entity.Staff;
import com.example.ltw.entity.StaffBill;
import com.example.ltw.entity.StaffBuilding;

public interface StaffBillRepository extends JpaRepository<StaffBill, Long> {
	@Query(value = "select * from staffbill where staff_building_id = :id ", nativeQuery = true)
	List<StaffBill> findAllByStaffBuildingId(@Param("id") long  id);
	@Query(value = "select * from staffbill where staff_building_id = :id And month = :month", nativeQuery = true)
	StaffBill findAllByStaffBuildingIdAndMonth(@Param("id") long  id,@Param("month") long  month);
	
}
