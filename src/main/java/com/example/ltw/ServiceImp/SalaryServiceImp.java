package com.example.ltw.serviceImp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.ltw.entity.StaffBill;
import com.example.ltw.entity.StaffBuilding;
import com.example.ltw.entity.StaffDayWork;
import com.example.ltw.model.SalaryTotal;
import com.example.ltw.repository.StaffBillRepository;
import com.example.ltw.repository.StaffBuildingRepository;
import com.example.ltw.repository.StaffDayWorkRepository;
import com.example.ltw.service.SalaryService;

@Service
public class SalaryServiceImp implements SalaryService {

	@Autowired
	StaffDayWorkRepository staffDayWorkRepository;
	@Autowired
	StaffBillRepository staffBillRepository;
	@Autowired
	StaffBuildingRepository staffBuildingRepository;

	@Override
	public List<SalaryTotal> getAllSalaryTotal() {
		// TODO Auto-generated method stub
		List<SalaryTotal> list = new ArrayList<SalaryTotal>();
		staffBillRepository.findAll().forEach(staffBill -> list.add(new SalaryTotal(staffBill)));

		return list;
	}

	@Override
	public List<SalaryTotal> getSalaryTotalByName(String name) {
		// TODO Auto-generated method stub
		List<SalaryTotal> list = new ArrayList<SalaryTotal>();
		staffBillRepository.findAll().forEach(staffBill -> list.add(new SalaryTotal(staffBill)));
		List<SalaryTotal> list2 = new ArrayList<SalaryTotal>();
		for(SalaryTotal salaryTotal : list) {
			if(salaryTotal.getName().contains(name)) {
				list2.add(salaryTotal);
			}
		}
		return list2;
	}

	@Override
	public Optional<StaffBill> getStaffBillId(long id) {

		Optional<StaffBill> staffBill = staffBillRepository.findById(id);
		// return staffBuilding.getStaffBillID();
		return staffBill;
	}

	@Override
	public SalaryTotal getStaffBillByMonthAndYear(int month, int year, long id) {
		id =  1;
		
		Calendar c = Calendar.getInstance();
		c.set(year, month, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		Calendar c2 = Calendar.getInstance();
		c2.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
		c2.set(Calendar.MILLISECOND, 0);
		Date startDate = c.getTime();
		Date endDate = c2.getTime();
		
	
		StaffBill staffBill = staffBillRepository.findAllByStaffBuildingIdAndMonth(id, startDate.getTime());
		//System.out.print(staffBill);
		if(staffBill == null) {
			System.out.print("abcaaasdqwesdasdzxcq"+"\n");
		StaffBuilding staffBuilding = staffBuildingRepository.findOneById(id);
		List<StaffDayWork> staffDayWorks = staffDayWorkRepository
				.findStaffDayWorkByDateBetweenAndStaffBuildingId(startDate.getTime(), endDate.getTime(), id);
		long dayWorks = staffDayWorks.size();
		long totalSalary = dayWorks*staffBuilding.getEarnPerDay();
		staffBill = new StaffBill(dayWorks,totalSalary,startDate.getTime(),staffDayWorks,staffBuilding);
		staffBillRepository.save(staffBill);
		}
		return new SalaryTotal(staffBill);
	}

	@Override
	public List<SalaryTotal> getSalaryById(long id) {
		// TODO Auto-generated method stub
		List<SalaryTotal> list = new ArrayList<SalaryTotal>();
		staffBillRepository.findAllByStaffBuildingId(id).forEach(staffBill -> list.add(new SalaryTotal(staffBill)));
		return list;
	}

}
