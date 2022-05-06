package com.example.ltw.service;

import com.example.ltw.entity.ServiceOrder;
import com.example.ltw.request.ServiceOrderFilter;
import com.example.ltw.request.ServiceOrderRequest;

import java.util.List;
import java.util.Optional;

public interface ServiceOrderService {
    List<ServiceOrder> getAllServiceOrder();
    List<ServiceOrder> getListServiceOrderByCompany(long companyId);
    Optional<ServiceOrder> getServiceOrderById(long serviceOrderId);
    String insertServiceOrder(ServiceOrderRequest request);
    String deleteServiceOrder(long serviceOrderId);
    boolean updateServiceOrder(long serviceOrderId, ServiceOrderRequest serviceOrder);
    List<ServiceOrder> filterServiceOrder(ServiceOrderFilter filter);
    String update(ServiceOrderRequest request, long id);
}
