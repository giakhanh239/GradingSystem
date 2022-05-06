package com.example.ltw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ltw.entity.Contract;
import com.example.ltw.entity.ServiceOrder;

public interface ContractRepository extends JpaRepository<Contract, Long>{
	List<Contract> findByNameContaining(String name);
	List<Contract> findContractsByCompanyId(Long id);
    List<Contract> findContractsByRoomId(Long roomId);
    List<Contract> findContractsByCompanyIdAndRoomId(Long companyId, Long roomId);
    List<Contract> findContractsByCompanyIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long companyId, long endDate, long startDate);
}
