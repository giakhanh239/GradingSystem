package com.example.ltw.repository;

import com.example.ltw.entity.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
    List<ServiceOrder> findServiceOrdersByCompanyId(long id);
    List<ServiceOrder> findServiceOrdersByServiceId(long serviceId);
    List<ServiceOrder> findServiceOrdersByStaffBuildingId(long staffbuildingId);
    List<ServiceOrder> findServiceOrdersByCompanyIdAndServiceId(long companyId, long serviceId);
    List<ServiceOrder> findServiceOrdersByCompanyIdAndStaffBuildingId(long companyId, long staffBuildingId);
    List<ServiceOrder> findServiceOrdersByServiceIdAndStaffBuildingId(long serviceId, long staffBuildingId);
    List<ServiceOrder> findServiceOrdersByCompanyIdAndAndServiceIdAndStaffBuildingId(long companyId, long serviceId, long staffBuildingId);
    List<ServiceOrder> findServiceOrdersByCompanyIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(long companyId, long endDate, long startDate);
 }
