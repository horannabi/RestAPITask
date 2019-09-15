package com.restapi.task.service.houseFinance;

import java.util.List;

import com.restapi.task.domain.HouseFinance;
import com.restapi.task.domain.Institute;
import com.restapi.task.dto.HouseFinanceDTO;

public interface HouseFinanceService {
	// API (1)
	public void addYearlyData(List<HouseFinance> houseFinances);
	// API (3)
	public List<HouseFinanceDTO> listYearlySum();
	// API (4)
	public HouseFinanceDTO listYearlySumMax(int year);
	// API (5)
	public HouseFinanceDTO listYearlyAvgMinMax(String instituteName);

}
