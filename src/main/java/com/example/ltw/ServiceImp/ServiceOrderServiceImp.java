package com.example.ltw.serviceImp;

import com.example.ltw.entity.Company;
import com.example.ltw.entity.ServiceOrder;
import com.example.ltw.entity.StaffBuilding;
import com.example.ltw.repository.CompanyRepository;
import com.example.ltw.repository.ServiceOrderRepository;
import com.example.ltw.repository.ServiceRepositoty;
import com.example.ltw.repository.StaffBuildingRepository;
import com.example.ltw.request.ServiceOrderFilter;
import com.example.ltw.request.ServiceOrderRequest;
import com.example.ltw.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceOrderServiceImp implements ServiceOrderService {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;
    @Autowired
    StaffBuildingRepository staffBuildingRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ServiceRepositoty serviceRepositoty;

    @Override
    public List<ServiceOrder> getAllServiceOrder() {
        return serviceOrderRepository.findAll();
    }

    @Override
    public List<ServiceOrder> getListServiceOrderByCompany(long companyId) {
        return serviceOrderRepository.findServiceOrdersByCompanyId(companyId);
    }

    @Override
    public Optional<ServiceOrder> getServiceOrderById(long serviceOrderId) {
        return serviceOrderRepository.findById(serviceOrderId);
    }

    @Override
    public String insertServiceOrder(ServiceOrderRequest request) {
        long serviceId = request.getServiceId();
        long companyId = request.getCompanyId();
        long staffBuildingId = request.getStaffBuildingId();

        Optional<com.example.ltw.entity.Service> serviceOp = serviceRepositoty.findById(serviceId);
        if(!serviceOp.isPresent()){
            return "mã dịch vụ không tồn tại";
        }
        Optional<Company> companyOp = companyRepository.findById(companyId);
        if(!companyOp.isPresent()){
            return "mã công ty không tồn tại";
        }
        Optional<StaffBuilding> staffBuildingOp = staffBuildingRepository.findById(staffBuildingId);
        if(!staffBuildingOp.isPresent()){
            return "mã nhân viên không tồn tại";
        }
        if(serviceOp.isPresent() && companyOp.isPresent() && staffBuildingOp.isPresent()){
            ServiceOrder serviceOrder = new ServiceOrder();
            serviceOrder.setService(serviceOp.get());
            serviceOrder.setCompany(companyOp.get());
            serviceOrder.setStaffBuilding(staffBuildingOp.get());
            serviceOrder.setStartDate(request.getStartDate());
            serviceOrder.setEndDate(request.getEndDate());
            serviceOrderRepository.save(serviceOrder);
            return "insert thành công";
        }
        return "insert lỗi";
    }

    @Override
    public String deleteServiceOrder(long serviceOrderId) {
        if(serviceOrderRepository.findById(serviceOrderId).isPresent()) {
            serviceOrderRepository.deleteById(serviceOrderId);
            return "Xóa thành công";
        }
        return "ID " + serviceOrderId + " không tồn tại";
    }

    @Override
    public boolean updateServiceOrder(long serviceOrderId, ServiceOrderRequest request) {
        Optional<ServiceOrder> orderOptional = serviceOrderRepository.findById(serviceOrderId);
        if(orderOptional.isPresent()) {
            ServiceOrder serviceOrder = orderOptional.get();
            StaffBuilding staffBuilding = staffBuildingRepository.findById(request.getStaffBuildingId()).get();
            serviceOrder.setStaffBuilding(staffBuilding);
            Company company = companyRepository.findById(request.getCompanyId()).get();
            serviceOrder.setCompany(company);
            com.example.ltw.entity.Service service = serviceRepositoty.findById(request.getServiceId()).get();
            serviceOrder.setService(service);
            serviceOrder.setStartDate(request.getStartDate());
            serviceOrder.setEndDate(request.getEndDate());
            serviceOrderRepository.save(serviceOrder);

        }
        return false;
    }

    @Override
    public List<ServiceOrder> filterServiceOrder(ServiceOrderFilter filter) {
        List<ServiceOrder> list = new ArrayList<>();
        long companyId = filter.getCompanyId();
        long serviceId = filter.getServiceId();
        long staffBuildingId = filter.getStaffBuildingId();
        if(filter.getServiceId() != -1 && filter.getCompanyId() != -1 && filter.getStaffBuildingId() != -1){
            list = serviceOrderRepository.findServiceOrdersByCompanyIdAndAndServiceIdAndStaffBuildingId(companyId,serviceId,staffBuildingId);
        }else if(filter.getServiceId() != -1 && filter.getCompanyId() != -1 ){
            list = serviceOrderRepository.findServiceOrdersByCompanyIdAndServiceId(companyId,serviceId);
        }else if(filter.getServiceId() != -1 && filter.getStaffBuildingId() != -1){
            list = serviceOrderRepository.findServiceOrdersByServiceIdAndStaffBuildingId(serviceId,staffBuildingId);
        }else if(filter.getCompanyId() != -1 && filter.getStaffBuildingId() != -1){
            list = serviceOrderRepository.findServiceOrdersByCompanyIdAndStaffBuildingId(companyId,staffBuildingId);
        }else if(filter.getServiceId() != -1 ){
            list = serviceOrderRepository.findServiceOrdersByServiceId(serviceId);
        }else if(filter.getCompanyId() != -1 ){
            list = serviceOrderRepository.findServiceOrdersByCompanyId(companyId);
        }else if(filter.getStaffBuildingId() != -1){
            list = serviceOrderRepository.findServiceOrdersByStaffBuildingId(staffBuildingId);
        }else{
            list = serviceOrderRepository.findAll();
        }

        List<ServiceOrder> resultList = new ArrayList<>();
        if(filter.getStartDate() != -1 && filter.getEndDate() != -1){
            long startDate = filter.getStartDate();
            long endDate = filter.getEndDate();
            for(int i = 0; i<list.size(); i++){
                ServiceOrder serviceOrder = list.get(i);
                if((serviceOrder.getStartDate() >= startDate && serviceOrder.getStartDate() <= endDate )||
                        (serviceOrder.getEndDate() >= startDate && serviceOrder.getEndDate() <= endDate )){
                    resultList.add(serviceOrder);
                }
            }
            return resultList;
        }
        return list;
    }

    @Override
    public String update(ServiceOrderRequest request, long id) {
        return null;
    }
}
