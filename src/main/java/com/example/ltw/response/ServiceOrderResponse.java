package com.example.ltw.response;

import com.example.ltw.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrderResponse {
    private long id;
    private long startDate;
    private long endDate;
    private Service service;
    private Company company;
    private StaffBuilding staffBuilding;
    private ServiceOrderBill serviceOrderBill;
}
