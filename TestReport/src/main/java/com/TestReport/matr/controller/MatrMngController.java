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
import com.TestReport.matr.service.MatrBaseService;
import com.TestReport.matr.service.MatrMngService;

@RestController
@RequestMapping("/C_MatrMng")
public class MatrMngController {
	@Autowired
	public MatrBaseService matrbaseService;
	@Autowired
	public MatrMngService matrMngService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView matrMng(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Version Select - get
	@RequestMapping(value="/MATRADMIN/VERSION", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMartVersion(@RequestParam("MATCOD") String MATCOD, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		return matrbaseService.selectMatrVersion(CORCOD, MATCOD);
	}
	
	//Item Value Select - get
	@RequestMapping(value="/MATRADMIN/VALUE", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> selectMdtrMngValue(@RequestParam("params") String params, HttpSession session) throws Exception{
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		return matrMngService.selectMatrMngValue(params, CORCOD);
	}
	
	//Select - get
	@RequestMapping(value="/MATRADMIN", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMartMng(@RequestParam("params") String params, HttpSession session) throws Exception{
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
    	return matrMngService.selectMatrMng(params, CORCOD); 
	}
	
	//Insert - post
	@RequestMapping(value="/MATRADMIN", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postMartMng(@RequestBody HashMap<String, Object> param, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		param.put("CORCOD", CORCOD);
		return matrMngService.insertMatrMng(param); 
	}
	
	//Update - put
	@RequestMapping(value="/MATRADMIN", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putMdtrMng(@RequestBody HashMap<String, Object> param, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		param.put("CORCOD", CORCOD);
		return matrMngService.updateMatrMng(param); 
	}
	
	//Delete - delete
	@RequestMapping(value="/MATRADMIN", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteMartMng(@RequestBody HashMap<String, String> param, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		param.put("CORCOD", CORCOD);
		return matrMngService.deleteMatrMng(param); 
	}
}
