package com.restapi.task.service.institute.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.task.domain.Institute;
import com.restapi.task.service.institute.InstituteRepository;
import com.restapi.task.service.institute.InstituteService;

@Service("instituteServiceImpl")

public class InstituteServiceImpl implements InstituteService {
	
	@Autowired
	private InstituteRepository instituteRepository;
	
	@Override
	public void addInstituteList(List<Institute> institutes) {
		for(int i=0; i<institutes.size(); i++) {
			instituteRepository.save(institutes.get(i));
		}
	}

	@Override
	public List<Institute>  listInstitute() {
		return (List<Institute>) instituteRepository.findAll();
	}

	@Override
	public Institute getInstitute(String intituteCode) {
		return instituteRepository.findById(intituteCode).get();
	}	
}
