package com.example.ltw.controller;

import com.example.ltw.entity.Contract;
import com.example.ltw.exception.ResourceNotFoundException;
import com.example.ltw.repository.ContractRepository;
import com.example.ltw.request.ContractFilter;
import com.example.ltw.request.ContractRequest;
import com.example.ltw.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("contract")
@CrossOrigin("*")
public class ContractController extends BaseController{
	@Autowired
    private ContractService contractServiceImpl;
	@Autowired
	private ContractRepository contractRepository;
    @RequestMapping(value = "")
    public ResponseEntity<List<Contract>> getAllContract(){
        logger.info("Client get all contract");
        return new ResponseEntity<List<Contract>>(contractServiceImpl.getAllContract(), HttpStatus.OK);
    }

    @PostMapping
    public Contract insertContract(@RequestBody ContractRequest request){
        return contractServiceImpl.insertContract(request);
    }
    @PutMapping("/update/{id}")
	public ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody ContractRequest contractDetails){
    	Contract contract = contractRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company not exist with id :" + id));
//    	contract.setName(contractDetails.getName());
//    	contract.setStartDate(contractDetails.getStartDate());
//    	contract.setEndDate(contractDetails.getEndDate());
//    	contract.setCostPerSquare(contractDetails.getCostPerSquare());
//    	contract.setTotalCost(contractDetails.getTotalCost());
//    	contract.setRoom(contractDetails.getRoom());
//    	contract.setCompany(contractDetails.getCompany());
////		
		Contract updatedContract = contractServiceImpl.editContract(contractDetails, contract);
		return ResponseEntity.ok(updatedContract);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteContractById(@PathVariable("id") Long id){
    	Contract contract = contractRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contract not exist with id :" + id));
		
    	contractRepository.delete(contract);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contract> getContractById(@PathVariable("id") Long id){
        ResponseEntity<Contract> contractResponseEntity = new ResponseEntity<Contract>(contractServiceImpl.getContractById(id).get(), HttpStatus.OK);
        return contractResponseEntity;
    }

    @RequestMapping(value = "/filter/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Contract>> getContractByName(@PathVariable("name") String name){
        List<Contract> listContract = contractServiceImpl.getContractByName(name);
        return new ResponseEntity<List<Contract>>(listContract,HttpStatus.OK);
    }
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public List<Contract> filterContract(@RequestBody ContractFilter filter){
        return contractServiceImpl.filterContract(filter);
    }
    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET) // done
    public String payContractBill(@PathVariable("id") long id){
        return contractServiceImpl.payContractById(id);
    }
    @GetMapping("/findStatus")
    public List<Contract> getCompanyStatus() {
		return contractServiceImpl.findStatus();
	}
}
