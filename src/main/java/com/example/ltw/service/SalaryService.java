package com.example.ltw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ltw.entity.StaffBill;
import com.example.ltw.model.SalaryTotal;
@Service
public interface SalaryService {
	List<SalaryTotal> getAllSalaryTotal();//2
	List<SalaryTotal> getSalaryTotalByName(String name);//5
	Optional<StaffBill> getStaffBillId(long id);//4
	SalaryTotal getStaffBillByMonthAndYear(int month, int year, long id);//1
	List<SalaryTotal> getSalaryById(long id);//3
}
