package com.restapi.task.service.houseFinance.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.task.domain.HouseFinance;
import com.restapi.task.domain.Institute;
import com.restapi.task.dto.HouseFinanceDTO;
import com.querydsl.core.Tuple;
import com.restapi.task.service.houseFinance.HouseFinanceRepository;
import com.restapi.task.service.houseFinance.HouseFinanceService;

@Service("houseFinanceServiceImpl")
public class HouseFinanceServiceImpl implements HouseFinanceService {
	
	@Autowired
	private HouseFinanceRepository houseFinanceRepository;
	
	
	@Override
	public void addYearlyData(List<HouseFinance> houseFinances) {
		for(int i=0; i<houseFinances.size(); i++) {
			houseFinanceRepository.save(houseFinances.get(i));
		}
	}

	@Override
	public List<HouseFinanceDTO> listYearlySum() {
		return houseFinanceRepository.listYearlySum();
	}

	@Override
	public HouseFinanceDTO listYearlySumMax(int year) {
		return houseFinanceRepository.listYearlySumMax(year);
	}

	@Override
	public HouseFinanceDTO listYearlyAvgMinMax(String instituteName) {
		List<HouseFinanceDTO> list = houseFinanceRepository.listYearlyAvgMinMax(instituteName);
		List<HouseFinanceDTO> listMinMax = new ArrayList<HouseFinanceDTO>();
		listMinMax.add(list.get(0));					// MIN
		listMinMax.add(list.get(list.size()-1)); 	// MAX
		HouseFinanceDTO houseFinanceDTO = new HouseFinanceDTO(0, 0, 0, 0, null, list.get(0).getInstituteName(), listMinMax);
		return houseFinanceDTO;
	}

}
