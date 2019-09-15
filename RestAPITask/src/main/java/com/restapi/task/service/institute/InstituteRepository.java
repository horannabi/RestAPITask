package com.restapi.task.service.institute;

import org.springframework.data.repository.CrudRepository;

import com.restapi.task.domain.Institute;

public interface InstituteRepository extends CrudRepository<Institute, String> {
	
}
