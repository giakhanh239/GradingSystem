package com.example.ltw.response;

import com.example.ltw.entity.ServiceOrderBill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ServiceOrderBillCreateResponse {
    int code;
    String message;
    List<ServiceOrderBill> listBill;
}
