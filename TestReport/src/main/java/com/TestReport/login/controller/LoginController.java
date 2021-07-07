package com.TestReport.login.controller;

import java.security.PrivateKey;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.common.CharsetHandler;
import com.TestReport.configuration.aop.ChasetSetMake;
import com.TestReport.login.domain.ZOPR01_VO;
import com.TestReport.login.service.LoginService;
import com.TestReport.main.domain.CHASET_VO;
import com.TestReport.main.service.MainService;



@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	MainService mainService;
	
	//get 요청 시
	@ChasetSetMake
	@GetMapping
	public ModelAndView getLogin(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
		
	//post 요청 시
	@PostMapping
	public @ResponseBody ResponseEntity<HashMap<String, Object>> postLogin(@ModelAttribute @RequestParam(value = "securedUSERID", required = false, defaultValue = "")String securedUSERID 
    		, @RequestParam(value = "securedPASSWD", required = false, defaultValue = "")String securedPASSWD, HttpSession session, HttpServletResponse response){
    	//세션의 유저 정보 삭제
    	session.removeAttribute("user_bean");
    	PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
    	
    	//복호화를 위한 서비스 호출
    	ResponseEntity<HashMap<String, Object>> result = loginService.doDecrypt(privateKey, securedUSERID, securedPASSWD);
    	if((boolean) HttpStatus.OK.equals(result.getStatusCode())) {
    		ZOPR01_VO ZOPR01_VO = (ZOPR01_VO)result.getBody().get("user_bean");
    		session.setAttribute("user_bean",ZOPR01_VO);
    		//쿠키에 캐릭터셋 저장
    		response.setHeader("Set-Cookie", "CHASET="+ZOPR01_VO.getCHASET()+"; path=/");
    	}
    	return result; 
	}
	
	//로그인 인증 갱신을 위한 간이 로그인, MV 객체 반환
	@RequestMapping(value="/renewal", method=RequestMethod.GET)
	public ModelAndView getRenewalLogin(){
		ModelAndView mv = new ModelAndView("login_renewal");
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//로그인 인증 갱신을 위한 간이 로그인, ResponseEntity 객체 반환
	@RequestMapping(value="/renewal_ajax")
	public @ResponseBody ResponseEntity<HashMap<String, Object>> getRenewalAjaxLogin(){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO();
		result_map.put("msg", CHASET_VO.getMessage_auth_done());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result_map);
	}
	
	//간이 로그인 post 요청(AOP에서 조인포인트를 통해 다르게 postLogin과 다르게 작동)
	@ChasetSetMake
	@RequestMapping(value="/renewal", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<HashMap<String, Object>> postRenewalLogin(@ModelAttribute @RequestParam(value = "securedUSERID", required = false, defaultValue = "")String securedUSERID 
    		, @RequestParam(value = "securedPASSWD", required = false, defaultValue = "")String securedPASSWD, HttpSession session, HttpServletResponse response) throws Exception{
		HashMap<String, Object> session_map = new HashMap<String, Object>();
		
		//세션의 유저 정보 삭제
    	session.removeAttribute("user_bean");
    	PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
    	//복호화를 위한 서비스 호출
    	ResponseEntity<HashMap<String, Object>> result = loginService.doDecrypt(privateKey, securedUSERID, securedPASSWD);
    	if((boolean) HttpStatus.OK.equals(result.getStatusCode())) {
    		//필수 세션 갱신
    		ZOPR01_VO ZOPR01_VO = (ZOPR01_VO)result.getBody().get("user_bean");
    		session.setAttribute("user_bean",ZOPR01_VO);
    		session.setAttribute("USER_ID",ZOPR01_VO.getUSER_ID());
   	    	session.setAttribute("USER_NM",ZOPR01_VO.getUSER_NM());
   	    	session.setAttribute("GRADE",ZOPR01_VO.getGRADE());
    		
    		//날짜
    		session_map = mainService.getMainData();
			session.setAttribute("today", session_map.get("today"));
			
			//갱신값 프론트로 전달
			result.getBody().put("today", session_map.get("today"));
			result.getBody().put("USER_ID", ZOPR01_VO.getUSER_ID());
			result.getBody().put("USER_NM", ZOPR01_VO.getUSER_NM());
			result.getBody().put("GRADE", ZOPR01_VO.getGRADE());
			
			//쿠키에 캐릭터셋 저장
    		response.setHeader("Set-Cookie", "CHASET="+ZOPR01_VO.getCHASET()+"; path=/");
    	}
    	return result; 
	}
}