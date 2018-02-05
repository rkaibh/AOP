package com.java.test.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

@Repository
public class MenuDao implements MenuDaoInterface {

	@Override
	public List<HashMap<String, Object>> select(HttpSession session) {
		HashMap<String, Object> dataMap;
		List<HashMap<String, Object>> dataList 
			= new ArrayList<HashMap<String,Object>>();
		
		dataMap = new HashMap<String, Object>();
		dataMap.put("no", 1);
		dataMap.put("name", "계시판");
		dataList.add(dataMap);
		
		dataMap = new HashMap<String, Object>();
		dataMap.put("no", 2);
		dataMap.put("name", "구디방");
		dataList.add(dataMap);
		
		return dataList;
	}

}
