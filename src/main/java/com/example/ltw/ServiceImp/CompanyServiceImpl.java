package com.example.ltw.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltw.entity.Company;
import com.example.ltw.entity.Room;
import com.example.ltw.repository.CompanyRepository;
import com.example.ltw.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public <S extends Company> S save(S entity) {
		List<Company> listRoom = companyRepository.findAll();
		boolean value =true;
		for(Company room: listRoom)
		{
			if(entity.getName().equals(room.getName()) || entity.getTaxCode()==(room.getTaxCode()))
			{
				value =false;
				break;
			}
		}
		if(value)
		{
			return companyRepository.save(entity);	
		}else
			return null;
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Optional<Company> findById(Long id) {
		return companyRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		companyRepository.deleteById(id);
	}

	@Override
	public void delete(Company entity) {
		companyRepository.delete(entity);
	}

	@Override
	public List<Company> findByNameContaining(String name) {
		// TODO Auto-generated method stub
		return companyRepository.findByNameContaining(name);
	}
	
}
