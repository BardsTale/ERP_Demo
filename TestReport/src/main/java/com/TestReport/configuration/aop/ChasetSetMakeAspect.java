package com.TestReport.configuration.aop;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.common.CharsetHandler;
import com.TestReport.main.domain.CHASET_VO;
import com.TestReport.main.service.MainService;

/* 언어셋 생성 AOP 
 * 초기 로그인 화면 및 메인 화면 진입 시 캐릭터셋을 make하고 model 객체에 담음.
 * */
@Aspect
@Component
public class ChasetSetMakeAspect {
	@Autowired
	MainService mainService;
	
	//메소드 실행 후 캐릭터 셋을 CharsetHandler의 싱글톤 인스턴스에 담기
	@Around(value = "@annotation(com.TestReport.configuration.aop.ChasetSetMake)")
	public Object chasetSetSingleton(ProceedingJoinPoint joinPoint) throws Throwable{
		//메소드 실행 전, 후 언어셋을 생성.
		makeChaset();
		Object result = joinPoint.proceed();
		makeChaset();
		
		return result;
	}
	
	//return 타입 ModelAndView
	@AfterReturning(value = "@annotation(com.TestReport.configuration.aop.ChasetSetMake)", returning = "mv")
	public void chasetToMV(JoinPoint joinPoint, ModelAndView mv) throws Exception{
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
	}
	
	//언어셋 생성 메소드
	public void makeChaset() throws Exception {
		List<HashMap<String,String>> chasetList = mainService.getCharSetList();
		//현재 존재하는 언어셋을 등록.
		for(HashMap<String,String> chaset_map : chasetList) {
			CHASET_VO CHASET_VO = new CHASET_VO();
			String chaset = chaset_map.get("CHASET");
			HashMap<String, Object> chamap = mainService.getCharSet(chaset);
			Iterator<?> charset_keys = chamap.keySet().iterator();
			
			//CHASET_VO_MAP에 담기 위한 작업
			while(charset_keys.hasNext()) {
				String key = (String)charset_keys.next();
				PropertyDescriptor pd = new PropertyDescriptor(key, CHASET_VO.getClass());
				Method setter = pd.getWriteMethod();
				setter.invoke(CHASET_VO, chamap.get(key));
			}
			CharsetHandler.getInstance().setMap(chaset, chamap);
			CharsetHandler.getInstance().setVO(chaset, CHASET_VO);
		}
	}
}
