package com.example.ltw.controller;

import com.example.ltw.entity.ServiceOrder;
import com.example.ltw.request.ServiceOrderFilter;
import com.example.ltw.request.ServiceOrderRequest;
import com.example.ltw.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "service-order")
@CrossOrigin(origins = "*")
public class ServiceOrderController {

    @Autowired
    ServiceOrderService serviceOrderService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<ServiceOrder>> getAllServiceOrder(){
        return new ResponseEntity<List<ServiceOrder>>(serviceOrderService.getAllServiceOrder(), HttpStatus.OK);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ServiceOrder>> getServiceOrderByCompany(@PathVariable("id") long companyId){
        return new ResponseEntity<List<ServiceOrder>>(serviceOrderService.getListServiceOrderByCompany(companyId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServiceOrder> getServiceOrderById(@PathVariable("id") long serviceOrderId){
        ServiceOrder serviceOrder;
        if(serviceOrderService.getServiceOrderById(serviceOrderId).isPresent()){
            serviceOrder = serviceOrderService.getServiceOrderById(serviceOrderId).get();
        }else{
            serviceOrder = null;
        }
        return new ResponseEntity<ServiceOrder>(serviceOrder, HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertServiceOrder(@RequestBody ServiceOrderRequest request){
        return serviceOrderService.insertServiceOrder(request);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public boolean update(@PathVariable("id") long id, @RequestBody ServiceOrderRequest request){
        return serviceOrderService.updateServiceOrder(id,request);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public List<ServiceOrder> filterServiceOrder(@RequestBody ServiceOrderFilter filter){
        return serviceOrderService.filterServiceOrder(filter);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteServiceOrderById(@PathVariable("id") long serviceOrderId){
        return serviceOrderService.deleteServiceOrder(serviceOrderId);
    }
}
