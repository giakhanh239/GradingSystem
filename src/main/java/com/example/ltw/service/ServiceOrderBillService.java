package com.example.ltw.service;

import com.example.ltw.entity.ServiceOrderBill;
import com.example.ltw.entity.ServiceOrderBillRow;
import com.example.ltw.request.ServiceOrderBillCreate;
import com.example.ltw.request.ServiceOrderBillFilter;
import com.example.ltw.response.ServiceOrderBillCreateResponse;

import java.util.List;

public interface ServiceOrderBillService{
    ServiceOrderBillCreateResponse createServiceOrderBill(ServiceOrderBillCreate create);
    List<ServiceOrderBill> getAllServiceOrderBill();
    List<ServiceOrderBill> getServiceOrderBillByCompanyId(long companyId);
    List<ServiceOrderBill> getServiceOrderBillByTime(long startTime, long endTime);
    List<ServiceOrderBill> getServiceOrderBillByComanyAndTime(long comanyId, long startTime, long endTime);
    String deleteServiceOrderBillById(long serviceOrderBillId);
    String payServiceOrderBillById(long serviceOrderBillId);
    List<ServiceOrderBill> filterServiceOrderBill(ServiceOrderBillFilter serviceOrderBillFilter);
    List<ServiceOrderBillRow> getDetail(long id);
}
