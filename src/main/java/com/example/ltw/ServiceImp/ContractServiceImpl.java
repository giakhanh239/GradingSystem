package com.example.ltw.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.ltw.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltw.entity.Company;
import com.example.ltw.entity.Contract;
import com.example.ltw.entity.Room;
import com.example.ltw.entity.ServiceOrder;
import com.example.ltw.request.ContractFilter;
import com.example.ltw.request.ContractRequest;
import com.example.ltw.request.ServiceOrderFilter;
import com.example.ltw.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	@Autowired
	private StaffBuildingRepository staffBuildingRepository;
	@Autowired
	private ServiceRepositoty serviceRepositoty;
	@Override
	public List<Contract> getAllContract() {
		return contractRepository.findAll();
	}

	@Override
	public Contract insertContract(ContractRequest request) {
		Optional<Company> companyOp = companyRepository.findById(request.getCompanyId());
		Optional<Room> roomOp = roomRepository.findById(request.getRoomId());
		List<Contract> listContract = contractRepository.findAll();
		int value = 1;
		Contract contract = new Contract();
		if (roomOp.isPresent() && companyOp.isPresent()) {
			contract.setCompany(companyOp.get());
			contract.setRoom(roomOp.get());
			contract.setName(request.getName());
			contract.setDescription(request.getDescription());
			contract.setStartDate(request.getStartDate());
			contract.setSquare(request.getSquare());
			contract.setCostPerSquare(request.getCostPerSquare());
			contract.setTotalCost(request.getTotalCost());
			contract.setEndDate(request.getEndDate());
//            System.out.println("contract"+contract.toString());
			System.out.println("contract" + listContract.size());

		}
		for (int i = 0; i < listContract.size(); i++) {
			if (contract.getCompany().equals(listContract.get(i).getCompany()))
//            		System.out.println("no nnnn");
			{
				value = -1;
				break;
			}
		}
//            System.out.println("list"+listContract.get(0).toString());
		if(value == 1) {
			Room roomUpdate = roomOp.get();
			if(roomUpdate.getRestSquare()<contract.getSquare())
			{
				return null;
			}else {
				contractRepository.save(contract);
				listContract = contractRepository.findAll();
				long totalSquare =0;
				for(int i=0;i<listContract.size();i++)
				{
					totalSquare+=listContract.get(i).getSquare();
				}
				roomUpdate.setRestSquare(roomUpdate.getSquare()-totalSquare);
				roomRepository.save(roomUpdate);

				// thêm 2 dịch vụ
				ServiceOrder serviceOrder = new ServiceOrder();
				serviceOrder.setCompany(companyOp.get());
				serviceOrder.setService(serviceRepositoty.findById((long)1).get());
				serviceOrder.setStaffBuilding(staffBuildingRepository.findById((long)1).get());
				serviceOrder.setStartDate(request.getStartDate().getTime());
				serviceOrder.setEndDate(request.getEndDate().getTime());
				serviceOrderRepository.save(serviceOrder);
				//
				ServiceOrder serviceOrder1 = new ServiceOrder();
				serviceOrder1.setCompany(companyOp.get());
				serviceOrder1.setService(serviceRepositoty.findById((long)2).get());
				serviceOrder1.setStaffBuilding(staffBuildingRepository.findById((long)1).get());
				serviceOrder1.setStartDate(request.getStartDate().getTime());
				serviceOrder1.setEndDate(request.getEndDate().getTime());
				serviceOrderRepository.save(serviceOrder1);

				return contract;
			}
		}
		else
		return null;
	}
	
	@Override
	public Contract editContract(ContractRequest request, Contract contract) {
		Optional<Company> companyOp = companyRepository.findById(request.getCompanyId());
		Optional<Room> roomOp = roomRepository.findById(request.getRoomId());
		if (roomOp.isPresent() && companyOp.isPresent()) {

			contract.setCompany(companyOp.get());
			contract.setRoom(roomOp.get());
			contract.setName(request.getName());
			contract.setDescription(request.getDescription());
			contract.setStartDate(request.getStartDate());
			contract.setSquare(request.getSquare());
			contract.setCostPerSquare(request.getCostPerSquare());
			contract.setTotalCost(request.getTotalCost());
			contract.setEndDate(request.getEndDate());
			Contract updateContract = contractRepository.save(contract);
			return updateContract;
		}
		return null;
	}

	@Override
	public void deleteContract(Long Id) {
		contractRepository.deleteById(Id);
	}

	@Override
	public Optional<Contract> getContractById(Long Id) {
		return contractRepository.findById(Id);
	}

	@Override
	public List<Contract> getContractByName(String name) {
		return contractRepository.findByNameContaining(name);
	}

	@Override
	public Contract createContract(Contract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contract> filterContract(ContractFilter filter) {
		List<Contract> list = new ArrayList<>();
		Long companyId = filter.getCompanyId();
		Long roomId = filter.getRoomId();
		if (filter.getRoomId() != -1 && filter.getCompanyId() != -1) {
			list = contractRepository.findContractsByCompanyIdAndRoomId(companyId, roomId);
		} else if (filter.getRoomId() != -1) {
			list = contractRepository.findContractsByRoomId(roomId);
		} else if (filter.getCompanyId() != -1) {
			list = contractRepository.findContractsByCompanyId(companyId);
		} else {
			list = contractRepository.findAll();
		}

//        List<Contract> resultList = new ArrayList<>();
//        if(filter.getStartDate() != null && filter.getEndDate() != null){
//            Date startDate = filter.getStartDate();
//            Date endDate = filter.getEndDate();
//            for(int i = 0; i<list.size(); i++){
//                Contract contract = list.get(i);
//                if((contract.getStartDate() <= startDate.getTime() && contract.getEndDate() >= startDate.getTime()) ||
//                        (contract.getStartDate() <= endDate.getTime() && contract.getEndDate() >= endDate.getTime() )){
//                    resultList.add(contract);
//                }
//            }
//        }
		return list;
	}
	@Override
	public String payContractById(long contractId) {
        Optional<Contract> contractOptional = contractRepository.findById(contractId);
        if(contractOptional.isPresent()){
            Contract contract = contractOptional.get();
            contract.setStatus(true);
            contractRepository.save(contract);
            return "Thanh toán thành công";
        }else{
            return "Thanh toán thất bại";
        }
    }
	@Override
	public List<Contract> findStatus(){
		List<Contract> listContract = new ArrayList<>();
		List<Contract> dataContract = contractRepository.findAll();
		for(Contract contract: dataContract) {
			if(contract.isStatus()==true)
				listContract.add(contract);
		}
		return listContract;
	}
}
