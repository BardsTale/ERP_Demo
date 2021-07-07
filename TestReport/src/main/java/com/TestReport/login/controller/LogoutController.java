package com.TestReport.login.controller;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	//post 요청 시
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> doLogout(HttpSession session) throws Exception{
    	//모든 세션 정보 삭제
    	Enumeration<String> session_enum = session.getAttributeNames();
    	while (session_enum.hasMoreElements()) {
    		session.removeAttribute((String) session_enum.nextElement());
		}
    	return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, Object>()); 
	}
}
