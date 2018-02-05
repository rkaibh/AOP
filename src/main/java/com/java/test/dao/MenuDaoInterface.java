package com.java.test.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

public interface MenuDaoInterface {
	
	public List<HashMap<String, Object>> select(HttpSession session);
	
}
