package com.TestReport.matr.controller;

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
import com.TestReport.matr.domain.MATRBS_VO;
import com.TestReport.matr.service.MatrBaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/C_MatrBase")
public class MatrBaseController {
	@Autowired
	public MatrBaseService matrBaseService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView matrBase(@RequestParam("PGMCOD") String PGMCOD , @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}

	//Version Select - get
	@RequestMapping(value="/MATRBS/VERSION", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMatrVersion( @RequestParam("MATCOD") String MATCOD, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		return matrBaseService.selectMatrVersion(CORCOD, MATCOD); 
	}
	
	//Select - get
	@RequestMapping(value="/MATRBS", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMatrbs(@RequestParam("MATCOD") String MATCOD, @RequestParam("VSDATE") String VSDATE, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
    	return matrBaseService.selectMatrbase(CORCOD, MATCOD, VSDATE); 
	}
	
	//Insert - post
	@RequestMapping(value="/MATRBS", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postMatrbs(@RequestBody HashMap<String, String> param , HttpSession session){
		ObjectMapper mapper = new ObjectMapper();
		MATRBS_VO MATRBS_VO = mapper.convertValue(param, MATRBS_VO.class);
		MATRBS_VO.setCORCOD(((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD());
		MATRBS_VO.setREGUSR(((ZOPR01_VO)session.getAttribute("user_bean")).getUSER_ID());
		return matrBaseService.insertMatrbase(MATRBS_VO);
	}
	
	//Update - put
	@RequestMapping(value="/MATRBS", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putMatrbs(@RequestBody HashMap<String, String> param, HttpSession session){
		ObjectMapper mapper = new ObjectMapper();
		MATRBS_VO MATRBS_VO = mapper.convertValue(param, MATRBS_VO.class);
		MATRBS_VO.setCORCOD(((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD());
		MATRBS_VO.setMODUSR(((ZOPR01_VO)session.getAttribute("user_bean")).getUSER_ID());
		return matrBaseService.updateMatrbase(MATRBS_VO);
	}
	
	//Delete - delete
	@RequestMapping(value="/MATRBS", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteMatrbs(@RequestBody HashMap<String, String> param , HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		param.put("CORCOD", CORCOD);
		return matrBaseService.deleteMatrbase(param);
	}
	

	
	
}
