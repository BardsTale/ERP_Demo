package com.TestReport.comCode.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.comCode.service.JepMstService;
import com.TestReport.common.CharsetHandler;
@RestController
@RequestMapping("/A_JepMst")
public class JepMstController {
	@Autowired
	public JepMstService jepMstService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView jepMst(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Select - get
	@RequestMapping(value="/JEPMST", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getJEPMST(@RequestParam("CORCOD") String CORCOD, @RequestParam("JPCODE") String JPCODE){
		return jepMstService.selectJEPMST(CORCOD, JPCODE);
	}
	
	//Insert - post
	@RequestMapping(value="/JEPMST", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postJEPMST(@RequestBody HashMap<String, String> param){
		return jepMstService.insertJEPMST(param);
	}
	
	//Excel Insert - post
	@RequestMapping(value="/JEPMST/EXCEL", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postJEPMST_Excel(@RequestBody List<HashMap<String, String>> params){
		return jepMstService.insertJEPMST_Excel(params);
	}
	
	//Update - put
	@RequestMapping(value="/JEPMST", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putJEPMST(@RequestBody HashMap<String, String> param){
		return jepMstService.updateJEPMST(param);
	}
	
	//Delete - delete
	@RequestMapping(value="/JEPMST", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteJEPMST(@RequestBody HashMap<String, String> param){
		return jepMstService.deleteJEPMST(param);
	}
}
