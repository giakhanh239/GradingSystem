package com.example.ltw.service;

import java.util.List;
import java.util.Optional;

import com.example.ltw.entity.Contract;
import com.example.ltw.entity.ServiceOrder;
import com.example.ltw.request.ContractFilter;
import com.example.ltw.request.ContractRequest;
import com.example.ltw.request.ServiceOrderFilter;

public interface ContractService {

	List<Contract> getContractByName(String name);

	Optional<Contract> getContractById(Long Id);

	void deleteContract(Long Id);

	Contract createContract(Contract contract);

	List<Contract> getAllContract();
	Contract insertContract (ContractRequest request);
	public List<Contract> filterContract(ContractFilter filter);
	Contract editContract (ContractRequest request, Contract contract);

	String payContractById(long contractId);

	List<Contract> findStatus();
}
