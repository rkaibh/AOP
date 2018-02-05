package com.java.test.aop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect
public class TestAOP {
	
	private static final Logger logger 
	= LoggerFactory.getLogger(TestAOP.class);
	
	/***********************************************************
	 * AOP - Aspect Oriented Programming
	 * AOP 용어 정리
     * - Aspect : 공통 기능
     * - Advice : Aspect의 기능 자체
     * - Joinpoint : Advice를 적용해야 되는 부분 (메소드) <- 스프링은 메소드만
     * - Pointcut : Joinpoint의 부분으로 실제로 Advice가 적용되는 부분
     * - Weaving : Advice를 핵심 기능에 적용 하는 행위
     * 
     * 스프링에서 AOP 구현 방법 : proxy 사용
     * - 호출부(client) -> 대행(Proxy) -> 핵심기능(Target)
	 *  
	 * 스프링에서 AOP 구현 방식
	 * - xml 스키마 기반의 AOP 구현
 	 * - @Aspect 어노테이션 기반의 AOP 구현
 	 * 
 	 * @Aspect 구현
 	 * 1. 의존 설정 (pom.xml)
 	 * 2. @Aspect 어노테이션을 이용한 Aspect 클래스 제작
 	 * 3. xml 파일에 <aop:aspectj-autoproxy> 설정
 	 * 
 	 * Aspect Class 어노테이션 종류
 	 * - @Pointcut        : AOP가 실행 될 영역 선언
 	 * - @Around          : 호출 된 메소드가 실행 부분 제어
     * - @Before          : 호출 된 메소드가 실행 전 제어
     * - @AfterReturning  : 호출 된 메소드가 실행 후 리턴값 까지 제어
     * - @AfterThrowing   : 호출 된 메소드가 실행 후 오류까지 제어
     * - @After           : 호출 된 메소드가 실행 후 제어
     * 
     * AspectJ Pointcut 표현식
     * 1. Execution : 메소드 실행 결합점(join points)과 일치시키는데 사용
     *  - @Pointcut("execution(public void get*(..))")  
     *      <- public void인 모든 get메소드
     *  - @Pointcut("execution(* com.java.*.*())")      
     *      <- com.java 패키지에 파라미터가 없는 모든 메소드
     *  - @Pointcut("execution(* com.java..*.*())")     
     *      <- com.java 패키지 & com.java 하위 패키지에 파라미터가 없는 모든 메소드
     *  - @Pointcut("execution(* com.java.User.*())")   
     *      <- com.java.User 안의 모든 메소드
 	 *
 	 * 2. within : 특정 타입에 속하는 결합점(join points)을 정의
   	 * - @Pointcut("within(com.java.*)")      
 	 *      <- com.java 패키지 안에 모든 메소드
   	 * - @Pointcut("within(com.java..*)")     
 	 *      <- com.java 패키지 및 하위 패키지 안에 있는 모든 메소드
   	 * - @Pointcut("within(com.java.User)")   
 	 *      <- com.java.User 모든 메소드
 	 * 
  	 * 3. bean : 특정 bean 타입에 속하는 결합점(join points)을 정의
   	 * - @Pointcut("bean(User)")   <- User 빈에만 적용
   	 * - @Pointcut("bean(*ser)")   <- ~ser로 끝나는 빈에만 적용
 	 *
	 * -JoinPoint 인터페이스는 호출되는 대상 객체, 메소드 그리고 전달되는 파라미터 
	 *  목록에 접근할 수 있는 메서드를 제공
	 * 1) Signature getSignature() - 호출되는 메서드에 대한 정보를 구할수있다.
	 * 2) Object    getTarget()    - 대상 객체를 구할수 있다.
	 * 3) Object[]  getArgs()      - 파라미터 목록을 구할수 있다.
	 * 
	 * - org.aspectj.lang.Signature 인터페이스는 호출되는 메서드와 관련된
	 *   정보를 제공하기 위해 다음과 같은 메서드를 정의 한다.
	 * 1) String getName()     - 메서드의 이름을 구한다.
	 * 2) String toLongName()  - 메서드를 완전하게 표현한 문장을 구한다.
	 *    (메서드의 리턴 타입, 파라미터 타입 모두 표시)
	 * 3) String toShortName() - 메서드를 축약해서 표현한 문장을 구한다. 
	 *    (메서드의 이름만 표시)
	 ***********************************************************/
	
//	@Before("within(com.java.test..*)")
//	public void before(JoinPoint jp) throws Throwable {
//		logger.info("before : " + jp.getSignature().toShortString());
//		Object[] obs = jp.getArgs();
//		for(int i = 0; i < obs.length; i++){
//			
//			if(obs[i] instanceof HashMap){
//				logger.info("data type : HashMap");
//				HashMap<String, Object> map = (HashMap<String, Object>) obs[i];
//				for(String key : map.keySet()){ // map에 있는 key값 가져오기를 위한 for문
//					logger.info("key : " + key + ", value : " + map.get(key));
//				}
//				logger.info("data : " + map); // map으로 데이터 출력
//			}
//			
//		}
//	}
	
//	@After("within(com.java.test..*)")
//	public void after(JoinPoint jp) throws Throwable {
//		logger.info("after : " + jp.getSignature().toShortString());
//	}
	
//	@AfterReturning(pointcut="within(com.java.test..*)", returning="retVal")
//	public void afertReturning(JoinPoint jp, Object retVal){
//		logger.info("afertReturning : " + jp.getSignature().toShortString() + " " + retVal);
//		if(retVal instanceof HashMap){
//			logger.info("data type : HashMap");
//			HashMap<String, Object> map = (HashMap<String, Object>) retVal;
//			for(String key : map.keySet()){ // map에 있는 key값 가져오기를 위한 for문
//				logger.info("key : " + key + ", value : " + map.get(key));
//			}
//			logger.info("data : " + map); // map으로 데이터 출력
//		}
//	}
	
	@Pointcut("execution(* com.java.test.controller.MainController.menu(..))")               
	public void menuMethod(){}
	
	@Pointcut("execution(* com.java.test.controller.MainController.where(..))")
	public void whereMethod(){}
	
	@Around("menuMethod() || whereMethod()")
	public Object controllerAround(ProceedingJoinPoint jp) throws Throwable{
		try {
			String nm = jp.toShortString();
			
			Object[] obs = jp.getArgs();
			for(int i = 0; i < obs.length; i++){
				if(obs[i] instanceof HttpSession){
					HttpSession session = (HttpSession) obs[i];
					HashMap<String, Object> map 
						= (HashMap<String, Object>) session.getAttribute("user");
					logger.info(nm + " --> " + map);
					if(map == null){
						Object retVal = "redirect:home";
						return retVal;
					}
					
					if(map.get("auth").equals(1)) {
						map = null;
					}
				}
			}
			
			Object obj = jp.proceed();
			return obj;
		} finally {
			
		}
	}
	
	@Pointcut("execution(* com.java.test.dao.MenuDao.select(..))")
	public void selectMethod(){}
	
	@Around("selectMethod()")
	public Object daoAround(ProceedingJoinPoint jp) throws Throwable{
		try {
			String nm = jp.toShortString();
			
			Object[] obs = jp.getArgs();
			for(int i = 0; i < obs.length; i++){
				if(obs[i] instanceof HttpSession){
					HttpSession session = (HttpSession) obs[i];
					HashMap<String, Object> map 
						= (HashMap<String, Object>) session.getAttribute("user");
					logger.info(nm + " --> " + map);
					if("gudi@goodee.co.kr".equals(map.get("email"))){
						Object obj = jp.proceed();
						return obj;
					}
				}
			}
			
			List<HashMap<String, Object>> resultList 
				= new ArrayList<HashMap<String, Object>>();
			Object retVal = resultList;
			return retVal;
		} finally {
			
		}
	}
	
//	@Pointcut("within(com.java.test.controller..*)")
//	public void controllerMethod(){}
//	
//	@Pointcut("within(com.java.test.service..*)")
//	public void serviceMethod(){}
//	
//	@Pointcut("within(com.java.test.dao..*)")
//	public void daoMethod(){}
//	
//	@Around("controllerMethod()")
//	public Object controllerAround(ProceedingJoinPoint jp) throws Throwable{
//		 return runMethod(jp);
//	}
//	
//	@Around("serviceMethod()")
//	public Object serviceAround(ProceedingJoinPoint jp) throws Throwable{
//		 return runMethod(jp);
//	}
//	
//	@Around("daoMethod()")
//	public Object daoAround(ProceedingJoinPoint jp) throws Throwable{
//		 return runMethod(jp);
//	}
	
//	private Object runMethod(ProceedingJoinPoint jp) throws Throwable{
//		String nm = jp.getSignature().toShortString();
//		long st = System.currentTimeMillis();
//		Object[] obs = jp.getArgs();
//		for(int i = 0; i < obs.length; i++){
//			if(obs[i] instanceof HashMap){
//				logger.info("Start : " + nm + " 인자값 : " + obs[i]);
//			}
//		}
//		try {
//			Object obj = jp.proceed();
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			if(obj instanceof HashMap){
//				map.put("key", "변경된 값");
//				Object obj2 = (Object) map;
//				logger.info("End   : " + nm + " 리턴값 : " + obj2);
//				return obj2;
//			}else{
//				logger.info("End   : " + nm + " 리턴값 : " + obj);
//				return obj;
//			}
//		} finally {
//			long en = System.currentTimeMillis();
//			logger.info(nm + " 경과 시간 : " + (en - st));
//		}
//	}
	
}
