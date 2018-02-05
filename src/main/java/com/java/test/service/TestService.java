package com.java.test.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.test.dao.TestDaoInterface;

@Service
public class TestService implements TestServiceInterface {

	private static final Logger logger 
	= LoggerFactory.getLogger(TestService.class);
	
	@Autowired
	TestDaoInterface tdi;
	
	@Override
	public HashMap<String, Object> select(HashMap<String, Object> param) {
		HashMap<String, Object> resultMap = tdi.select(param); // DAO select() 호출
		return resultMap;
	}

}
