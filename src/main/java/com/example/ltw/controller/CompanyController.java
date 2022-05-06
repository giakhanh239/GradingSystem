package com.example.ltw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ltw.exception.ResourceNotFoundException;
import com.example.ltw.entity.Company;
import com.example.ltw.entity.Staff;
import com.example.ltw.repository.CompanyRepository;
import com.example.ltw.service.CompanyService;

@CrossOrigin (origins = "*")
@RestController
@RequestMapping("company")
public class CompanyController {
	@Autowired
	private CompanyService companyServiceImpl;
	@GetMapping
	public List<Company> getAllCompany() {
		return companyServiceImpl.findAll();
	}
	@PostMapping
	public Company createCompany(@RequestBody Company company) {
		return companyServiceImpl.save(company);
	}
	
	// get company by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
		Company company = companyServiceImpl.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company not exist with id :" + id));
		return ResponseEntity.ok(company);
	}
	
	// update company rest api
	
	@PutMapping("/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails){
		Company company = companyServiceImpl.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company not exist with id :" + id));
		company.setTaxCode(companyDetails.getTaxCode());
		company.setName(companyDetails.getName());
		company.setCharterCapital(companyDetails.getCharterCapital());
		company.setBusinessAreas(companyDetails.getBusinessAreas());
		company.setNumberStaff(companyDetails.getNumberStaff());
		company.setRoomNumber(companyDetails.getRoomNumber());
		company.setArea(companyDetails.getArea());
		company.setPhone(companyDetails.getPhone());
//		
		Company updatedCompany = companyServiceImpl.save(company);
		return ResponseEntity.ok(updatedCompany);
	}
	
	// delete company rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCompany(@PathVariable Long id){
		Company company = companyServiceImpl.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company not exist with id :" + id));
		
		companyServiceImpl.delete(company);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	@RequestMapping(value = "/filter/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> findStaffByName(@PathVariable("name") String name){
        List<Company> listCompany = companyServiceImpl.findByNameContaining(name);
        return new ResponseEntity<List<Company>>(listCompany,HttpStatus.OK);
    }
}


