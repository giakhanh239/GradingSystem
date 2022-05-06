package com.example.ltw.serviceImp;
import com.example.ltw.entity.Service;
import com.example.ltw.repository.ServiceRepositoty;
import com.example.ltw.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImp implements ServiceService {

    @Autowired
    ServiceRepositoty serviceRepositoty;
    //hi

    @Override
    public List<Service> getAllService() {
        return serviceRepositoty.findAll();
    }

    @Override
    public void updateService(Service service) {
        serviceRepositoty.save(service);
    }

    @Override
    public Service createService(Service service) {
        return serviceRepositoty.save(service);
    }

    @Override
    public void deleteService(long serviceId) {
        serviceRepositoty.deleteById(serviceId);
    }

    @Override
    public Optional<Service> getServiceById(long serviceId) {
        return serviceRepositoty.findById(serviceId);
    }

    @Override
    public List<Service> getServiceByName(String name) {
        return serviceRepositoty.findByNameContaining(name);
    }
}
