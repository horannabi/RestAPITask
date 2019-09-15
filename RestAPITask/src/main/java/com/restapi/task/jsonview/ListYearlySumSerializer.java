package com.restapi.task.jsonview;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.restapi.task.dto.HouseFinanceDTO;

public class ListYearlySumSerializer extends JsonSerializer<HouseFinanceDTO> {
	
	@Override
	public void serialize(HouseFinanceDTO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		
		gen.writeFieldName("name");
		gen.writeString("주택금융 공급현황");
		
		gen.writeArrayFieldStart("");
		List<HouseFinanceDTO> list = value.getListHouseFinanceDTO();
		for(int i=0; i<list.size(); ) {
			gen.writeStartObject();
			gen.writeFieldName("year");
			gen.writeNumber(list.get(i).getYear());
			int sum =0;
			int cnt = 0;
			for(int j=i; j<list.size(); j++) {
				if(list.get(i).getYear() != list.get(j).getYear()) break;
				sum += list.get(j).getAmount();
				cnt++;
			}
			gen.writeFieldName("total_amount");
			gen.writeNumber(sum);
			
			gen.writeObjectFieldStart("detail_amount");
			for(int j=i; j<list.size(); j++) {
				if(list.get(i).getYear() != list.get(j).getYear()) break;
				gen.writeFieldName(list.get(j).getInstituteName());
				gen.writeNumber(list.get(j).getAmount());
			}
			gen.writeEndObject();
			gen.writeEndObject();
			i += cnt;
		}
		gen.writeEndArray();
	}
}
