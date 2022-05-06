package com.example.ltw.response;

import com.example.ltw.entity.ServiceOrder;

import java.util.Date;
import java.util.Map;

public class ServiceOrderBillResponse {
    private long id;
    private long total;
    private long time;
    private boolean status;
    private Map<ServiceOrder, Long> listServiceOrder;
    private int numberService;
}
