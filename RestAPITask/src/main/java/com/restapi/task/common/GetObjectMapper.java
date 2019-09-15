package com.restapi.task.common;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.restapi.task.dto.HouseFinanceDTO;

public class GetObjectMapper {
	
	public static ObjectMapper getObjectMapper(JsonSerializer<HouseFinanceDTO> jsonSerializer) {

        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(HouseFinanceDTO.class, jsonSerializer);
        objectMapper.registerModule(simpleModule);

        return objectMapper;
    }
	
}
