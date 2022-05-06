package com.example.ltw.repository;

import com.example.ltw.entity.ServiceOrderBillRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceOrderBillRowRepository extends JpaRepository<ServiceOrderBillRow, Long> {
    List<ServiceOrderBillRow> getServiceOrderBillRowByServiceOrderBillId(long id);
}
