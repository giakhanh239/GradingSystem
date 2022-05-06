package com.example.ltw.serviceImp;

import com.example.ltw.entity.Company;
import com.example.ltw.entity.ServiceOrder;
import com.example.ltw.entity.ServiceOrderBill;
import com.example.ltw.entity.ServiceOrderBillRow;
import com.example.ltw.repository.CompanyRepository;
import com.example.ltw.repository.ServiceOrderBillRepository;
import com.example.ltw.repository.ServiceOrderBillRowRepository;
import com.example.ltw.repository.ServiceOrderRepository;
import com.example.ltw.request.ServiceOrderBillCreate;
import com.example.ltw.request.ServiceOrderBillFilter;
import com.example.ltw.response.ServiceOrderBillCreateResponse;
import com.example.ltw.service.ServiceOrderBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceOrderBillServiceImp implements ServiceOrderBillService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;
    @Autowired
    private ServiceOrderBillRepository serviceOrderBillRepository;
    @Autowired
    private ServiceOrderBillRowRepository serviceOrderBillRowRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ServiceOrderBillCreateResponse createServiceOrderBill(ServiceOrderBillCreate create) {
        Calendar c = Calendar.getInstance();
        c.set(create.year, create.month-1, 1,0,0,0);// ngày đâu của tháng
        c.set(Calendar.MILLISECOND,0);
        Calendar c2 =Calendar.getInstance();
        c2.set(create.year, create.month-1,c.getActualMaximum(Calendar.DAY_OF_MONTH),23,59,59); // ngày cuối cùng của tháng
        c2.set(Calendar.MILLISECOND,0);
        long startDate = c.getTime().getTime();
        long endDate = c2.getTime().getTime();
        long companyId = create.companyId;
        if(endDate >= System.currentTimeMillis()){
            return new ServiceOrderBillCreateResponse(400,"Không thể tạo bill cho những tháng sau tháng này!", null);
        }
        int totalBill = 0;
        List<ServiceOrderBill> resultList = new ArrayList<>();
        if(create.companyId == -1){
            List<Company> listCompany = companyRepository.findAll();
            for(Company company : listCompany){
                ServiceOrderBill serviceOrderBill = createServiceOrderBillById(company.getId(),startDate,endDate);
                if(serviceOrderBill == null){
                    //
                }else{
                    totalBill++;
                    resultList.add(serviceOrderBill);
                }
            }
            if(totalBill == 0){
                return new ServiceOrderBillCreateResponse(400,"Tất cà bill cho tháng đã được tạo rồi!", resultList);
            }else{
                return new ServiceOrderBillCreateResponse(200,"Các bill được tạo thành công", resultList);
            }
        }else{
            ServiceOrderBill serviceOrderBill = createServiceOrderBillById(companyId,startDate,endDate);
            if(serviceOrderBill == null){
                return new ServiceOrderBillCreateResponse(400,"Bill đã được tạo từ trước!", null);
            }else{
                resultList.add(serviceOrderBill);
                return new ServiceOrderBillCreateResponse(200,"Bill đã được tạo từ trước!", resultList);
            }
        }

    }

    private ServiceOrderBill createServiceOrderBillById(long companyId, long startTime, long endTime){
        double totalBill = 0;
        Optional<ServiceOrderBill> serviceOrderBillOp = serviceOrderBillRepository.findServiceOrderBillByCompanyIdAndAndDateCreate(companyId, startTime);
        if(serviceOrderBillOp.isPresent()){
            return null;
        }else{
            Company company = companyRepository.getById(companyId);
            long soNguoi = company.getNumberStaff();
            long dienTich = company.getArea();
            float tiSuat1 = 1;
            float tiSuat2 = 1;
            if(soNguoi > 10){
                tiSuat1 = (soNguoi-10);
            }

            if(dienTich > 100){
                tiSuat2 = (dienTich-100)/2;
            }
            float tiSuat = ((float) (Math.max(tiSuat1,tiSuat2)))/100 + 1;
            List<ServiceOrder> serviceOrderList = serviceOrderRepository
                    .findServiceOrdersByCompanyIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual
                            (companyId, endTime,startTime);
            List<ServiceOrderBillRow> listRow = new ArrayList<>();
            ServiceOrderBill serviceOrderBill = new ServiceOrderBill();
            serviceOrderBill.setCompanyId(companyId);
            serviceOrderBill.setStatus(false);
            serviceOrderBill.setDateCreate(startTime);
            for(int i = 0; i<serviceOrderList.size(); i++){
                ServiceOrder serviceOrder = serviceOrderList.get(i);
                double cost = serviceOrder.getService().getCost();
                int totalDay = 1+ (int) ((Math.min(endTime,serviceOrder.getEndDate()) - Math.max(startTime,serviceOrder.getStartDate())) / (1000 * 60 * 60* 24));
                ServiceOrderBillRow serviceOrderBillRow = new ServiceOrderBillRow();
                serviceOrderBillRow.setServiceOrderId(serviceOrder.getId());
                serviceOrderBillRow.setServiceName(serviceOrder.getService().getName());
                serviceOrderBillRow.setNumberDay(totalDay);
                serviceOrderBillRow.setDienTichSan(dienTich);
                serviceOrderBillRow.setSoNguoiSuDung(soNguoi);
                serviceOrderBillRow.setTiSuat(tiSuat);
                serviceOrderBillRow.setEndDate(Math.min(endTime,serviceOrder.getEndDate()));
                serviceOrderBillRow.setStartDate(Math.max(startTime,serviceOrder.getStartDate()));
                serviceOrderBillRow.setTotal( (long) ((float)cost*totalDay)*tiSuat);
                serviceOrderBillRow.setServiceOrderBill(serviceOrderBill);
                listRow.add(serviceOrderBillRow);
                totalBill += cost*totalDay;
            }
            serviceOrderBill.setTotal(totalBill);
            serviceOrderBill.setServiceOrderList(listRow);
            return serviceOrderBillRepository.save(serviceOrderBill);
        }
    }

    @Override
    public List<ServiceOrderBill> getAllServiceOrderBill() {
        return serviceOrderBillRepository.findAll();
    }

    @Override
    public List<ServiceOrderBill> filterServiceOrderBill(ServiceOrderBillFilter serviceOrderBillFilter) {
        if(serviceOrderBillFilter.companyId != -1 && serviceOrderBillFilter.startMonth != -1){
            int startMonth = serviceOrderBillFilter.startMonth;
            int startYear = serviceOrderBillFilter.startYear;
            int endMonth = serviceOrderBillFilter.endMonth;
            int endYear = serviceOrderBillFilter.endYear;
            Calendar c = Calendar.getInstance();
            c.set(startYear, startMonth-1, 1,0,0,0);// ngày đâu của tháng
            c.set(Calendar.MILLISECOND,0);
            Calendar c2 =Calendar.getInstance();
            c2.set(endYear, endMonth-1,c.getActualMaximum(Calendar.DAY_OF_MONTH),23,59,59); // ngày cuối cùng của tháng
            c2.set(Calendar.MILLISECOND,0);
            Date startDate = c.getTime();
            Date endDate = c2.getTime();
            return getServiceOrderBillByComanyAndTime(serviceOrderBillFilter.companyId,startDate.getTime(), endDate.getTime());
        }else if(serviceOrderBillFilter.startMonth != -1){
            int startMonth = serviceOrderBillFilter.startMonth;
            int startYear = serviceOrderBillFilter.startYear;
            int endMonth = serviceOrderBillFilter.endMonth;
            int endYear = serviceOrderBillFilter.endYear;
            Calendar c = Calendar.getInstance();
            c.set(startYear, startMonth-1, 1,0,0,0);// ngày đâu của tháng
            c.set(Calendar.MILLISECOND,0);
            Calendar c2 =Calendar.getInstance();
            c2.set(endYear, endMonth-1,c.getActualMaximum(Calendar.DAY_OF_MONTH),23,59,59); // ngày cuối cùng của tháng
            c2.set(Calendar.MILLISECOND,0);
            Date startDate = c.getTime();
            Date endDate = c2.getTime();
            return getServiceOrderBillByTime(startDate.getTime(), endDate.getTime());
        }else if(serviceOrderBillFilter.companyId != -1){
            return getServiceOrderBillByCompanyId(serviceOrderBillFilter.companyId);
        }else{
            return getAllServiceOrderBill();
        }
    }

    @Override
    public List<ServiceOrderBillRow> getDetail(long id) {
        return serviceOrderBillRowRepository.getServiceOrderBillRowByServiceOrderBillId(id);
    }

    @Override
    public List<ServiceOrderBill> getServiceOrderBillByCompanyId(long companyId) {
        List<ServiceOrderBill> list = serviceOrderBillRepository.findServiceOrderBillByCompanyId(companyId);
        return list;
    }

    @Override
    public List<ServiceOrderBill> getServiceOrderBillByTime(long startTime, long endTime) {
        List<ServiceOrderBill> list = serviceOrderBillRepository.findServiceOrderBillByDateCreateBetween(startTime,endTime);
        return list;
    }

    @Override
    public List<ServiceOrderBill> getServiceOrderBillByComanyAndTime(long comanyId, long startTime, long endTime) {
        List<ServiceOrderBill> list = serviceOrderBillRepository.findServiceOrderBillByCompanyIdAndAndDateCreateBetween(comanyId,startTime,endTime);
        return list;
    }

    @Override
    public String deleteServiceOrderBillById(long serviceOrderBillId) {
        if(serviceOrderBillRepository.findById(serviceOrderBillId).isPresent()){
            serviceOrderBillRepository.deleteById(serviceOrderBillId);
            return "Xóa thành công";
        }else{
            return "ID: " + serviceOrderBillId + " không tồn tại";
        }
    }

    @Override
    public String payServiceOrderBillById(long serviceOrderBillId) {
        Optional<ServiceOrderBill> serviceOrderBillOptional = serviceOrderBillRepository.findById(serviceOrderBillId);
        if(serviceOrderBillOptional.isPresent()){
            ServiceOrderBill serviceOrderBill = serviceOrderBillOptional.get();
            serviceOrderBill.setStatus(true);
            serviceOrderBillRepository.save(serviceOrderBill);
            return "Thanh toán thành công";
        }else{
            return "Thanh toán thất bại";
        }
    }
}
