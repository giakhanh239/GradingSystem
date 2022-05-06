package com.example.ltw.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltw.entity.Company;
import com.example.ltw.entity.Staff;
import com.example.ltw.repository.StaffRepository;
import com.example.ltw.service.StaffService;
@Service
public class StaffServiceImpl implements StaffService{
	@Autowired
	private StaffRepository staffRepository;

	@Override
	public <S extends Staff> S saveNew(S entity) {
		List<Staff> listStaff = staffRepository.findAll();
		boolean value =true;
		for(Staff staff: listStaff)
		{
			if(entity.getCode().equals(staff.getCode()))
			{
				value =false;
				break;
			}
		}
		if(value)
		{
			return staffRepository.save(entity);
		}else
			return null;
	}

	@Override
	public <S extends Staff> S save(S entity) {
		// TODO Auto-generated method stub
		return staffRepository.save(entity);
	}

	@Override
	public List<Staff> findAll() {
		return staffRepository.findAll();
	}

	@Override
	public Optional<Staff> findById(Long id) {
		return staffRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		staffRepository.deleteById(id);
	}

	@Override
	public void delete(Staff entity) {
		staffRepository.delete(entity);
	}

	@Override
	public List<Staff> findStaffByName(String name) {
		List<Staff> listStaff = staffRepository.findByNameContaining(name);
		return listStaff;
	}

}
