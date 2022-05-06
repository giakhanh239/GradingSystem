package com.example.ltw.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ServiceOrderRequest {
    private long startDate;
    private long endDate;
    private long staffBuildingId;
    private long companyId;
    private long serviceId;
}
