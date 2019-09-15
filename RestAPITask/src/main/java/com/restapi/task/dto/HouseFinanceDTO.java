package com.restapi.task.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HouseFinanceDTO {
	private int hfNo;
	private int year;
	private int month;
	private int amount;
	private String instituteCode;
	private String instituteName;
	private List<HouseFinanceDTO> listHouseFinanceDTO;
}
