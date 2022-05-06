package com.example.ltw.controller;

import com.example.ltw.entity.Service;
import com.example.ltw.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "service")
public class ServiceController extends BaseController {
    @Autowired
    ServiceService serviceService;

    @RequestMapping(value = "/list")
    public ResponseEntity<List<Service>> getAllService(){
        logger.info("Client get all service");
        return new ResponseEntity<List<Service>>(serviceService.getAllService(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Service> insertService(@RequestBody Service service){
        logger.info("Client create new Service: ");
        return new ResponseEntity<Service>(serviceService.createService(service), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> insertService(@PathVariable("id") int id ,@RequestBody Service service){
        service.setId(id);
        serviceService.createService(service);
        return new ResponseEntity("create success", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteServiceById(@PathVariable("id") int id){
        if(id == 1 || id == 2){
            return new ResponseEntity<String>("đây là dịch vụ mặc định, không thể xóa", HttpStatus.OK);
        }
        serviceService.deleteService(id);
        return new ResponseEntity<String>("xóa thành công", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Service> getServiceById(@PathVariable("id") int id){
        ResponseEntity<Service> serviceResponseEntity = new ResponseEntity<Service>(serviceService.getServiceById(id).get(), HttpStatus.OK);
        return serviceResponseEntity;
    }

    @RequestMapping(value = "/filter/{key}", method = RequestMethod.GET) // filter both name and id
    public ResponseEntity<List<Service>> getServiceByName(@PathVariable("key") String key){
        try{
            int keyId = Integer.parseInt(key);
            Optional<Service> serviceOp = serviceService.getServiceById(keyId);
            List<Service> listService = serviceService.getServiceByName(key);
            if(serviceOp.isPresent()){
                for(Service service : listService){
                    if(service.getId() == serviceOp.get().getId()){
                        listService.add(serviceOp.get());
                        break;
                    }
                }
            }
            return new ResponseEntity<List<Service>>(listService,HttpStatus.OK);
        }catch (NumberFormatException e){
            List<Service> listService = serviceService.getServiceByName(key);
            return new ResponseEntity<List<Service>>(listService,HttpStatus.OK);
        }
    }

}
