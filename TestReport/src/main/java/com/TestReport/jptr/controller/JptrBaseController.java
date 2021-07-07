package com.TestReport.jptr.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.common.CharsetHandler;
import com.TestReport.jptr.domain.JPTRBS_VO;
import com.TestReport.jptr.service.JptrbaseService;
import com.TestReport.login.domain.ZOPR01_VO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/D_JptrBase")
public class JptrBaseController {
	@Autowired
	public JptrbaseService jptrbaseService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView jptrBase(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Version Select - get
	@RequestMapping(value="/JPTRBS/VERSION", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getJprtVersion(@RequestParam("JPCODE") String JPCODE, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
    	return jptrbaseService.selectJptrVersion(CORCOD, JPCODE); 
	}
	
	//Select - get
	@RequestMapping(value="/JPTRBS", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getJprtBase(@RequestParam("JPCODE") String JPCODE, @RequestParam("VSDATE") String VSDATE, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
    	return jptrbaseService.selectJptrbase(CORCOD, JPCODE, VSDATE); 
	}
	
	//Insert - post
	@RequestMapping(value="/JPTRBS", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postJprtBase(@RequestBody HashMap<String, String> param, HttpSession session){
		ObjectMapper mapper = new ObjectMapper();
		JPTRBS_VO JPTRBS_VO = mapper.convertValue(param, JPTRBS_VO.class);
		JPTRBS_VO.setCORCOD(((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD());
		JPTRBS_VO.setREGUSR(((ZOPR01_VO)session.getAttribute("user_bean")).getUSER_ID());
		return jptrbaseService.insertJptrbase(JPTRBS_VO); 
	}
	
	//Update - put
	@RequestMapping(value="/JPTRBS", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putJprtBase(@RequestBody HashMap<String, String> param, HttpSession session){
		ObjectMapper mapper = new ObjectMapper();
		JPTRBS_VO JPTRBS_VO = mapper.convertValue(param, JPTRBS_VO.class);
		JPTRBS_VO.setCORCOD(((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD());
		JPTRBS_VO.setMODUSR(((ZOPR01_VO)session.getAttribute("user_bean")).getUSER_ID());
		return jptrbaseService.updateJptrbase(JPTRBS_VO); 
	}
	
	//Delete - delete
	@RequestMapping(value="/JPTRBS", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteJprtBase(@RequestBody HashMap<String, String> param, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		param.put("CORCOD", CORCOD);
		return jptrbaseService.deleteJptrbase(param); 
	}
}
