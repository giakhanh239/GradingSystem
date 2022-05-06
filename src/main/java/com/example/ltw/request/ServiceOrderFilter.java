package com.example.ltw.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrderFilter {
    private long companyId = -1;
    private long serviceId = -1;
    private long staffBuildingId = -1;
    private long startDate = -1;
    private long endDate = -1;
}
