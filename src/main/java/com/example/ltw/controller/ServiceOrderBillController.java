package com.example.ltw.controller;

import com.example.ltw.entity.ServiceOrderBill;
import com.example.ltw.entity.ServiceOrderBillRow;
import com.example.ltw.request.ServiceOrderBillCreate;
import com.example.ltw.request.ServiceOrderBillFilter;
import com.example.ltw.response.ServiceOrderBillCreateResponse;
import com.example.ltw.service.ServiceOrderBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "order-bill")
public class ServiceOrderBillController {
    @Autowired
    ServiceOrderBillService serviceOrderBillService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ServiceOrderBillCreateResponse createServiceOrderBill(@RequestBody ServiceOrderBillCreate request){
        ServiceOrderBillCreateResponse  serviceOrderBillCreateResponse = serviceOrderBillService.createServiceOrderBill(request);
        return serviceOrderBillCreateResponse;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET) // done
    public List<ServiceOrderBill> getAllServiceOrderBill(){
        List<ServiceOrderBill> list = serviceOrderBillService.getAllServiceOrderBill();
        return list;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public List<ServiceOrderBill> filterServiceOrderBill(@RequestBody ServiceOrderBillFilter serviceOrderBillFilter){
        return serviceOrderBillService.filterServiceOrderBill(serviceOrderBillFilter);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE) // done
    public String deleteServiceOrderBillById(@PathVariable("id") long id){
        return serviceOrderBillService.deleteServiceOrderBillById(id);
    }

    @RequestMapping(value = "/pay-bill/{id}", method = RequestMethod.GET) // done
    public String payServiceOrderBill(@PathVariable("id") long id){
        return serviceOrderBillService.payServiceOrderBillById(id);
    }
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public List<ServiceOrderBillRow> getBillDetail(@PathVariable("id") long id){
        return serviceOrderBillService.getDetail(id);
    }
}
