package com.java.test.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.test.service.TestServiceInterface;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger 
		= LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root() {
		return "redirect:home";
	}
	
	@Autowired
	TestServiceInterface tsi;
	
	@RequestMapping("/Test")
	public String test(HttpServletRequest req){
		// req를 이용하여 DAO까지 데이터를 전달 후 다시 돌려 받기!
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("value", req.getParameter("value")); 
		// get방식으로 온 변수를 map에 담아서 보내기 위하여 넣어준다.
		HashMap<String, Object> resultMap = tsi.select(param); // 서비스 select() 호출
		return "test";
	}
	
}
