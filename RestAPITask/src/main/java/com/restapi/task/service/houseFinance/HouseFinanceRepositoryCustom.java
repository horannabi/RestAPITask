package com.restapi.task.service.houseFinance;

import java.util.List;

import com.restapi.task.domain.HouseFinance;
import com.restapi.task.domain.Institute;
import com.restapi.task.dto.HouseFinanceDTO;

public interface HouseFinanceRepositoryCustom {
	// API (3)
	public List<HouseFinanceDTO> listYearlySum();
	// API (4)
	public HouseFinanceDTO listYearlySumMax(int year);
	// API (5)
	public List<HouseFinanceDTO> listYearlyAvgMinMax(String instituteName);
}
