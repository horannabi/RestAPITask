package com.restapi.task.service.institute;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.restapi.task.domain.Institute;

public interface InstituteService {
	// API (1)
	public void addInstituteList(List<Institute> institutes);
	// API (2)
	public List<Institute> listInstitute();
	// For Test
	public Institute getInstitute(String intituteCode);
}
