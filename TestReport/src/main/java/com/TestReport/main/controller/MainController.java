package com.TestReport.main.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.configuration.aop.ChasetSetMake;
import com.TestReport.login.domain.ZOPR01_VO;
import com.TestReport.main.service.MainService;


@Controller
public class MainController {
	@Autowired
	MainService mainService;
	
	@ChasetSetMake
	@RequestMapping(value = { "/", "index" })
	public ModelAndView mainSetting(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("index");//기본 index페이지로 이동
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		
		/* 암호화를 위해 공개키를 생성해서 사용자에게 전달한다.
		* 세션에 공개키의 문자열을 키로하여 개인키를 저장한다.
		* 로직은 AOP 방면에서 처리
		*/
		
		//유저 접속 정보 체크
		ZOPR01_VO ZOPR01_VO = (ZOPR01_VO)session.getAttribute("user_bean");
		if(ZOPR01_VO == null) {
			mv.setViewName("login");
			return mv;
		}else {
			//날짜, 버전
			result_map = mainService.getMainData();
            session.setAttribute("today", result_map.get("today"));
            session.setAttribute("version", result_map.get("version"));
			
			//초기화 후 재사용
			//메뉴들
			result_map.clear();
			result_map = mainService.getMenus(ZOPR01_VO.getCHASET());
   	    	mv.addObject("menu_Head", result_map.get("menu_Head"));
   	    	mv.addObject("menu_Body", result_map.get("menu_Body"));
   	    	session.setAttribute("USER_ID",ZOPR01_VO.getUSER_ID());
   	    	session.setAttribute("USER_NM",ZOPR01_VO.getUSER_NM());
   	    	session.setAttribute("GRADE",ZOPR01_VO.getGRADE());
		}
		return mv;
	}
	
	@RequestMapping(value="CODMST", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<HashMap<String, Object>> getCODMST(@RequestParam("PROC_KIND") String proc_kind, @RequestParam("MAJOCD") String MAJOCD, @RequestParam("MINOCD") String MINOCD, HttpSession session) throws Exception {
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		return mainService.selectCODMST(proc_kind, MINOCD, MAJOCD, CORCOD);
	}
	
	@RequestMapping(value="LANG", method=RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getCharSetByLang(@RequestParam("CHASET") String CHASET, HttpSession session, HttpServletResponse response) throws Exception {
		HashMap<String, Object> result_map = mainService.getCharSet(CHASET);
		
		//언어셋을 지정하면 쿠키에 저장함.
		response.setHeader("Set-Cookie", "CHASET="+CHASET+"; path=/");
		return result_map;
	}
}
