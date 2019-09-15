package com.restapi.task.jsonview;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.restapi.task.dto.HouseFinanceDTO;

public class ListYearlyAvgMinMaxSerializer extends JsonSerializer<HouseFinanceDTO> {
	
	@Override
	public void serialize(HouseFinanceDTO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		
		gen.writeFieldName("bank");
		gen.writeString(String.valueOf(value.getInstituteName()));
		
		gen.writeArrayFieldStart("support_amount");

		for(int i=0; i<value.getListHouseFinanceDTO().size(); i++) {
			gen.writeStartObject();
			gen.writeFieldName("year");
			gen.writeNumber(value.getListHouseFinanceDTO().get(i).getYear());
			
			gen.writeFieldName("amount");
			gen.writeNumber(value.getListHouseFinanceDTO().get(i).getAmount());
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}
}
