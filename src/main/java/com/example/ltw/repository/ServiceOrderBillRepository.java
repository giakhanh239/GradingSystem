package com.example.ltw.repository;

import com.example.ltw.entity.ServiceOrderBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceOrderBillRepository extends JpaRepository<ServiceOrderBill,  Long> {
    List<ServiceOrderBill> findServiceOrderBillByCompanyId(long companyId);
    Optional<ServiceOrderBill> findServiceOrderBillByCompanyIdAndAndDateCreate(long companyId, long createDate);
    List<ServiceOrderBill> findServiceOrderBillByDateCreateBetween(long startDate, long endDate);
    List<ServiceOrderBill> findServiceOrderBillByCompanyIdAndAndDateCreateBetween(long companyId, long startDate, long endDate);
}
