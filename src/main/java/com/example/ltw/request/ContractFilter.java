package com.example.ltw.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractFilter {
	private Long companyId=-1L;
	private Long roomId=-1L;
	private Date startDate = null;
    private Date endDate = null;
}
