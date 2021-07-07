package com.TestReport.comCode.controller;

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

import com.TestReport.comCode.service.CorporationService;
import com.TestReport.common.CharsetHandler;

@RestController
@RequestMapping("/A_CorporMng")
public class CorporationController {
	@Autowired
	public CorporationService corporationService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView CorporationMaster(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Select - get
	@RequestMapping(value="/CORPOR", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getCorporation(@RequestParam("SEARCH") String SEARCH, @RequestParam("SCHGBN") String SCHGBN){
		return corporationService.selectCORPOR(SEARCH, SCHGBN);
	}
	
	//Insert - post
	@RequestMapping(value="/CORPOR", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postCorporation(@RequestBody HashMap<String, String> param, HttpSession session){
		return corporationService.insertCORPOR(param);
	}
	
	//Update - put
	@RequestMapping(value="/CORPOR", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putCorporation(@RequestBody HashMap<String, String> param){
		return corporationService.updateCORPOR(param);
	}
	
	//Delete - delete
	@RequestMapping(value="/CORPOR", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteCorporation(@RequestBody HashMap<String, String> param){
		return corporationService.deleteCORPOR(param);
	}
}
