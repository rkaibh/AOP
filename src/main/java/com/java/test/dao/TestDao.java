package com.java.test.dao;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao implements TestDaoInterface {
	
	private static final Logger logger 
		= LoggerFactory.getLogger(TestDao.class);

	@Override
	public HashMap<String, Object> select(HashMap<String, Object> param) {
		HashMap<String, Object> resultMap = param;
		return resultMap;
	}

}
