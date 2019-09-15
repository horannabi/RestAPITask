package com.restapi.task.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restapi.task.domain.HouseFinance;
import com.restapi.task.domain.Institute;

public class CSVParser {

	public static Map<String, Object> parser(){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\data\\data_task3.csv"),"euc-kr"));
			String[] arr = br.readLine().split(",");
			int count = arr.length;
			
			List<Institute> instituteList = new ArrayList<Institute>();						
			
			//:: 은행목록 저장('bnk101'부터시작)
			int codeNo = 101;
			Institute institute;
			for(int i=2; i<arr.length; i++) {
				if(i==2) {
					institute = new Institute("bnk"+codeNo++, arr[i].substring(0, arr[i].length()-6));
				}else {
					institute = new Institute("bnk"+codeNo++, arr[i].substring(0, arr[i].length()-4));
				}
				instituteList.add(institute);
			}
			String string;
			List<HouseFinance> houseFinances = new ArrayList<HouseFinance>();
			int hfNo = 10000;
			while ((string = br.readLine()) != null) { 
				// *예외* :: 숫자에 ','가 들어간 경우 
				//  (시간복잡도 = O(NM)
				// 				=> N = 문자열길이, M = 쿼리수)
				if(string.split(",").length!= count) {
					StringBuilder sb = new StringBuilder(string);
					int r =sb.indexOf("\"");
					int l;
					int num = sb.length();
					while(r<num && num>0) {
						if(sb.charAt(r) == '"') {
							sb.deleteCharAt(r);
							num--;
							l = r;
							while(sb.charAt(r) != '"' && r<num) r++;
							while(l <= r ) {
								if(sb.charAt(l) == ',') {
									sb.deleteCharAt(r);
									sb.deleteCharAt(l);
									num-=2;
									r--;
									break;
								}
								l++;
							}
						}
						r++;
					}
					string = sb.toString();
					arr = string.split(",");
				}else {
					arr = string.split(",");
				}
					codeNo = 101;
					int year = Integer.parseInt(arr[0]);
					int month = Integer.parseInt(arr[1]);
					String amount;
					HouseFinance houseFinance;
					for(int i=2; i<arr.length; i++) {
						amount = arr[i].replace(",", ""); 
						houseFinance = new HouseFinance(hfNo++, year, month, Integer.parseInt(amount), instituteList.get(i-2));
						houseFinances.add(houseFinance);
					}
			} 
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("instituteList", instituteList);
			map.put("yearlyData", houseFinances);
			return map;
		} catch (IOException e) { 
			e.printStackTrace(); 
			return null;
		}
	}

}
