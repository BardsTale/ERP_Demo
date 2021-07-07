package com.TestReport.usrMst.controller;

import java.security.PrivateKey;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.common.CharsetHandler;
import com.TestReport.usrMst.service.UsrMstService;

@RestController
@RequestMapping("/A_UsrMst")
public class UsrMstController {
	@Autowired
	public UsrMstService usrMstService;

	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView usrMst(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Select - get
	@RequestMapping(value="/USRMST", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getUsrMst(@RequestParam("USERID") String USERID){
    	return usrMstService.selectUsrMst(USERID); 
	}
	
	//Select - get, RSA 키 재발급
	@RequestMapping(value="/USRMST/RSAKEY", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getRSAKEY(){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}
	
	//Insert - post
	@RequestMapping(value="/USRMST", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postUsrMst(@RequestBody HashMap<String, String> param, HttpSession session) throws Exception{
		//세션의 비공개키 사용
    	PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		return usrMstService.insertUsrMst(privateKey, param); 
	}
	
	//Update - put
	@RequestMapping(value="/USRMST", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putUsrMst(@RequestBody HashMap<String, String> param, HttpSession session) throws Exception{
		//세션의 비공개키 사용
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		return usrMstService.updateUsrMst(privateKey, param); 
	}
	
	//Update - put, 비밀번호 변경
	@RequestMapping(value="/USRMST/PASSWD", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putUsrMstPassword(@RequestBody HashMap<String, String> param, HttpSession session) throws Exception{
		//세션의 비공개키 사용
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		return usrMstService.updateUsrMstPassword(privateKey, param); 
	}
	
	//Delete - delete
	@RequestMapping(value="/USRMST", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteUsrMst(@RequestBody HashMap<String, String> param){
		return usrMstService.deleteUsrMst(param); 
	}
}
