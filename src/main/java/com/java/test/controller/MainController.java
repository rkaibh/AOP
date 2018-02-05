package com.java.test.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.test.dao.MenuDaoInterface;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
public class MainController {
	
	private static final Logger logger 
		= LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(HttpSession session) {
		return "menu";
	}
	
	@RequestMapping(value = "/where", method = RequestMethod.GET)
	public String where(HttpSession session) {
		return "where";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		HashMap<String, Object> user = getParameterMap(req);
		logger.info("param : {}", user);
		resp.setContentType("application/json; charset=utf-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(JSONSerializer.toJSON(user));
		try {
			session.setAttribute("user", user);
			resp.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletResponse resp, HttpSession session) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", 1);
		resp.setContentType("application/json; charset=utf-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(JSONSerializer.toJSON(resultMap));
		try {
			session.invalidate();
			resp.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	MenuDaoInterface mdi;
	
	@RequestMapping(value = "/jsonData", method = RequestMethod.GET)
	public void jsonData(HttpServletResponse resp, HttpSession session) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", mdi.select(session));
		
		resp.setContentType("application/json; charset=utf-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(JSONSerializer.toJSON(resultMap));
		try {
			resp.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, Object> getParameterMap(HttpServletRequest request) {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		Enumeration<?> enums = request.getParameterNames();
		while (enums.hasMoreElements()) {
			String paramName = (String) enums.nextElement();
			String[] parameters = request.getParameterValues(paramName);
			if (parameters.length > 1) {
				parameterMap.put(paramName, parameters);
			} else {
				try {
					parameterMap.put(paramName, parameters[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return parameterMap;
	}
	
}
