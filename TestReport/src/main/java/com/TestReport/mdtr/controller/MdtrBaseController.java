package com.TestReport.mdtr.controller;

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
import com.TestReport.login.domain.ZOPR01_VO;
import com.TestReport.mdtr.domain.MDTRBS_VO;
import com.TestReport.mdtr.service.MdtrbaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/B_MdtrBase")
public class MdtrBaseController {
	@Autowired
	public MdtrbaseService mdtrbaseService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView mdtrBase(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Version Select - get
	@RequestMapping(value="/MDTRBS/VERSION", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMdtrBase(@RequestParam("JPCODE") String JPCODE, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
    	return mdtrbaseService.selectMdtrVersion(CORCOD, JPCODE); 
	}
	
	//Select - get
	@RequestMapping(value="/MDTRBS", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMdtrBase(@RequestParam("JPCODE") String JPCODE, @RequestParam("VSDATE") String VSDATE, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
    	return mdtrbaseService.selectMdtrbase(CORCOD, JPCODE, VSDATE); 
	}
	
	//Insert - post
	@RequestMapping(value="/MDTRBS", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postMdtrBase(@RequestBody HashMap<String, String> param, HttpSession session){
		ObjectMapper mapper = new ObjectMapper();
		MDTRBS_VO MDTRBS_VO = mapper.convertValue(param, MDTRBS_VO.class);
		MDTRBS_VO.setCORCOD(((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD());
		MDTRBS_VO.setREGUSR(((ZOPR01_VO)session.getAttribute("user_bean")).getUSER_ID());
		return mdtrbaseService.insertMdtrbase(MDTRBS_VO); 
	}
	
	//Update - put
	@RequestMapping(value="/MDTRBS", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putMdtrBase(@RequestBody HashMap<String, String> param, HttpSession session){
		ObjectMapper mapper = new ObjectMapper();
		MDTRBS_VO MDTRBS_VO = mapper.convertValue(param, MDTRBS_VO.class);
		MDTRBS_VO.setCORCOD(((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD());
		MDTRBS_VO.setMODUSR(((ZOPR01_VO)session.getAttribute("user_bean")).getUSER_ID());
		return mdtrbaseService.updateMdtrbase(MDTRBS_VO); 
	}
	
	//Delete - delete
	@RequestMapping(value="/MDTRBS", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteMdtrBase(@RequestBody HashMap<String, String> param, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		param.put("CORCOD", CORCOD);
		return mdtrbaseService.deleteMdtrbase(param); 
	}
}
