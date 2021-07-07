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
import com.TestReport.mdtr.service.MdtrMngService;
import com.TestReport.mdtr.service.MdtrbaseService;

@RestController
@RequestMapping("/B_MdtrMng")
public class MdtrMngController {
	@Autowired
	public MdtrbaseService mdtrbaseService;
	@Autowired
	public MdtrMngService mdtrMngService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView mdtrMng(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Version Select - get
	@RequestMapping(value="/MDTRADMIN/VERSION", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMdtrVersion( @RequestParam("JPCODE") String JPCODE, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		return mdtrbaseService.selectMdtrVersion(CORCOD, JPCODE);
	}
	
	//Item Value Select - get
	@RequestMapping(value="/MDTRADMIN/VALUE", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> selectMdtrMngValue(@RequestParam("params") String params, HttpSession session) throws Exception{
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		return mdtrMngService.selectMdtrMngValue(params, CORCOD);
	}
	
	//Select - get
	@RequestMapping(value="/MDTRADMIN", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMdtrMng(@RequestParam("params") String params, HttpSession session) throws Exception{
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
    	return mdtrMngService.selectMdtrMng(params, CORCOD); 
	}
	
	//Insert - post
	@RequestMapping(value="/MDTRADMIN", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postMdtrMng(@RequestBody HashMap<String, Object> param, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		param.put("CORCOD", CORCOD);
		return mdtrMngService.insertMdtrMng(param); 
	}
	
	//Update - put
	@RequestMapping(value="/MDTRADMIN", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putMdtrMng(@RequestBody HashMap<String, Object> param, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		param.put("CORCOD", CORCOD);
		return mdtrMngService.updateMdtrMng(param); 
	}
	
	//Delete - delete
	@RequestMapping(value="/MDTRADMIN", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteMdtrMng(@RequestBody HashMap<String, String> param, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
		param.put("CORCOD", CORCOD);
		return mdtrMngService.deleteMdtrMng(param); 
	}
}
