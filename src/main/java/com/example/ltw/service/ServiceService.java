package com.example.ltw.service;

import com.example.ltw.entity.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<Service> getAllService();
    void updateService(Service service);
    Service createService(Service service);
    void deleteService(long serviceId);
    Optional<Service> getServiceById(long serviceId);
    List<Service> getServiceByName(String name);
}
