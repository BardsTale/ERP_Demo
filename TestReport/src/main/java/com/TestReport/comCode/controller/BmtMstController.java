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

import com.TestReport.comCode.service.BmtMstService;
import com.TestReport.common.CharsetHandler;
@RestController
@RequestMapping("/A_BmtMst")
public class BmtMstController {
	@Autowired
	public BmtMstService bmtMstService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView bmtMst(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Select - get
	@RequestMapping(value="/BMTMST", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getBMTMST(@RequestParam("CORCOD") String CORCOD, @RequestParam("MATCOD") String JPCODE){
		return bmtMstService.selectBMTMST(CORCOD, JPCODE);
	}
	
	//Insert - post
	@RequestMapping(value="/BMTMST", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postBMTMST(@RequestBody HashMap<String, String> param){
		return bmtMstService.insertBMTMST(param);
	}
	
	//Excel Insert - post
	@RequestMapping(value="/BMTMST/EXCEL", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postBMTMST_Excel(@RequestBody List<HashMap<String, String>> params){
		return bmtMstService.insertBMTMST_Excel(params);
	}
	
	//Update - put
	@RequestMapping(value="/BMTMST", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putBMTMST(@RequestBody HashMap<String, String> param){
		return bmtMstService.updateBMTMST(param);
	}
	
	//Delete - delete
	@RequestMapping(value="/BMTMST", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteBMTMST(@RequestBody HashMap<String, String> param){
		return bmtMstService.deleteBMTMST(param);
	}
}
