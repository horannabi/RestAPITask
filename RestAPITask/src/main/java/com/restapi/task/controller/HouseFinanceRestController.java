package com.restapi.task.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.task.common.CSVParser;
import com.restapi.task.common.GetObjectMapper;
import com.restapi.task.domain.HouseFinance;
import com.restapi.task.domain.Institute;
import com.restapi.task.dto.HouseFinanceDTO;
import com.restapi.task.jsonview.ListYearlyAvgMinMaxSerializer;
import com.restapi.task.jsonview.ListYearlySumMaxSerializer;
import com.restapi.task.jsonview.ListYearlySumSerializer;
import com.restapi.task.service.houseFinance.HouseFinanceService;
import com.restapi.task.service.institute.InstituteService;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/housefinance/*")
@NoArgsConstructor
public class HouseFinanceRestController {
	@Autowired
	private HouseFinanceService houseFinanceService;
	@Autowired
	private InstituteService instituteService;
	
	@GetMapping(value="json/addBulkData")
	public String addBulkData() {
		Map<String, Object> map = CSVParser.parser();
		instituteService.addInstituteList((List<Institute>)map.get("instituteList"));
		houseFinanceService.addYearlyData((List<HouseFinance>)map.get("yearlyData"));				
		return "OK";
	}
	
	@GetMapping(value="json/listInstitute")
	public List<Institute> listInstitute(){
		System.out.println("(2) /housefinance/json/listInstitute : GET");
		return instituteService.listInstitute();
	}
	
	@GetMapping(value="json/listYearlySum")
	public String listYearlySum() throws JsonProcessingException{
		System.out.println("(3) /housefinance/json/listYearlySum : GET");
		ObjectMapper objectMapper = GetObjectMapper.getObjectMapper((JsonSerializer<HouseFinanceDTO>)new ListYearlySumSerializer());
		HouseFinanceDTO houseFinanceDTO = new HouseFinanceDTO(0, 0, 0, 0, null, null, houseFinanceService.listYearlySum());
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(houseFinanceDTO); 
	}
	
	@GetMapping(value="json/listYearlySumMax/{year}")
	public String listYearlySumMax(@PathVariable int year) throws JsonProcessingException {
		System.out.println("(4) /housefinance/json/listYearlySumMax : GET");
		ObjectMapper objectMapper = GetObjectMapper.getObjectMapper((JsonSerializer<HouseFinanceDTO>)new ListYearlySumMaxSerializer());
		HouseFinanceDTO houseFinanceDTO = houseFinanceService.listYearlySumMax(year);
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(houseFinanceDTO); 
	}
	
	@GetMapping(value="json/listYearlyAvgMinMax/{instituteName}")
	public String listYearlyAvgMinMax(@PathVariable  String instituteName) throws JsonProcessingException {
		System.out.println("(5) /housefinance/json/listYearlyAvgMinMax : GET");
		ObjectMapper objectMapper = GetObjectMapper.getObjectMapper((JsonSerializer<HouseFinanceDTO>)new ListYearlyAvgMinMaxSerializer());
		HouseFinanceDTO houseFinanceDTO = houseFinanceService.listYearlyAvgMinMax(instituteName);
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(houseFinanceDTO); 
	}

}
